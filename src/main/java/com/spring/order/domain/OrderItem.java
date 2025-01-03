package com.spring.order.domain;

import com.spring.order.domain.item.Item;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_item")
@Getter
@NoArgsConstructor
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "oreder_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "oreder_id")
    private Order order;

    private int orderPrice;
    private int count;

    public void setOrder(Order order) {
        this.order = order;
    }

    public OrderItem(Item item, int Price, int count) {
        this.item = item;
        this.orderPrice = Price;
        this.count = count;
    }
    //비즈니스 로직
    public void cancel() {
        getItem().addStockQuantity(count);
    }

    public int getTotalPrice() {
        return getOrderPrice()*getCount();
    }

}
