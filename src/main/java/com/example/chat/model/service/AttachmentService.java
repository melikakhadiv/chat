package com.example.chat.model.service;

import com.example.chat.model.entity.Attachment;
import com.example.chat.model.service.impl.ServiceImpl;

import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.List;

@RequestScoped
public class AttachmentService implements ServiceImpl<Attachment, Long> {
    @PersistenceContext(unitName = "mft")
    private EntityManager entityManager;

    @Override
    public Attachment save(Attachment attachment) throws Exception {
        entityManager.persist(attachment);
        return attachment;
    }

    @Override
    public Attachment edit(Attachment attachment) throws Exception {
        entityManager.merge(attachment);
        return attachment;
    }

    @Override
    public Attachment remove(Long id) throws Exception {
        Attachment attachment = findById(id);
        attachment.setActive(false);
        return entityManager.merge(attachment);
    }

    @Override
    public List<Attachment> findAll() throws Exception {
        Query query = entityManager.createQuery("select oo from attachmentEntity  oo");
        return query.getResultList();
    }

    public  Attachment findById(Long id) throws Exception {
        return entityManager.find(Attachment.class, id);
    }
}
