package com.example.chat.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor

@Entity(name ="userRolesEntity")
@Table(name = "user_roles")
public class UserRoles {
    @Id
    @Column(name = "u_username" , length = 20)
    private String username;

    @Column(name = "role_name" , length = 20)
    private String roleName;

}
