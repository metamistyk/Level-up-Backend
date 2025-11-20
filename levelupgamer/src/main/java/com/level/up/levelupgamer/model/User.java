package com.level.up.levelupgamer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Column(nullable = false, length = 40)
    private String email;

    @Column(nullable = false, length = 40)
    private String password;

    // Para evitar recursión infinita en JSON
    @JsonIgnore
    @OneToOne(mappedBy = "user")
    private Cart cart;
}
