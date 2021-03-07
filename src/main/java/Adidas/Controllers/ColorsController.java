/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adidas.Controllers;

import Adidas.Entities.Color;
import Adidas.Repositories.ColorRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Chahir Chalouati
 */
@RestController
@RequestMapping("/colors")
@AllArgsConstructor
public class ColorsController {

    private final ColorRepository colorRepository;

    @GetMapping
    public ResponseEntity<?> getAll() {

        Pageable page = PageRequest.of(0, 100);
        return ResponseEntity.ok().body(colorRepository.findAll(page));

    }

    @GetMapping("/{name}")
    public ResponseEntity<?> getByName(@PathVariable(name = "name") String name) {
        Pageable page = PageRequest.of(0, 100);
        Page<List<Color>> findByNameContainingIgnoreCase = colorRepository.findByNameContainingIgnoreCase(name, page);
        return new ResponseEntity<>(findByNameContainingIgnoreCase, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody List<Color> color) {
        List<Color> saveAll = colorRepository.saveAll(color);
        return new ResponseEntity<>(saveAll, HttpStatus.CREATED);
    }

}
