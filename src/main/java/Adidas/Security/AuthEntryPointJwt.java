/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adidas.Security;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 *
 * @author Chahir Chalouati
 */
@Component
@Slf4j
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
        final String expired = (String) request.getAttribute("expired");
        if (expired != null) {
//            log.info(expired);
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, expired);
        } else {

            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Login Credentials");
//            log.info("Invalid Sign-in details \n"
//                    + request.getHeader("authorization") + "\n"
//                    + request.getHeader("host") + "\n"
//            );
        }
    }

}
