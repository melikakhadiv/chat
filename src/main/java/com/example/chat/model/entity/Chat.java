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
@NamedQueries({
        @NamedQuery(name = "chatBetween2Users",query = "select c from chatEntity c " +
                "where ( sender.username = :firstUsername and receiver.username = :secondUsername ) or " +
                "      (receiver.username = :firstUsername and sender.username = :secondUsername) " +
                "  order by date")
}
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
