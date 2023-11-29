package com.chat.model.service;


import com.chat.model.service.impl.ServiceImpl;
import com.chat.model.entity.UserRole;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

import java.awt.event.WindowListener;
import java.util.List;

@RequestScoped
public class UserRoleService implements ServiceImpl<UserRole, String> {

    @PersistenceContext(unitName = "mft")
    private EntityManager entityManager;

    @Override
    @Transactional
    public UserRole save(UserRole userRole) throws Exception {
        entityManager.persist(userRole);
        return userRole;
    }

    @Override
    @Transactional
    public UserRole edit(UserRole userRole) throws Exception {
        entityManager.merge(userRole);
        return userRole;
    }

    @Override
    @Transactional
    public UserRole remove(String username) throws Exception {
        UserRole userRole = findById(username);
        if (userRole != null) {
            entityManager.remove(username);
            return userRole;
        } else {
            return null;
        }
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

