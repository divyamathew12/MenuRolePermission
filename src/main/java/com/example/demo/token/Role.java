package com.example.demo.token;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "role")
public class Role {
    @Id
    @Column (name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private  Long id;
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
}
