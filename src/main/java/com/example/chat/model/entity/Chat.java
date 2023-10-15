package com.example.chat.model.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder


@Entity(name = "chatEntity")
@Table(name = "chat_tbl")
public class Chat extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "c_message")
    private String message;

    @Column(name = "c_sender")
    @OneToOne
    private User sender;

    @Column(name = "c_receiver")
    @OneToMany
    private List<User> receiver;

    @Column(name = "c_date")
    private LocalDateTime date;
}
