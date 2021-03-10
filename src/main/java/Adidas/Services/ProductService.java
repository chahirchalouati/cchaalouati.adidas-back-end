/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adidas.Services;

import Adidas.Entities.Category;
import Adidas.Entities.Color;
import Adidas.Entities.File;
import Adidas.Entities.Product;
import Adidas.Entities.Rating;
import Adidas.Entities.Size;
import Adidas.Exceptions.FileStorageException;
import Adidas.Projection.ProductVW;
import Adidas.Repositories.CategoryRepository;
import Adidas.Repositories.ColorRepository;
import Adidas.Repositories.ProductRepository;
import Adidas.Repositories.SizeRepository;
import Adidas.Repositories.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Chahir Chalouati
 */
@Service
@AllArgsConstructor
public class ProductService {

    private final ColorRepository colorRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final SizeRepository sizeRepository;
    private final UserRepository userRepository;
    private final FileService fileService;

    @Transactional
    public ResponseEntity<?> create(ProductVW productVW) {

        //check required media file in product
        int filesCount = productVW.getFiles().size();
        if (filesCount <= 0) {
            throw new FileStorageException("Product must contain at least one  Image Or Video");
        }
        List<File> files = productVW.getFiles().stream()
                .map(file -> fileService.storeFiles(file)).collect(Collectors.toList());

        List<Size> sizes = productVW.getSizes()
                .stream()
                .distinct()
                .collect(Collectors.toList())
                .stream()
                .map(size -> sizeRepository.getOne(size))
                .collect(Collectors.toList());

        List<Category> categories = productVW.getCategories()
                .stream()
                .distinct()
                .collect(Collectors.toList())
                .stream().map(category -> categoryRepository.getOne(category)).collect(Collectors.toList());

        List<Color> colors = productVW.getColors()
                .stream()
                .distinct()
                .collect(Collectors.toList()).stream()
                .map(color -> colorRepository.getOne(color)).collect(Collectors.toList());

        Product product = new Product();
//        Faker faker = new Faker();
//=================********FAKE DATA OF PRODUCT*********============================//
//        product.setGender(faker.hipster().word());
//        product.setProductionDate(faker.date().past(1000, TimeUnit.DAYS));
//        product.setQuantity(faker.random().nextInt(0, 15000));
//        product.setPrice(Double.valueOf(faker.commerce().price(1.99, 3.9999)));
//        product.setName(faker.commerce().productName());
//        product.setCode(faker.commerce().promotionCode());
//=================********REAL DATA OF PRODUCT *********============================//
        product.setGender(productVW.getGender());
        product.setProductionDate(productVW.getProductionDate());
        product.setQuantity(productVW.getQuantity());
        product.setPrice(productVW.getPrice());
        product.setName(productVW.getName());
        product.setCode(productVW.getCode());
        product.setDescription(productVW.getDescription());
        product.setColors(colors);
        product.setCategories(categories);
        product.setSizes(sizes);
        product.setFiles(files);
        Product save = productRepository.save(product);
        System.out.println(save.toString());
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    public ResponseEntity<?> delete(Long id) {

        return new ResponseEntity<>(HttpStatus.OK);

    }

    public ResponseEntity<?> update(Long id, Product product) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     *
     * @param page
     * @param size
     * @param sort
     * @return Page products
     */
    @Transactional(readOnly = true)
    public ResponseEntity<?> findAllPageable(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return new ResponseEntity<>(productRepository.findAll(pageable), HttpStatus.OK);
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

    public ResponseEntity<?> findAny(String param, String sort, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Page<Product> findAll = productRepository.findByNameOrCodeContainingIgnoreCase(param, param, pageable);

        return new ResponseEntity<>(findAll, HttpStatus.OK);
    }

}
