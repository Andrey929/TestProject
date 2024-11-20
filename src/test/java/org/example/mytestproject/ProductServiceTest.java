package org.example.mytestproject;

import org.example.mytestproject.Service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class ProductServiceTest {
    ProductService productService;
    @BeforeEach
    public void init(){
        productService = new ProductService();
        productService.createProduct("Apple","Color-green",100D,true);
    }



    @Test
    public void createProductTest(){
        productService.createProduct("Peach","Orange-red",100D,true);
        assertEquals(2,productService.getAllProducts().size());
    }
    @Test
    public void getByNameTest(){
        assertEquals("Apple",productService.getByName("Apple").getName());
    }
    @Test
    public void updateProductTest(){
        productService.updateName("Apple","Pineapple");
        productService.updateDescription("Pineapple","Red-Green");
        productService.updatePrice("Pineapple",250D);
        productService.updateAvailability("Pineapple",false);

        assertEquals("Pineapple",productService.getByName("Pineapple").getName());
        assertEquals("Red-Green",productService.getByName("Pineapple").getDescription());
        assertEquals(250D,productService.getByName("Pineapple").getPrice());
        assertFalse(productService.getByName("Pineapple").isAvailability());

    }

}
