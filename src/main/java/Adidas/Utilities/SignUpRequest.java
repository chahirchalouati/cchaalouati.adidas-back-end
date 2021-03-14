/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adidas.Utilities;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.*;

/**
 * @author Chahir Chalouati
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class SignUpRequest {
    @Email(message = "error.email.not-valid")
    private String email;
    @NotBlank(message = "error.password.required")
    private String password;
    @NotBlank(message = "error.firstname.required")
    private String firstname;
    @NotBlank(message = "error.lastname.required")
    private String lastname;
}
