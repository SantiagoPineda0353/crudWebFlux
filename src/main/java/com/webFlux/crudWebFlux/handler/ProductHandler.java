package com.webFlux.crudWebFlux.handler;

import com.webFlux.crudWebFlux.entity.Product;
import com.webFlux.crudWebFlux.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@RequiredArgsConstructor
public class ProductHandler {

    private final ProductService productService;

    public Mono<ServerResponse> findAll(ServerRequest  request) {
        Flux<Product> products = productService.findAll();
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(products, Product.class);
    }

    public Mono<ServerResponse> findById(ServerRequest  request) {
        int id= Integer.parseInt(request.pathVariable("id"));
        Mono<Product> product = productService.findById(id);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(product, Product.class);
    }

    public Mono<ServerResponse> save(ServerRequest  request) {
        Mono<Product> product= request.bodyToMono(Product.class);
        return product.flatMap(p->ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(productService.save(p), Product.class));
    }

    public Mono<ServerResponse> update(ServerRequest  request) {
        int id= Integer.parseInt(request.pathVariable("id"));
        Mono<Product> product= request.bodyToMono(Product.class);
        return product.flatMap(p->ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(productService.update(id,p), Product.class));
    }

    public Mono<ServerResponse> delete(ServerRequest  request) {
        int id= Integer.parseInt(request.pathVariable("id"));
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(productService.deleteById(id), Product.class);
    }
}
