/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adidas.Services;

import Adidas.Entities.User;
import Adidas.Exceptions.UserExistException;
import Adidas.Repositories.RoleRepository;
import Adidas.Repositories.UserRepository;
import Adidas.Security.AuthenticationService;
import Adidas.Utilities.JWTResponse;
import Adidas.Utilities.JwtUtils;
import Adidas.Utilities.SignInRequest;
import Adidas.Utilities.SignUpRequest;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Chahir Chalouati
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    private BCryptPasswordEncoder encoder;

    @Override
    public ResponseEntity<?> signUp(SignUpRequest request) {
        if (userRepository.existByEmail(request.getEmail())) {
            throw new UserExistException("E-mail already exist");
        }
        encoder = new BCryptPasswordEncoder();

        User user = new User();
        user.setEmail(request.getEmail());
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setRoles(List.of(roleRepository.findByRole("USER")));
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> signIn(SignInRequest request) {

        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());

        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);

        return ResponseEntity.ok(new JWTResponse(jwt, new Date()));

    }

}
