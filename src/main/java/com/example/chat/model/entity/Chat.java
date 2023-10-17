package com.example.chat.model.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor

@Entity(name = "chatEntity")
@Table(name = "chat_tbl")

@NamedQueries(
        @NamedQuery(name = "findChatByUserName", query = "select oo from chatEntity oo where oo.sender.username=:username or receiver.username=:username")
)

public class Chat extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "c_message")
    private String message;

    @JoinColumn(name = "c_sender")
    @ManyToOne
    private User sender;

    @JoinColumn(name = "c_receiver")
    @ManyToOne
    private User receiver;

    @Column(name = "c_date")
    private LocalDateTime date;
}
