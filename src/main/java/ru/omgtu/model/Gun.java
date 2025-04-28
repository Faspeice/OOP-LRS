package ru.omgtu.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.omgtu.repo.IdentifiedByPK;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class Gun extends Product implements IdentifiedByPK<Long> {
    private String model;
    private String producer;

    public Gun(Long id, BigDecimal price,String name,String description,ProductStatus status,String model,String producer,String img) {
        super(id,price,name,description,status,img);
        this.producer = producer;
        this.model = model;
    }

    @Override
    public Long getPK() {
        return getId();
    }
}
