/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adidas.Services;

import Adidas.Entities.Category;
import Adidas.Entities.Color;
import Adidas.Entities.Product;
import Adidas.Entities.Rating;
import Adidas.Entities.Size;
import Adidas.Repositories.CategoryRepository;
import Adidas.Repositories.ProductRepository;
import Adidas.Repositories.SizeRepository;
import Adidas.Repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author Chahir Chalouati
 */
@Service
@AllArgsConstructor
public class ProductService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final SizeRepository sizeRepository;
    private final UserRepository userRepository;
    private final FileService fileService;

    public ResponseEntity<?> create(Product product) {

        //name
        //code
        //set is new to new
        //price
        //description
        //list of files
        //[*]
        //list of categories
        //list of colors
        return new ResponseEntity<>(HttpStatus.OK);

    }

    public ResponseEntity<?> delete(Long id) {

        return new ResponseEntity<>(HttpStatus.OK);

    }

    public ResponseEntity<?> update(Long id, Product product) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> findAllPageable(int page, int size, Sort sort) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> findByName(String name) {

        return new ResponseEntity<>(HttpStatus.OK);

    }

    public ResponseEntity<?> findById(Long id) {

        return new ResponseEntity<>(HttpStatus.OK);

    }

    public ResponseEntity<?> findByCategory(Category category) {

        return new ResponseEntity<>(HttpStatus.OK);

    }

    public ResponseEntity<?> findByColor(Color color) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> findByRating(Rating rating) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> findBySize(Size size) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> findByPrice(Double min, Double max) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
