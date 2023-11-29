package com.chat.model.service;

import com.chat.model.entity.Attachment;
import com.chat.model.service.impl.ServiceImpl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.List;

@ApplicationScoped
public class AttachmentService implements ServiceImpl<Attachment, Long> {
    @PersistenceContext(unitName = "mft")
    private EntityManager entityManager;

    @Override
    public Attachment save(Attachment attachment) throws Exception {
        System.out.println("attachment service");
        System.out.println(attachment);
        try {
            entityManager.persist(attachment);
            return attachment;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Attachment edit(Attachment attachment) throws Exception {
        entityManager.merge(attachment);
        return attachment;
    }

    @Override
    public Attachment remove(Long id) throws Exception {
        Attachment attachment = findById(id);
        if (attachment != null) {
            attachment.setActive(false);
            return entityManager.merge(attachment);
        } else {
            return null;
        }
    }

    @Override
    public List<Attachment> findAll() throws Exception {
        Query query = entityManager.createQuery("select oo from attachmentEntity  oo");
        return query.getResultList();
    }

    public Attachment findById(Long id) throws Exception {
        return entityManager.find(Attachment.class, id);
    }
}
