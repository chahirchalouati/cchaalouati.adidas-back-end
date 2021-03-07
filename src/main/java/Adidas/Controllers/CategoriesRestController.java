/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adidas.Controllers;

import Adidas.Repositories.CategoryRepository;
import lombok.AllArgsConstructor;
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

    private final CategoryRepository categoryRepository;

    @GetMapping()
    public ResponseEntity<?> getAll() {

        return new ResponseEntity<>(categoryRepository.findAll(), HttpStatus.OK);
    }

}
