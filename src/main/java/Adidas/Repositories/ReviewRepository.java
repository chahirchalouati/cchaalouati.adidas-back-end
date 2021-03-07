/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adidas.Repositories;

import Adidas.Entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Chahir Chalouati
 */
@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

}
