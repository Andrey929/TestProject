package org.example.mytestproject.DTO;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ProductDTO {
    private String name;
    private String newName;
    private String description;
    private double price;
    private boolean availability;
}
