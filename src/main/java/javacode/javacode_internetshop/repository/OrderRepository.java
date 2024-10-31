package javacode.javacode_internetshop.repository;

import javacode.javacode_internetshop.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
