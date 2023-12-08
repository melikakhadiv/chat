package com.chat.model.service;

import com.chat.controller.session.SessionManager;
import com.chat.model.service.impl.ServiceImpl;
import com.chat.model.entity.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class UserService implements ServiceImpl<User, Long> {

    @PersistenceContext(unitName = "mft")
    private EntityManager entityManager;


    @Override
    @Transactional
    public User save(User user) throws Exception {
        try {
            user.setActive(true);
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

    public User findByUsername(String username) throws Exception {
        Query query = entityManager.createNamedQuery("User.FindByUsername")
                .setParameter("username", username);
        return (User) query.getSingleResult();
    }

    public List<User> findPrivateAcc() throws Exception {
        Query query = entityManager.createNamedQuery("User.FindPrivateAccount");
        return query.getResultList();
    }

    public List<User> findPublicAcc() throws Exception {
        Query query = entityManager.createNamedQuery("User.FindPublicAccount");
        return query.getResultList();
    }

    public List<User> findOtherUsers(String currentUsername) {
        Query query = entityManager.createQuery("select oo.username, oo.photo.filePath from userEntity oo where oo.username in :users and oo.username!=:currentUsername");
        query.setParameter("users", SessionManager.getOnlineUsers());
        query.setParameter("currentUsername", currentUsername);
        System.out.println(query.getResultList());
        return query.getResultList();
    }

    public List<User> findOnlineUsers() {
        Query query = entityManager.createQuery("select oo.username, oo.photo.filePath from userEntity oo where oo.username in :users and oo.privateAccount=false");
        query.setParameter("users", SessionManager.getOnlineUsers());
        return query.getResultList();
    }

    public List<User> findUsers() {
        Query query = entityManager.createQuery("select oo.username, oo.photo.filePath from userEntity oo where oo.username in :users");
        query.setParameter("users", SessionManager.getOnlineUsers());
        return query.getResultList();
    }
}
