/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adidas.Entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author Chahir Chalouati
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Products")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String code;
    private Double price;
    private Integer quantity;
    private String description;
    private Boolean isNew = true;
    @Temporal(TemporalType.DATE)
    private Date productionDate = new Date();

    @OneToMany
    private List<Color> colors = new ArrayList<>();
    @OneToMany
    private List<Size> sizes = new ArrayList<>();
    @OneToMany
    private List<Category> categories = new ArrayList<>();
    @OneToMany
    private List<Detail> details = new ArrayList<>();
    @OneToMany
    private List<Rating> ratings = new ArrayList<>();
    @OneToMany
    private List<Review> reviews = new ArrayList<>();
    @OneToMany
    private List<File> files = new ArrayList<>();
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

}
