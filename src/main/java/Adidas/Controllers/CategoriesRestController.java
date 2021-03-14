/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adidas.Controllers;

import Adidas.Repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Chahir Chalouati
 */
@RestController
@RequestMapping("/categories")
@AllArgsConstructor
public class CategoriesRestController {
    private final Logger logger = LoggerFactory.getLogger(AuthRestController.class);
    private final CategoryRepository categoryRepository;

    @GetMapping()
    public ResponseEntity<?> getAll() {
        logger.debug("RESOURCE::REQUEST TO GET ALL CATEGORIES");
        return new ResponseEntity<>(categoryRepository.findAll(), HttpStatus.OK);
    }

}
