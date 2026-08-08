package com.webFlux.crudWebFlux.router;

import com.webFlux.crudWebFlux.handler.ProductHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@Slf4j
public class ProductRouter {

    private static final String PATH = "/product";
    @Bean
    public RouterFunction<ServerResponse> routes(ProductHandler handler) {
        return RouterFunctions.route()
                .GET(PATH,handler::findAll)
                .GET(PATH + "/{id}",handler::findById)
                .POST(PATH, handler::save)
                .PUT(PATH + "/{id}",handler::update)
                .DELETE(PATH +"/{id}",handler::delete)
                .build();
    }
}
