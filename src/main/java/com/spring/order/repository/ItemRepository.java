package com.spring.order.repository;

import com.spring.order.domain.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Item findByName(String name);
}
