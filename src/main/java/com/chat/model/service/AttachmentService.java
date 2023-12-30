package com.chat.model.service;

import com.chat.model.entity.Attachment;
import com.chat.model.service.impl.ServiceImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Slf4j
public class AttachmentService implements ServiceImpl<Attachment, Long> {
    @PersistenceContext(unitName = "mft")
    private EntityManager entityManager;

    @Override
    public Attachment save(Attachment attachment) throws Exception {
        entityManager.persist(attachment);
        log.info("Attachment-Service-Saved");
        return attachment;
    }

    @Override
    public Attachment edit(Attachment attachment) throws Exception {
        entityManager.merge(attachment);
        log.info("Attachment-Service-Edited");
        return attachment;
    }

    @Override
    public Attachment remove(Long id) throws Exception {
        Attachment attachment = findById(id);
        if (attachment != null) {
            attachment.setActive(false);
            log.info("Attachment-Service-Removed");
            return entityManager.merge(attachment);
        } else {
            log.error("Attachment-Service-NotFound");
            return null;
        }
    }

    @Override
    public List<Attachment> findAll() throws Exception {
        TypedQuery<Attachment> query = entityManager.createQuery("select oo from attachmentEntity  oo", Attachment.class);
        log.info("Attachment-Service-FindAll");
        return query.getResultList();
    }

    public Attachment findById(Long id) throws Exception {
        Optional<Attachment> attachment = Optional.ofNullable(entityManager.find(Attachment.class, id));
        log.info("Attachment-Service-FindById");
        return attachment.isPresent() ? attachment.get() : null;
    }
}
