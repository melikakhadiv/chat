package com.example.chat.model.service;

import com.example.chat.model.entity.Chat;
import com.example.chat.model.service.impl.ServiceImpl;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

import java.util.List;

@RequestScoped
@Named
public class chatService implements ServiceImpl<Chat, Long> {
    @PersistenceContext(unitName = "mft")
    private EntityManager entityManager;

    @Transactional
    public Chat save(Chat chat) throws Exception {
        entityManager.persist(chat);
        return chat;
    }

    public Chat edit(Chat chat) throws Exception {
        entityManager.merge(chat);
        return chat;
    }

    public Chat remove(Long id) throws Exception {
        Chat chat = entityManager.find(Chat.class, id);
        entityManager.remove(chat);
        return chat;
    }

    public List<Chat> findAll() {
        Query query = entityManager.createQuery("select oo from chatEntity oo");
        return query.getResultList();
    }

    @Override
    public Chat findById(Long id) throws Exception {
        return entityManager.find(Chat.class, id);
    }


}