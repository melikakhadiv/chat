package com.example.chat.model.service;


import com.example.chat.model.entity.UserRoles;
import com.example.chat.model.service.impl.ServiceImpl;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@RequestScoped
public class UserRolesService implements ServiceImpl<UserRoles,String> {


    @PersistenceContext(unitName = "mft")
    private EntityManager entityManager;

    @Override
    public UserRoles save(UserRoles userRoles) throws Exception {
        entityManager.persist(userRoles);
        return userRoles;
    }

    @Override
    public UserRoles edit(UserRoles userRoles) throws Exception {
        return null;
    }

    @Override
    public UserRoles remove(String id) throws Exception {
        return null;
    }

    @Override
    public List<UserRoles> findAll() throws Exception {
        return null;
    }

    @Override
    public UserRoles findById(String id) throws Exception {
        return null;
    }
}
