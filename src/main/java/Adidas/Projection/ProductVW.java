/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adidas.Projection;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Chahir Chalouati
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductVW {

    private String name;
    private String code;
    private Double price;
    private String description;
    private Integer quantity;
    private String gender;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date productionDate;
    private List<MultipartFile> files = new ArrayList<>();
    private List<Long> colors = new ArrayList<>();
    private List<Long> sizes = new ArrayList<>();
    private List<Long> categories = new ArrayList<>();

}
