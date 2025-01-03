package com.spring.order.domain.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@DiscriminatorValue("B")
@NoArgsConstructor
public class Book extends Item{

    private String author;
    private String isbn;

    public Book(String name, int price, int stockQuantity) {
        super(name, price, stockQuantity);
    }
}
