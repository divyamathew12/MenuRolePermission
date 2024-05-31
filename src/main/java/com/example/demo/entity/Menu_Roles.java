package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Setter
@Getter
@Table(name = "menu_roles")
@NoArgsConstructor
public class Menu_Roles {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long menu_id;
    Long role_id;

    @OneToMany(mappedBy = "menu_roles", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ButtonPermission> buttonPermission;


}
