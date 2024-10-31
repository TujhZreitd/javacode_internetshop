package javacode.javacode_internetshop.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(int id) {
        super("User by id " + id + " not found");
    }
}
