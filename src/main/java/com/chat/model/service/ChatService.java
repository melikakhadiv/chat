package com.chat.model.service;

import com.chat.controller.exception.NoContentException;
import com.chat.controller.session.SessionManager;
import com.chat.model.entity.Chat;
import com.chat.model.service.impl.ServiceImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Named
@Slf4j
public class ChatService implements ServiceImpl<Chat, Long> {
    @PersistenceContext(unitName = "mft")
    private EntityManager entityManager;

    @Transactional
    public Chat save(Chat chat) throws Exception {
        chat.setActive(true);
        chat.setTimeStamp(LocalDateTime.now());
        entityManager.persist(chat);
        log.info("Chat-Service-Saved");
        return chat;
    }

    @Transactional
    public Chat edit(Chat chat) throws Exception {
        entityManager.merge(chat);
        log.info("Chat-Service-Edited");
        return chat;
    }

    @Transactional
    public Chat remove(Long id) throws Exception {
        Chat chat = findById(id);
        if (chat != null) {
            entityManager.remove(chat);
            log.info("Chat-Service-Removed");
            return chat;
        } else {
            log.error("Chat-Service-Remove-NotFound");
            throw new NoContentException();
        }
    }

    @Transactional
    public List<Chat> findAll() {
        TypedQuery<Chat> query = entityManager.createQuery("select oo from chatEntity oo" , Chat.class);
        log.info("Chat-Service-FindAll");
        return query.getResultList();
    }


    @Transactional
    public List<Chat> findBySenderAndReceiver(String sender, String receiver) throws Exception {
        TypedQuery<Chat> query = entityManager.createNamedQuery("Chat.FindBySenderAndReceiver", Chat.class)
                .setParameter("sender", sender)
                .setParameter("receiver", receiver);
        List<Chat> chatList = query.getResultList();
        for (Chat chat : chatList) {
            if (!chat.isReceived() &&
                    SessionManager.getOnlineUsers().contains(chat.getReceiver().getUsername())) {
                log.info("Chat-Service-Received");
                chat.setReceived(true);
                edit(chat);
            }
        }
        log.info("Chat-Service-FindBySenderAndReceiver");
        return chatList;

    }

    @Transactional
    public List<Chat> findByUsername(String username) {
        TypedQuery<Chat> query = entityManager.createNamedQuery("Chat.FindByUsername", Chat.class)
                .setParameter("username", username);
        log.info("Chat-Service-FindByUsername");
        return query.getResultList();
    }

    @Override
    public Chat findById(Long id) throws Exception {
        Optional<Chat> chat = Optional.ofNullable(entityManager.find(Chat.class, id));
        log.info("Chat-Service-FindById");
        return chat.isPresent() ? chat.get() : null;
    }


}