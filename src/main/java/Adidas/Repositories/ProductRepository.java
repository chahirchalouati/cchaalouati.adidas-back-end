/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adidas.Repositories;

import Adidas.Entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Chahir Chalouati
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    public Page<Product> findByNameOrCodeContainingIgnoreCase(String param, String param0, Pageable pageable);

}
