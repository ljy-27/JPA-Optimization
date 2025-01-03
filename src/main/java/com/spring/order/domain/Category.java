package com.spring.order.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Category {

    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

}
