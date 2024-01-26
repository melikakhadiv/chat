package com.chat.model.service;

import com.chat.controller.exception.NoContentException;
import com.chat.model.service.impl.ServiceImpl;
import com.chat.model.entity.Role;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Slf4j
public class RoleService implements ServiceImpl<Role, Long> {
    @PersistenceContext(unitName = "mft")
    private EntityManager entityManager;

    @Override
    @Transactional
    public Role save(Role role) throws Exception {
        entityManager.persist(role);
        log.info("Role-Service-Saved");
        return role;
    }

    @Override
    @Transactional
    public Role edit(Role role) throws Exception {
        entityManager.merge(role);
        log.info("Role-Service-Edited");
        return role;
    }

    @Override
    @Transactional
    public Role remove(Long id) throws Exception {
        Role role = findById(id);
        if (role != null) {
            entityManager.remove(id);
            log.info("Role-Service-Removed");
            return role;
        } else {
            log.error("Role-Service-NotFound");
            throw new NoContentException();
        }
    }

    @Override
    public List<Role> findAll() throws Exception {
        TypedQuery<Role> query = entityManager.createQuery("select oo from roleEntity oo" , Role.class);
        log.info("Role-Service-FindAll");
        return query.getResultList();
    }

    public Role findById(Long id) throws Exception {
        Optional<Role> role = Optional.ofNullable(entityManager.find(Role.class , id));
        log.info("Role-Service-findById");
        return role.isPresent() ? role.get() : null;
    }

    public Role findByRole(String roleName) throws Exception {
        Optional<Role> role = Optional.ofNullable(entityManager.createNamedQuery("Role.FindByName" , Role.class)
                .setParameter("role", roleName).getSingleResult());
        log.info("Role-Service-FindByRole");
        return role.isPresent() ? role.get() : null;
    }
}
