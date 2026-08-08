package com.webFlux.crudWebFlux.service;

import com.webFlux.crudWebFlux.entity.Product;
import com.webFlux.crudWebFlux.exception.CustomException;
import com.webFlux.crudWebFlux.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final static String NF_MESSAGE = "Product not found";
    private final static String NAME_MESSAGE = "Product name not found";
    private final ProductRepository productRepository;

    public Flux<Product> findAll() {
        return productRepository.findAll();
    }

    public Mono<Product> findById(int id) {
        return productRepository.findById(id)
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND,NF_MESSAGE)));
    }

    public Mono<Product> save(Product product) {
        Mono<Boolean> exitsName = productRepository.findByName(product.getName()).hasElement();
        return  exitsName.flatMap(exits->exits ? Mono.error(new CustomException(HttpStatus.BAD_REQUEST,NAME_MESSAGE))
                :productRepository.save(product));
    }

    public Mono<Product> update(int id, Product product) {
        Mono<Boolean> productId = productRepository.findById(id).hasElement();
        Mono<Boolean> findByNameAndId = productRepository.findByNameAndId(product.getName(),product.getId()).hasElement();

        return productId.flatMap(
                existsId->existsId ?
                        findByNameAndId.flatMap(existsName -> existsName ? Mono.error(new CustomException(HttpStatus.BAD_REQUEST,NAME_MESSAGE))
                                :productRepository.save(new Product(id,product.getName(),product.getPrice())))
        : Mono.error(new CustomException(HttpStatus.NOT_FOUND,NF_MESSAGE)));
    }

    public Mono<Void> deleteById(int id) {
        Mono<Boolean> productId = productRepository.findById(id).hasElement();
        return productId.flatMap(exits -> exits ? productRepository.deleteById(id):Mono.error(new CustomException(HttpStatus.NOT_FOUND,NF_MESSAGE)));
    }
}
