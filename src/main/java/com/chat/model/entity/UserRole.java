package com.chat.model.entity;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor

@Entity(name ="UserRoleEntity")
@Table(name = "user_role")
@ApplicationScoped
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "u_username" , length = 20)
    private String username;

    @Column(name = "role_name" , length = 20)
    private String roleName;

}
