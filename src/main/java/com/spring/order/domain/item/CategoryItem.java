package com.spring.order.domain.item;

import com.spring.order.domain.Category;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class CategoryItem {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;
}
