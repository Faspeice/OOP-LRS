package ru.omgtu.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Product {
    private Long id;
    private BigDecimal price;
    private String name;
    private String description;
    private ProductStatus status;
    private String img;
}
