package com.webFlux.crudWebFlux.controller;

import com.webFlux.crudWebFlux.entity.Product;
import com.webFlux.crudWebFlux.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/product")
@Slf4j
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public Flux<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Product> findById(@PathVariable Integer id) {
        return productService.findById(id);
    }

    @PostMapping
    public Mono<Product> save(@RequestBody Product product) {
        return productService.save(product);
    }

    @PutMapping("/{id}")
    public Mono<Product> update(@PathVariable ("id") int id, @RequestBody Product product) {
        return productService.update(id, product);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteById(@PathVariable Integer id) {
        return productService.deleteById(id);
    }
}
