package com.chat.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder

@Entity(name = "userEntity")
@Table(name = "chat_user_tbl")
@NamedQueries({@NamedQuery(name = "User.FindPrivateAccount" , query = "select oo from userEntity oo where oo.privateAccount=true"),
        @NamedQuery(name = "User.FindRoleByUsername" , query = "select oo from userEntity oo where oo.username=:username"),
        @NamedQuery(name = "User.FindPublicAccount" , query = "select oo from userEntity oo where oo.privateAccount=false")})

public class User extends Base{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "u_username" , length = 20)
//    @Pattern(regexp = "^[A-Za-z]{10,30}$", message = "Invalid UserName")
    private String username;

    @Column(name = "u_password" , length = 20)
//    @Pattern(regexp = "^[A-Za-z]{8,30}$", message = "Invalid Password")
    private String password;

    @Column(name = "u_nickname" , length = 20)
//    @Pattern(regexp = "^[A-Za-z]{10,30}$", message = "Invalid Nickname")
    private String nickname;

    @Column(name = "u_firstname" , length = 20)
//    @Pattern(regexp = "^[A-Za-z]{10,30}$", message = "Invalid Firstname")
    private String firstname;

    @Column(name = "u_lastname" , length = 20)
//    @Pattern(regexp = "^[A-Za-z]{10,30}$", message = "Invalid Lastname")
    private String lastname;

    @JoinColumn(name = "u_role_id" )
    @OneToOne
    private Role role;

    @Column(name = "u_privateAccount" )
    private boolean privateAccount;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "u_photo_id")
    private Attachment photo;

    @OneToMany
    @JoinColumn(name = "u_chat_id")
    private List<Chat> chat;

}


