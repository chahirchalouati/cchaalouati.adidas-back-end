/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adidas.Repositories;

import Adidas.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Chahir Chalouati
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public User findByEmail(String email);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u "
            + "WHERE u.email = :email")
    Boolean existByEmail(@Param("email") String email);
}
