/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adidas.Repositories;

import Adidas.Entities.Category;
import java.util.List;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Chahir Chalouati
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Cacheable(value = "colors")
    @Override
    public List<Category> findAll();

}
