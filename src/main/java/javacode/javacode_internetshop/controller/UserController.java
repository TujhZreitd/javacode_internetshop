package javacode.javacode_internetshop.controller;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import javacode.javacode_internetshop.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javacode.javacode_internetshop.service.UserService;
import javacode.javacode_internetshop.view.Views;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;
    public UserController(UserService simpleUserService) {
        this.userService = simpleUserService;
    }

    @GetMapping
    @JsonView(Views.UserSummary.class)
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    @JsonView(Views.UserDetails.class)
    public ResponseEntity<User> findById(@PathVariable int id) {
        return ResponseEntity.ok(userService.findById(id));

    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@Valid @RequestBody User user) {
        userService.update(user);
        return ResponseEntity.ok("Operation update complete");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        userService.deleteById(id);
        return ResponseEntity.ok("Operation update complete");
    }

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@Valid @RequestBody User user) {
        userService.create(user);
        return ResponseEntity.ok("Operation create complete");
    }



}
