/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adidas.Controllers;

import Adidas.Entities.User;
import Adidas.Services.AuthenticationServiceImpl;
import Adidas.Utilities.SignInRequest;
import Adidas.Utilities.SignUpRequest;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Chahir Chalouati
 */
@RestController
@AllArgsConstructor
public class AuthRestController {

    private final Logger logger = LoggerFactory.getLogger(AuthRestController.class);
    private final AuthenticationServiceImpl authenticationServiceImpl;

    @PostMapping(value = "/signin")
    public ResponseEntity<?> signIn(@RequestBody SignInRequest request) {
        logger.debug("RESOURCE::REQUEST TO SIGNIN {}", request);
        return authenticationServiceImpl.signIn(request);
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpRequest request) {
        logger.debug("RESOURCE::REQUEST TO SIGNUP {}", request);
        User result = authenticationServiceImpl.signUp(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}
