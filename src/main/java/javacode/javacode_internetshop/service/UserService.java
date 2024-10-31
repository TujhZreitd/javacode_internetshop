package javacode.javacode_internetshop.service;

import javacode.javacode_internetshop.model.User;

import java.util.List;

public interface UserService {
    User create(User user);
    User update(User user);
    User findById(int id);
    void deleteById(int id);
    List<User> findAll();
}
