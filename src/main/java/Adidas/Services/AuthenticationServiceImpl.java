/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adidas.Services;

import Adidas.DTO.SignUpRequestMapper;
import Adidas.Entities.User;
import Adidas.Exceptions.UserExistException;
import Adidas.Repositories.UserRepository;
import Adidas.Security.AuthenticationService;
import Adidas.Utilities.JWTResponse;
import Adidas.Utilities.JwtUtils;
import Adidas.Utilities.SignInRequest;
import Adidas.Utilities.SignUpRequest;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

/**
 * @author Chahir Chalouati
 */
@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final Logger logger = LoggerFactory.getLogger(AuthenticationServiceImpl.class);
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final SignUpRequestMapper signUpRequestMapper;
    private final JwtUtils jwtUtils;


    @Override
    public User signUp(SignUpRequest request) {
        logger.debug("SERVICE::REQUEST TO SIGNUP {}", request);
        if (userRepository.existByEmail(request.getEmail())) {
            throw new UserExistException("E-mail already exist");
        }
        return Optional.of(request)
                .map(signUpRequestMapper::toModel)
                .map(userRepository::save)
                .orElse(null);
    }

    @Override
    public ResponseEntity<?> signIn(SignInRequest request) {
        logger.debug("SERVICE::REQUEST TO SIGNIN {}", request);
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());

        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);

        return ResponseEntity.ok(new JWTResponse(jwt, new Date()));

    }

}
