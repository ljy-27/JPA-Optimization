package com.spring.order.service;

import com.spring.order.domain.item.Item;
import com.spring.order.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public void save(Item item) {
        itemRepository.save(item);
    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public Item findByName(String name) {
        return itemRepository.findByName(name);
    }

    public Item findOne(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Not Found"));
    }

}
