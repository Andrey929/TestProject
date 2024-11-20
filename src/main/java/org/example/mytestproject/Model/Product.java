package org.example.mytestproject.Model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Product {
    private Long id;
    private String name;
    private String description;
    private double price;
    private boolean availability;
    public Product(String name,String description,double price,boolean availability){
        this.name = name;
        this.description = description;
        this.price = price;
        this.availability = availability;


    }

}
