/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adidas.Controllers;

import Adidas.Services.FileService;
import javax.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Chahir Chalouati
 */
@RestController
@RequestMapping("/files")
@AllArgsConstructor
public class FilesController {

    private final FileService fileService;

    @GetMapping(value = "/{filename}")
    public ResponseEntity<?> list(@PathVariable(name = "filename") String filename, HttpServletRequest request) {
        return fileService.getFile(filename, request);
    }

}
