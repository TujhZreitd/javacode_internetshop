package javacode.javacode_internetshop.model;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javacode.javacode_internetshop.view.Views;

import jakarta.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.UserSummary.class)
    private int id;

    @NotNull
    @JsonView(Views.UserSummary.class)
    private String name;

    @JsonView(Views.UserSummary.class)
    @NotNull(message = "Email cannot be null")
    @Email(message = "Email should be valid")
    private String email;

    @JsonView(Views.UserDetails.class)
    @OneToMany(mappedBy = "user")
    private List<Order> orders;
}
