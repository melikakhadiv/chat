package com.chat.model.service;

import com.chat.model.service.impl.ServiceImpl;
import com.chat.model.entity.User;
import com.chat.model.entity.UserRoles;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class UserService implements ServiceImpl<User, Long> {
    @Inject
    UserRolesService userRolesService;

    @PersistenceContext(unitName = "mft")
    private EntityManager entityManager;


    @Override
    @Transactional
    public User save(User user) throws Exception {
        try {
            entityManager.persist(user);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    @Transactional
    public User edit(User user) throws Exception {
        try {
            UserRoles userRoles = UserRoles.builder()
                    .username(user.getUsername())
                    .roleName(user.getRole().getRole())
                    .build();
            userRolesService.edit(userRoles);
            entityManager.merge(user);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @Transactional
    public User remove(Long id) throws Exception {
        User user = findById(id);
        if (user != null) {
            user.setActive(false);
            return user;
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public List<User> findAll() throws Exception {
        Query query = entityManager.createQuery("select oo from userEntity oo");
        return query.getResultList();
    }

    @Override
    @Transactional
    public User findById(Long id) throws Exception {
        return entityManager.find(User.class, id);
    }


    public User login(String username, String password) throws Exception {
        Query query = entityManager.createNamedQuery("User.FindByUsernameAndPassWord")
                .setParameter("username", username)
                .setParameter("password", password);
        return (User) query.getSingleResult();
    }

    public User findByUsername(String username) throws Exception {
        Query query = entityManager.createNamedQuery("User.FindRoleByUsername")
                .setParameter("username", username);
        return (User) query.getSingleResult();
    }

    public List<User> privateAcc() throws Exception {
        Query query = entityManager.createNamedQuery("User.FindPrivateAccount");
        return query.getResultList();
    }

    public List<User> publicAcc() throws Exception {
        Query query = entityManager.createNamedQuery("User.FindByPublicAccount");
        return query.getResultList();
    }
}