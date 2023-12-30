package com.chat.model.service;


import com.chat.model.service.impl.ServiceImpl;
import com.chat.model.entity.UserRole;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Slf4j
public class UserRoleService implements ServiceImpl<UserRole, Integer> {

    @PersistenceContext(unitName = "mft")
    private EntityManager entityManager;

    @Override
    @Transactional
    public UserRole save(UserRole userRole) throws Exception {
        entityManager.persist(userRole);
        log.info("UserRole-Service-Saved");
        return userRole;
    }

    @Override
    @Transactional
    public UserRole edit(UserRole userRole) throws Exception {
        entityManager.merge(userRole);
        log.info("UserRole-Service-Edited");
        return userRole;
    }

    @Override
    @Transactional
    public UserRole remove(Integer id) throws Exception {
        UserRole userRole = findById(id);
        if (userRole != null) {
            entityManager.remove(id);
            log.info("UserRole-Service-Removed");
            return userRole;
        } else {
            log.error("UserRole-Service-NotFound");
            return null;
        }
    }

    @Override
    @Transactional
    public List<UserRole> findAll() throws Exception {
        TypedQuery<UserRole> query = entityManager.createQuery("select oo from UserRoleEntity oo" , UserRole.class);
        log.info("UserRole-Service-FindAll");
        return query.getResultList();
    }

    @Override
    @Transactional
    public UserRole findById(Integer id) throws Exception {
        Optional<UserRole> userRole = Optional.ofNullable(entityManager.find(UserRole.class , id));
        log.info("UserRole-Service-FindById");
        return userRole.isPresent() ? userRole.get() : null;
    }

}

