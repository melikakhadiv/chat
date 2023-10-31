package com.example.chat.model.service;


import com.example.chat.model.entity.User;
import com.example.chat.model.entity.UserRoles;
import com.example.chat.model.service.impl.ServiceImpl;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

import java.util.List;

@RequestScoped
public class UserRolesService implements ServiceImpl<UserRoles, String> {


    @PersistenceContext(unitName = "mft")
    private EntityManager entityManager;

    @Override
    @Transactional

    public UserRoles save(UserRoles userRoles) throws Exception {
        entityManager.persist(userRoles);
        return userRoles;
    }

    @Override
    @Transactional

    public UserRoles edit(UserRoles userRoles) throws Exception {
        entityManager.merge(userRoles);
        return userRoles;
    }

    @Override
    @Transactional

    public UserRoles remove(String username) throws Exception {
        UserRoles userRoles = entityManager.find(UserRoles.class, username);
        entityManager.remove(username);
        return userRoles;
    }

    @Override
    @Transactional

    public List<UserRoles> findAll() throws Exception {
        Query query = entityManager.createQuery("select oo from userRolesEntity oo");
        return query.getResultList();
    }

    @Override
    @Transactional

    public UserRoles findById(String username) throws Exception {
        return entityManager.find(UserRoles.class, username);
    }

}

