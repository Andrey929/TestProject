package org.example.mytestproject.Service;

import lombok.RequiredArgsConstructor;
import org.example.mytestproject.Model.Product;
import org.example.mytestproject.Repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;




    public boolean createProduct(String name,String description, double price,boolean availability){
        if (validate(name,description,price)) {
            productRepository.save(new Product(name, description, price, availability));
            return true;
        }else return false;
    }

    public ArrayList<Product> getAllProducts(){
        return (ArrayList<Product>) productRepository.findAll();
    }

    public Product getByName(String name){
        return productRepository.findByName(name).orElseThrow();
    }

    public void deleteProduct(String name){
        productRepository.deleteById(productRepository.findByName(name).orElseThrow().getId());
    }


    public boolean updateName(String name,String newName){
        Product prod = productRepository.findByName(name).orElseThrow();
            if (validate(newName,prod.getDescription())) {
                prod.setName(newName);
                productRepository.save(prod);
                return true;
            }else return false;

    }

    public boolean updateDescription(String name,String newDescription){
        Product prod = productRepository.findByName(name).orElseThrow();
            if (validate(name,newDescription)) {
                prod.setDescription(newDescription);
                productRepository.save(prod);
                return true;
            }else return false;
    }

    public boolean updatePrice(String name,double newPrice){
        Product prod = productRepository.findByName(name).orElseThrow();
            if (validate(name, prod.getDescription(),newPrice)) {
                prod.setPrice(newPrice);
                productRepository.save(prod);
                return true;
            }else return false;
    }

    public void updateAvailability(String name,boolean availability){
        Product prod = productRepository.findByName(name).orElseThrow();
        prod.setAvailability(availability);
        productRepository.save(prod);

    }


    public ArrayList<Product> filterProduct(String param){
        return (ArrayList<Product>) productRepository.findAll().stream().filter(user -> user.getName().toLowerCase().contains(param.toLowerCase()))
                .collect(Collectors.toList());
    }

    public ArrayList<Product> filterProductByPrice(Double price,String param){
        if (param.isBlank()) {
            return (ArrayList<Product>) productRepository.findAll().stream().filter(user ->  price.equals(user.getPrice())).collect(Collectors.toList());
        }else return myFilterProductByPrice(price,param);
    }

    private ArrayList<Product> myFilterProductByPrice(double price,String param){
        ArrayList<Product> products = (ArrayList<Product>) productRepository.findAll();
        if (param.equals("<")) {
            return (ArrayList<Product>) products.stream().filter(product -> product.getPrice() < price)
                    .collect(Collectors.toList());
        } else if (param.equals(">")) {
            return (ArrayList<Product>) products.stream().filter(product -> product.getPrice() > price)
                    .collect(Collectors.toList());
        }else return products;
    }

    public ArrayList<Product> filterByAvailability(boolean param){
        return (ArrayList<Product>) productRepository.findAll().stream().filter(product -> product.isAvailability() == param).collect(Collectors.toList());
    }

    public ArrayList<Product> sortProduct(String tmp){
        if (tmp.equals("name")) {
            return (ArrayList<Product>) productRepository.findAll().stream()
                    .sorted(Comparator.comparing(Product::getName))
                    .collect(Collectors.toList());
        } else if (tmp.equals("price")) {
            return (ArrayList<Product>) productRepository.findAll().stream()
                    .sorted(Comparator.comparing(Product::getPrice))
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<>(productRepository.findAll());
        }

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
