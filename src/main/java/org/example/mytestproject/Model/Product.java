package org.example.mytestproject.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 255)
    private String name;

    @Column(name = "description", length = 4096)
    private String description;

    private double price;
    private boolean availability;

    private Long count;
    public Product(String name,String description,double price,boolean availability){
        this.name = name;
        this.description = description;
        this.price = price;
        this.availability = availability;


    }

}
