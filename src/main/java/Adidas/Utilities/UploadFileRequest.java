/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adidas.Utilities;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Chahir Chalouati
 */
@Data
public class UploadFileRequest {

    private String username;
    private MultipartFile file;
}
