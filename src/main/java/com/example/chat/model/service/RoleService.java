package com.example.chat.model.service;

import com.example.chat.model.entity.Role;
import com.example.chat.model.service.impl.ServiceImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@ApplicationScoped
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

//        todo : changed by messbah.a --> simple and clean code
//        List<Role> roles = query.getResultList();
//        if (roles.isEmpty()) {
//            return null;
//        } else {
//            return roles;
//        }
        return (!query.getResultList().isEmpty()) ? query.getResultList() : null;
    }


    public Role findById(Long id) throws Exception {
        return entityManager.find(Role.class, id);
    }

    public Role findByRole(String role) throws Exception {
        Query query = entityManager.createNamedQuery("Role.FindByName")
                .setParameter("role", role);

//        todo : changed by messbah.a --> because if there is not result, getSingleResult throws exception
//        return (Role) query.getSingleResult();
        return (!query.getResultList().isEmpty()) ? (Role) query.getResultList().get(0) : null;
    }
}
