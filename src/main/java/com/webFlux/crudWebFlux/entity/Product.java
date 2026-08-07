package com.webFlux.crudWebFlux.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("product")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {

    @Id
    private Integer id;

    private String name;

    private Float price;
}
