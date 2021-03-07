/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adidas.Services;

import Adidas.Entities.File;
import Adidas.Exceptions.FileStorageException;
import Adidas.Repositories.FileRepository;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Chahir Chalouati
 */
@Service
@Slf4j
public class FileService {

    private final String currentDir = "./adidas/media/products";

    private FileRepository fileRepository;

    public FileService() {
        try {
            boolean dirExists = Files.exists(Paths.get(currentDir).toAbsolutePath().normalize());

            if (!dirExists) {
                Files.createDirectory(Paths.get(currentDir).toAbsolutePath().normalize());
            }
        } catch (IOException e) {
            log.error(currentDir, e);
        }

    }

    /**
     * store assets file like image for shop and other
     *
     * @param file
     * @return
     * @throws IOException
     */
    @Transactional
    public File storeFiles(MultipartFile file) throws IOException {

        String fileExtentions = file.getOriginalFilename().split("\\.")[1];
        // to make file unique i use  combination of instant as String an UUID api to generate unique name
        long epochSecond = Instant.now().getEpochSecond();
        String newFileName
                = //new Instant token as String
                String.valueOf(epochSecond)
                        //random UUID
                        .concat(UUID.randomUUID().toString())
                        //retreive extentions of file
                        .concat(".")
                        .concat(fileExtentions);

        String concat = currentDir
                .concat("/")
                .concat(newFileName);

        //create path for new File to store
        Path path = Paths.get(concat).toAbsolutePath().normalize();
        // copy file in base directory
        long copy = Files
                .copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        // prepare file data for database storage
        File File = new File(
                null,
                newFileName,
                path.toString(),
                "/files/" + newFileName,
                copy,
                file.getContentType(),
                new Date(),
                new Date());
        if (copy > 0) {
            fileRepository.save(File);
        } else {
            throw new FileStorageException("Unable to save File ");
        }
        return File;
    }

    /**
     * store assets file like image for shop and other
     *
     * @param stream
     * @param fileName
     * @param fileExtentions
     * @return
     * @throws IOException
     */
    @Transactional
    public File storeImageResized(InputStream stream, String fileName, String fileExtentions) throws IOException {
        String concat = currentDir
                .concat("/")
                .concat(fileName);

        //create path for new File to store
        Path path = Paths.get(concat).toAbsolutePath().normalize();
        // copy file in base directory
        long copy = Files
                .copy(stream, path, StandardCopyOption.REPLACE_EXISTING);
        // prepare file data for database storage
        File File = new File(null, path.toString(), fileName, "/files/" + fileName, copy, "image/jpeg", new Date(), new Date());
        if (copy > 0) {
            fileRepository.save(File);
        } else {
            throw new FileStorageException("Unable to save File ");
        }
        return File;
    }

    /**
     * download File
     *
     * @param filename
     * @param request
     * @return
     */
    public ResponseEntity<?> getFile(String filename, HttpServletRequest request) {

        try {

            File file = fileRepository.findByName(filename);

            Path path = Paths.get(file.getFilePath()).toAbsolutePath().normalize();

            Resource resource = new UrlResource(path.toUri());

            if (resource.exists()) {

                String contentType = request
                        .getServletContext()
                        .getMimeType(
                                resource.getFile()
                                        .getAbsolutePath());

                if (contentType == null) {
                    contentType = "application/octet-stream";
                }

                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(contentType))
                        .cacheControl(CacheControl.maxAge(1, TimeUnit.DAYS).cachePrivate().proxyRevalidate())
                        .header(HttpHeaders.CONTENT_LENGTH, String.valueOf(resource.contentLength()))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);

            }

        } catch (MalformedURLException ex) {
            Logger.getLogger(FileService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ResponseEntity.notFound().build();
    }

}
