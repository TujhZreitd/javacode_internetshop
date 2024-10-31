package javacode.javacode_internetshop.service;

import lombok.AllArgsConstructor;
import javacode.javacode_internetshop.model.Order;
import org.springframework.stereotype.Service;
import javacode.javacode_internetshop.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class SimpleOrderService implements OrderService {

    private final OrderRepository orderRepository;
    @Override
    public Order create(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Optional<Order> findById(int id) {
        return orderRepository.findById(id);
    }

    @Override
    public Order update(Order order) {
        Optional<Order> orderOptional = orderRepository.findById(order.getId());
        if (orderOptional.isEmpty()) {
            throw new RuntimeException();
        }
        Order orderNew = orderOptional.get();
        orderNew.setName(order.getName());
        orderNew.setAmount(order.getAmount());
        orderNew.setStatus(order.getStatus());
        return orderRepository.save(orderNew);
    }

    @Override
    public boolean deleteById(int id) {
        boolean result;
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (orderOptional.isEmpty()) {
            result = false;
        } else {
            orderRepository.deleteById(id);
            result = true;
        }
        return result;
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }
}
