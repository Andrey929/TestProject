package org.example.mytestproject.Service;

import org.example.mytestproject.Data.ProductData;
import org.example.mytestproject.Model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductService {
    ProductData productData = new ProductData();


    public boolean createProduct(String name,String description, double price,boolean availability){
        if (validate(name,description,price)) {
            productData.addProduct(new Product(name, description, price, availability));
            return true;
        }else return false;
    }

    public ArrayList<Product> getAllProducts(){
        return productData.getAll();
    }

    public Product getByName(String name){
        return productData.getProductByName(name);
    }

    public boolean deleteProduct(String name){
        if (productData.deleteProductByName(name)) {
            return true;
        }else return false;
    }


    public boolean updateName(String name,String newName){
        Product prod = productData.getProductByName(name);
        if (prod != null) {
            if (validate(newName,prod.getDescription())) {
                prod.setName(newName);
                productData.updateProduct(name, prod);
                return true;
            }else return false;
        }else return false;
    }

    public boolean updateDescription(String name,String newDescription){
        Product prod = productData.getProductByName(name);
        if (prod != null) {
            if (validate(name,newDescription)) {
                prod.setDescription(newDescription);
                productData.updateProduct(name, prod);
                return true;
            }else return false;
        }else return false;
    }

    public boolean updatePrice(String name,double newPrice){
        Product prod = productData.getProductByName(name);
        if (prod != null) {
            if (validate(name, prod.getDescription(),newPrice)) {
                prod.setPrice(newPrice);
                productData.updateProduct(name, prod);
                return true;
            }else return false;
        }else return false;
    }

    public boolean updateAvailability(String name,boolean availability){
        Product prod = productData.getProductByName(name);
        if (prod != null) {
                prod.setAvailability(availability);
                productData.updateProduct(name, prod);
                return true;
        }else return false;
    }










    private boolean validate(String name,String description, double price){
        if (name.isBlank() | name.length() > 255 | description.length() > 4096 | price < 0) {
            return false;
        }else return true;
    }
    private boolean validate(String name,String description){
        if (name.isBlank() | name.length() > 255 | description.length() > 4096) {
            return false;
        }else return true;
    }
}
