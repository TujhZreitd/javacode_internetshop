package javacode.javacode_internetshop.service;

import javacode.javacode_internetshop.exception.UserNotFoundException;
import javacode.javacode_internetshop.model.Order;
import javacode.javacode_internetshop.repository.OrderRepository;
import lombok.AllArgsConstructor;
import javacode.javacode_internetshop.model.User;
import org.springframework.stereotype.Service;
import javacode.javacode_internetshop.repository.UserRepository;

import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class SimpleUserService implements UserService {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        Optional<User> optionalUser = userRepository.findById(user.getId());
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException(user.getId());
        }
        User userNew = optionalUser.get();
        userNew.setName(user.getName());
        userNew.setEmail(user.getEmail());
        return userRepository.save(userNew);
    }

    @Override
    public User findById(int id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException(id);
        }
        List<Order> orders = orderRepository.findByUserId(id);
        User user = userOptional.get();
        user.setOrders(orders);
        return user;
    }

    @Override
    public void deleteById(int id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException(id);
        } else {
            userRepository.deleteById(id);
        }
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
