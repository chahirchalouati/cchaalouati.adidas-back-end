/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adidas.Repositories;

import Adidas.Entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Chahir Chalouati
 */
public interface ContactRepository extends JpaRepository<Contact, Long> {

}
