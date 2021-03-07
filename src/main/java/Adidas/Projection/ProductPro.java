/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adidas.Projection;

import Adidas.Entities.Category;
import Adidas.Entities.Color;
import Adidas.Entities.Size;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Chahir Chalouati
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductPro {

    private String name;
    private String code;
    private Double price;
    private String description;
    private List<MultipartFile> files = new ArrayList<>();
    private List<Color> colors = new ArrayList<>();
    private List<Size> sizes = new ArrayList<>();
    private List<Category> categories = new ArrayList<>();

}
