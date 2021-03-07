/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package Adidas.Controllers;

import Adidas.Services.AuthenticationServiceImpl;
import Adidas.Utilities.SignInRequest;
import Adidas.Utilities.SignUpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Chahir Chalouati
 */
@RestController
public class AuthRestController {

    @Autowired
    private AuthenticationServiceImpl authenticationServiceImpl;

    @PostMapping(value = "/signin")
    public ResponseEntity<?> signIn(@RequestBody SignInRequest request) {
        return authenticationServiceImpl.signIn(request);
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpRequest request) {
        return authenticationServiceImpl.signUp(request);
    }
}
