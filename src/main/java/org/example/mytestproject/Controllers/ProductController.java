package org.example.mytestproject.Controllers;

import lombok.RequiredArgsConstructor;
import org.example.mytestproject.DTO.ProductDTO;
import org.example.mytestproject.Model.Product;
import org.example.mytestproject.Service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RequiredArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService service;
    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestBody ProductDTO prodDTO){
        try {
            if (service.createProduct(prodDTO.getName(),
                    prodDTO.getDescription(), prodDTO.getPrice(),
                    prodDTO.isAvailability())) {
                return ResponseEntity.ok(prodDTO);
            } else return ResponseEntity.badRequest().body("Error with create product");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }
    @GetMapping("/getProduct/{name}")
    public ResponseEntity<?> getProduct(@PathVariable String name){
        return ResponseEntity.ok(service.getByName(name));
    }

    @GetMapping("/getAllProduct")
    public ResponseEntity<?> getAllProduct(){
        return ResponseEntity.ok(service.getAllProducts());
    }

    @GetMapping("/delete/{name}")
    public void deleteProduct(@PathVariable String name){
        service.deleteProduct(name);
    }

    @PostMapping("/update/name")
    public ResponseEntity<?> updateProductName(@RequestBody ProductDTO productDTO){
            try {
                service.updateName(productDTO.getName(),productDTO.getNewName());
                return ResponseEntity.ok(service.getByName(productDTO.getNewName()));
            }catch (Exception e){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(e.getMessage());
            }
    }

    @PostMapping("/update/description")
    public ResponseEntity<?> updateProductDescription(@RequestBody ProductDTO productDTO){
        try {
            service.updateDescription(productDTO.getName(),productDTO.getDescription());
            return ResponseEntity.ok(service.getByName(productDTO.getName()));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @PostMapping("/update/price")
    public ResponseEntity<?> updateProductPrice(@RequestBody ProductDTO productDTO){
        try {
            service.updatePrice(productDTO.getName(),productDTO.getPrice());
            return ResponseEntity.ok(service.getByName(productDTO.getName()));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @PostMapping("/update/availability")
    public ResponseEntity<?> updateProductAvailability(@RequestBody ProductDTO productDTO){
        try {
            service.updateAvailability(productDTO.getName(),productDTO.isAvailability());
            return ResponseEntity.ok(service.getByName(productDTO.getNewName()));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

}
