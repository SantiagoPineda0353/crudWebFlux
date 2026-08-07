package com.webFlux.crudWebFlux.repository;

import com.webFlux.crudWebFlux.entity.Product;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends ReactiveCrudRepository<Product,Integer> {
}
