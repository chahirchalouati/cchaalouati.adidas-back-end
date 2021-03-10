/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adidas.Services;

import Adidas.Entities.File;
import Adidas.Exceptions.FileStorageException;
import Adidas.Repositories.FileRepository;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Chahir Chalouati
 */
@Service
@Slf4j
public class FileService {

    @Autowired
    private FileRepository fileRepository;

    private final String baseDir = ".\\products";

    public FileService() throws IOException {
        boolean dirExists = Files.exists(Paths.get(baseDir).toAbsolutePath().normalize());

        if (!dirExists) {
            Files.createDirectory(Paths.get(baseDir).toAbsolutePath().normalize());
        }

    }

    /**
     * store assets file like image for shop and other
     *
     * @param multipartFile
     * @return
     */
    public File storeFiles(MultipartFile multipartFile) {

        try {
            String fileExtentions = multipartFile.getOriginalFilename()
                    .substring(multipartFile.getOriginalFilename().length() - 3);

            String fileName = "product__".concat(UUID.randomUUID().toString())
                    .concat("size___original")
                    .concat(".")
                    .concat(fileExtentions);

            Path path = Paths.get(baseDir.concat("/").concat(fileName)).toAbsolutePath().normalize();
            // copy file in base directory
            long copy = Files.copy(multipartFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            File file = new File();
            file.setCreatedAt(new Date());
            file.setFilePath(path.toString());
            file.setFileType(multipartFile.getContentType());
            file.setFileUrl("/files/".concat(fileName));
            file.setName(fileName);
            file.setFileSize(copy);
            file.setId(null);
            File save = fileRepository.save(file);

            return save;

        } catch (IOException ex) {
            throw new FileStorageException("Unable to save file {} " + multipartFile.getOriginalFilename());
        }

    }

    BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws Exception {
        return Scalr.resize(originalImage, Scalr.Method.AUTOMATIC, Scalr.Mode.AUTOMATIC, targetWidth, targetHeight, Scalr.OP_ANTIALIAS);
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
