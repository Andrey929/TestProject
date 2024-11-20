package org.example.mytestproject.Data;

import org.example.mytestproject.Model.Product;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductData {
    protected static HashMap<Long,Product> products;
    public static Long idCounter;
    static {
        products= new HashMap<>();
        idCounter = 0L;
    }

    public ArrayList<Product> getAll(){
        return new ArrayList<>(products.values());
    }

    public void addProduct(Product product){
        product.setId(idCounter);
        products.put(idCounter,product);
        idCounter++;
    }
    public Product getProductById(Long id){
        return products.getOrDefault(id, null);
    }
    public Product getProductByName(String name){
        for (long i = 0; i < products.size(); i++) {
            if(products.get(i).getName().equals(name)){
                return products.get(i);
            }
        }
        return null;
    }

    public void deleteProductById(Long id){
        if (products.containsKey(id)){
            products.remove(id);
            System.out.println("Delete successfully");
        }else {
            System.out.println("Product don't found");
        }

    }
    public boolean deleteProductByName(String name){
        Product prod= getProductByName(name);
        if(prod != null){
            products.remove(prod.getId());
            System.out.println("Delete successfully");
            return true;
        }else {
            System.out.println("Product don't found");
            return false;
        }

    }

    public boolean updateProduct(String name,Product newProd){
        Product prod= getProductByName(name);
        if(prod != null){
            products.replace(prod.getId(),prod,newProd);
            System.out.println("Update successfully");
            return true;
        }else {
            System.out.println("Product don't found");
            return false;
        }
    }


}
