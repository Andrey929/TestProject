package org.example.mytestproject.Repositories;

import org.example.mytestproject.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    @Override
    Optional<Product> findById(Long aLong);
    Optional<Product> findByName(String name);


}
