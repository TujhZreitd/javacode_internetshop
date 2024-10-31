package javacode.javacode_internetshop.repository;

import javacode.javacode_internetshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
