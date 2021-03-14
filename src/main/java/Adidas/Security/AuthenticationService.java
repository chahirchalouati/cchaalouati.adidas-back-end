/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adidas.Security;

import Adidas.Entities.User;
import Adidas.Utilities.SignInRequest;
import Adidas.Utilities.SignUpRequest;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author Chahir Chalouati
 */
public interface AuthenticationService {

    User signUp(SignUpRequest request);

    ResponseEntity<?> signIn(SignInRequest request);
}
