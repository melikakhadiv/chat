package com.chat.model.service;


import com.chat.model.service.impl.ServiceImpl;
import com.chat.model.entity.UserRole;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

import java.util.List;

@RequestScoped
public class UserRoleService implements ServiceImpl<UserRole, String> {

    @PersistenceContext(unitName = "mft")
    private EntityManager entityManager;

    @Override
    @Transactional
    public UserRole save(UserRole UserRole) throws Exception {
        entityManager.persist(UserRole);
        return UserRole;
    }

    @Override
    @Transactional
    public UserRole edit(UserRole UserRole) throws Exception {
        entityManager.merge(UserRole);
        return UserRole;
    }

    @Override
    @Transactional
    public UserRole remove(String username) throws Exception {
        UserRole UserRole = entityManager.find(UserRole.class, username);
        entityManager.remove(username);
        return UserRole;
    }

    @Override
    @Transactional
    public List<UserRole> findAll() throws Exception {
        Query query = entityManager.createQuery("select oo from UserRoleEntity oo");
        return query.getResultList();
    }

    @Override
    @Transactional
    public UserRole findById(String username) throws Exception {
        return entityManager.find(UserRole.class, username);
    }

}

