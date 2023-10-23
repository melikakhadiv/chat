package com.example.chat.model.service;

import com.example.chat.model.entity.Role;
import com.example.chat.model.service.impl.ServiceImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import jakarta.persistence.PersistenceContext;
import java.util.List;

public class RoleService implements ServiceImpl<Role, Long> {
    @PersistenceContext(unitName = "mft")
    private EntityManager entityManager;

    @Override
    @Transactional
    public Role save(Role role) throws Exception {
        entityManager.persist(role);
        return role;
    }

    @Override
    @Transactional
    public Role edit(Role role) throws Exception {
        entityManager.merge(role);
        return role;
    }

    @Override
    @Transactional
    public Role remove(Long id) throws Exception {
        Role role = findById(id);
        if (role == null) {
            return null;
        } else {
            entityManager.remove(id);
            return role;
        }
    }


    @Override
    public List<Role> findAll() throws Exception {
        Query query = entityManager.createQuery("select oo from roleEntity oo");
        List<Role> roles = query.getResultList();
        if (roles.isEmpty()) {
            return null;
        } else {
            return roles;
        }
    }


    public static Role findById(String id) throws Exception {
        return entityManager.find(Role.class , id);
    }

}
