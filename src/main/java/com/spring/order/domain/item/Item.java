package com.spring.order.domain.item;

import com.spring.order.exception.NotEnoughStockException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@DiscriminatorColumn(name = "dtype")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NoArgsConstructor
public abstract class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @OneToMany(mappedBy = "item")
    private List<CategoryItem> categoryItems = new ArrayList<>();

    //비즈니스 로직
    public void addStockQuantity(int quantity) {
        stockQuantity += quantity;
    }

    public void removeStockQuantity(int quantity) {
        if (stockQuantity - quantity < 0) {
            throw new NotEnoughStockException("재고는 음수가 될 수 없습니다.");
        }
        stockQuantity -= quantity;
    }

    public Item(String name, int price, int stockQuantity) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

}
