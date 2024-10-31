package javacode.javacode_internetshop.model;

import com.fasterxml.jackson.annotation.JsonView;
import javacode.javacode_internetshop.view.Views;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.math.BigDecimal;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.UserDetails.class)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonView(Views.UserDetails.class)
    private String name;

    @JsonView(Views.UserDetails.class)
    private BigDecimal amount;

    @JsonView(Views.UserDetails.class)
    private String status;
}
