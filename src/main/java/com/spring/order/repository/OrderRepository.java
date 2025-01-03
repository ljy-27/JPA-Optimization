package com.spring.order.repository;

import com.spring.order.domain.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o from Order o join fetch o.member m join fetch o.delivery d")
    List<Order> findAllWithMemberDelivery();

    @Query(value = "select o from Order o join fetch o.member m join fetch o.delivery d", countQuery = "select count(o.id) from Order o")
    Page<Order> findAllWithMemberDeliveryItem(Pageable pageable);

}
