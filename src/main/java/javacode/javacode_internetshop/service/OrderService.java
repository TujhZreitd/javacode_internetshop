package javacode.javacode_internetshop.service;

import javacode.javacode_internetshop.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Order create(Order order);
    Order update(Order order);
    Optional<Order> findById(int id);
    boolean deleteById(int id);
    List<Order> findAll();
}
