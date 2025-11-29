package com.level.up.levelupgamer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;

    @Column(nullable = false, length = 40)
    private String username;

    @Column(nullable = false, unique = true, length = 40)
    private String email;

    @Column(nullable = false, length = 60)
    private String password;

    @Column(nullable = false, length = 20)
    private String role = "USER";  

    @JsonIgnore
    @OneToOne(mappedBy = "user")
    private Cart cart;
}
