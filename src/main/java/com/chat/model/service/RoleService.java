package com.chat.model.service;

import com.chat.model.service.impl.ServiceImpl;
import com.chat.model.entity.Role;
import jakarta.enterprise.context.ApplicationScoped;
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
        return (!query.getResultList().isEmpty()) ? query.getResultList() : null;
    }

    public Role findById(Long id) throws Exception {
        return entityManager.find(Role.class, id);
    }

    public Role findByRole(String role) throws Exception {
        Query query = entityManager.createNamedQuery("Role.FindByName")
                .setParameter("role", role);
        return (!query.getResultList().isEmpty()) ? (Role) query.getResultList().get(0) : null;
    }
}
