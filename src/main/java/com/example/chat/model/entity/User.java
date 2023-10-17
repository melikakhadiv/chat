package com.example.chat.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;
@Getter
@Setter
@SuperBuilder

@Entity(name = "userEntity")
@Table(name = "chat_user_tbl")
@NamedQueries({
        @NamedQuery(name ="User.FindAllUsernames" , query = "select oo.username from userEntity oo where oo.privateAccount=false ") ,
        @NamedQuery(name ="User.FindByUsername" , query = "select oo from userEntity oo where oo.username=:username") ,
        @NamedQuery(name ="User.FindByUsernameAndPassWord" , query = "select oo from userEntity oo where oo.username=:username and  oo.password=:password") ,
        @NamedQuery(name = "User.FindPrivateAccount" , query = "select oo from userEntity oo where oo.privateAccount=true"),
        @NamedQuery(name = "User.FindByPublicAccount" , query = "select oo from userEntity oo where oo.privateAccount=false")})

public class User extends Base{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "u_username" , length = 20)
    @Pattern(regexp = "^[A-Za-z]{10,30}$", message = "Invalid UserName")
    private String username;

    @Column(name = "u_password" , length = 20)
    @Pattern(regexp = "^[A-Za-z]{8,30}$", message = "Invalid Password")
    private String password;

    @Column(name = "u_nickname" , length = 20)
    @Pattern(regexp = "^[A-Za-z]{10,30}$", message = "Invalid Nickname")
    private String nickname;

    @Column(name = "u_firstname" , length = 20)
    @Pattern(regexp = "^[A-Za-z]{10,30}$", message = "Invalid Firstname")
    private String firstname;

    @Column(name = "u_lastname" , length = 20)
    @Pattern(regexp = "^[A-Za-z]{10,30}$", message = "Invalid Lastname")
    private String lastname;

    @ManyToOne
    @JoinColumn(name = "u_role")
    private Role role;

    @Column(name = "u_privateAccount" )
    private boolean privateAccount;

    @OneToOne
    @JoinColumn(name = "u_photo_id")
    private Attachment photo;

    @OneToMany(mappedBy = "sender")
    private List<Chat> chatSender;

    @OneToMany(mappedBy = "receiver")
    private List<Chat> chatReceiver;

}