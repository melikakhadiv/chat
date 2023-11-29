package com.chat.model.service;

import com.chat.model.entity.Chat;
import com.chat.model.service.impl.ServiceImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
@Named
public class ChatService implements ServiceImpl<Chat, Long> {
    @PersistenceContext(unitName = "mft")
    private EntityManager entityManager;

    @Transactional
    public Chat save(Chat chat) throws Exception {
        chat.setActive(true);
        chat.setTimeStamp(LocalDateTime.now());
        entityManager.persist(chat);
        return chat;
    }

    @Transactional
    public Chat edit(Chat chat) throws Exception {
        entityManager.merge(chat);
        return chat;
    }

    @Transactional
    public Chat remove(Long id) throws Exception {
        Chat chat = findById(id);
        if ( chat!= null){
        entityManager.remove(chat);
        return chat;}
        else {
            return null;
        }
    }

    @Transactional
    public List<Chat> findAll() {
        Query query = entityManager.createQuery("select oo from chatEntity oo");
        return query.getResultList();
    }


    @Transactional
    public List<Chat> findByUsername(String username) {
        Query query = entityManager.createQuery("select oo from chatEntity oo where oo.username=:username");
        query.setParameter("username",username);
        return query.getResultList();
    }

    @Transactional
    public List<Chat> findBySenderAndReceiver(String sender , String receiver) {
        Query query = entityManager.createNamedQuery("Chat.FindBySenderAndReceiver")
        .setParameter("sender",sender)
        .setParameter("receiver",receiver);
        return query.getResultList();
    }

    @Override
    public Chat findById(Long id) throws Exception {
        return entityManager.find(Chat.class, id);
    }


}