/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adidas.Controllers;

import Adidas.Entities.Color;
import Adidas.Repositories.ColorRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Chahir Chalouati
 */
@RestController
@RequestMapping("/colors")
@AllArgsConstructor
public class ColorsController {
    private final Logger logger = LoggerFactory.getLogger(AuthRestController.class);
    private final ColorRepository colorRepository;

    @GetMapping
    public ResponseEntity<?> getAll() {
        logger.debug("RESOURCE::REQUEST TO GET ALL COLORS");
        // todo fix the pagination request
        Pageable page = PageRequest.of(0, 100);
        return ResponseEntity.ok().body(colorRepository.findAll(page));

    }

    @GetMapping("/{name}")
    public ResponseEntity<?> getByName(@PathVariable(name = "name") String name) {
        logger.debug("RESOURCE::REQUEST TO GET BY NAME {}", name);
        // todo fix the pagination request
        Pageable page = PageRequest.of(0, 100);
        Page<List<Color>> findByNameContainingIgnoreCase = colorRepository.findByNameContainingIgnoreCase(name, page);
        return new ResponseEntity<>(findByNameContainingIgnoreCase, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody List<Color> color) {
        logger.debug("RESOURCE::REQUEST TO POST COLORS {}", color);
        List<Color> saveAll = colorRepository.saveAll(color);
        return new ResponseEntity<>(saveAll, HttpStatus.CREATED);
    }

}
