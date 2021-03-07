/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adidas.Utilities;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Chahir Chalouati
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {

    @Email
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String firstname;
    @NotBlank
    private String lastname;
}
