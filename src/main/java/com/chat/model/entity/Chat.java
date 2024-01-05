package com.chat.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import java.time.LocalDateTime;


@NoArgsConstructor
@Getter
@Setter
@SuperBuilder


@Entity(name = "chatEntity")
@Table(name = "chat_tbl")
@NamedQueries(
        {@NamedQuery(name = "Chat.FindBySenderAndReceiver", query = "select oo from chatEntity oo where (oo.sender.username=:sender and  oo.receiver.username=:receiver) or (oo.sender.username=:receiver and  oo.receiver.username=:sender) order by oo.timeStamp"),
                @NamedQuery(name = "Chat.FindByUsername", query = "select oo from chatEntity oo where oo.sender.username=:username or oo.receiver.username=:username")
        })
public class Chat extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "c_message")
    private String message;

    @JoinColumn(name = "c_sender_id")
    @OneToOne
    private User sender;

    @JoinColumn(name = "c_receiver_id")
    @OneToOne
    private User receiver;

    @Column(name = "c_date")
    private LocalDateTime timeStamp;

    @Column(name="c_status")
    private boolean received;
}
