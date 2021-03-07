/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adidas.Repositories;

import Adidas.Entities.Color;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Chahir Chalouati
 */
public interface ColorRepository extends JpaRepository<Color, Long> {

    public Color findByName(String name);

    public Page<List<Color>> findByNameContainingIgnoreCase(String name, Pageable page);

}
