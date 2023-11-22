package com.chat.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder

@NamedQueries(@NamedQuery(name = "Role.FindByName", query = "select oo from roleEntity oo where oo.role=:role"))

@Entity(name="roleEntity")
@Table(name="chat_role_tbl")

public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="r_name",length = 30, unique = true)
    @Pattern(regexp = "^[A-Za-z\\s]{2,30}$", message = "Invalid Role")
    private String role;
}
