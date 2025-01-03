//package com.spring.order;
//
//import com.spring.order.domain.*;
//import com.spring.order.domain.item.Book;
//import com.spring.order.service.ItemService;
//import com.spring.order.service.MemberService;
//import com.spring.order.service.OrderService;
//import jakarta.annotation.PostConstruct;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.Random;
//
//@Component
//@RequiredArgsConstructor
//public class InitDB {
//
//    private final InitService initService;
//
//    @PostConstruct
//    public void init() {
//        initService.dbInit1();
//        initService.dbInit2();
//    }
//
//    @Component
//    @Transactional
//    @RequiredArgsConstructor
//    static class InitService {
//        private final MemberService memberService;
//        private final ItemService itemService;
//        private final OrderService orderService;
//
//        public void dbInit1() {
//            for (int i=1; i<=100; i++) {
//                Member member = createMember("user"+i, "서울", String.valueOf(i), String.valueOf(1000*i+100*i+i));
//                memberService.save(member);
//            }
//            for (int i=1; i<=500; i++) {
//                Book book = createBook("Book"+i, 10000, 500);
//                itemService.save(book);
//            }
//        }
//        public void dbInit2() {
//            Random random = new Random();
//            for (int i=1; i<=100; i++) {
//                int count1 = random.nextInt(10)+1;
//                int count2 = random.nextInt(10)+1;
//                int count3 = random.nextInt(10)+1;
//                int bookNumber1 = random.nextInt(500)+1;
//                int bookNumber2 = random.nextInt(500)+1;
//                int bookNumber3 = random.nextInt(500)+1;
//                OrderItem orderItem1 = new OrderItem(itemService.findByName("Book" + bookNumber1), 10000 * count1, count1);
//                OrderItem orderItem2 = new OrderItem(itemService.findByName("Book" + bookNumber2), 10000 * count2, count2);
//                OrderItem orderItem3 = new OrderItem(itemService.findByName("Book" + bookNumber3), 10000 * count3, count3);
//                List<Member> findMember = memberService.findByName("user" + i);
//                Member member = findMember.getFirst();
//                Delivery delivery = createDelivery(member);
//                Order order = new Order(member, delivery, orderItem1, orderItem2, orderItem3);
//                orderService.save(order);
//            }
//        }
//
//        private Member createMember(String name, String city, String street, String zipcode) {
//            Address address = new Address(city, street, zipcode);
//            return new Member(name, address);
//        }
//
//        private Book createBook(String name, int price, int quantity) {
//            return new Book(name, price, quantity);
//        }
//
//        private Delivery createDelivery(Member member) {
//            return new Delivery(member.getAddress());
//        }
//    }
//}
