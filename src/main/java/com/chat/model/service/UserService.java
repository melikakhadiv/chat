package com.chat.model.service;

import com.chat.controller.session.SessionManager;
import com.chat.model.entity.Role;
import com.chat.model.service.impl.ServiceImpl;
import com.chat.model.entity.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Slf4j
public class UserService implements ServiceImpl<User, Long> {

    @PersistenceContext(unitName = "mft")
    private EntityManager entityManager;


    @Override
    @Transactional
    public User save(User user) throws Exception {
//        if (findByUsername(user.getUsername()) == null) {
            user.setActive(true);
            entityManager.persist(user);
            log.info("User-Service-Saved");
            return user;
//        } else {
//            log.error("User-Service-Save-Duplicate");
//            return null;
//        }
    }


    @Override
    @Transactional
    public User edit(User user) throws Exception {
        entityManager.merge(user);
        log.info("User-Service-Edited");
        return user;
    }

    @Override
    @Transactional
    public User remove(Long id) throws Exception {
        User user = findById(id);
        if (user != null) {
            user.setActive(false);
            log.info("User-Service-Removed");
            return user;
        } else {
            log.error("User-Service-Remove-NotFound");
            return null;
        }
    }

    @Override
    @Transactional
    public List<User> findAll(){
        TypedQuery<User> query = entityManager.createQuery("select oo from userEntity oo", User.class);
        log.info("User-Service-FindAll");
        return query.getResultList();
    }

    @Override
    @Transactional
    public User findById(Long id){
        Optional<User> user = Optional.ofNullable(entityManager.find(User.class, id));
        log.info("User-Service-FindById");
        return user.isPresent() ? user.get() : null;
    }

    public User findByUsername(String username) throws Exception {
        Optional<User> user = Optional.ofNullable(entityManager.createNamedQuery("User.FindByUsername" , User.class)
                .setParameter("username", username).getSingleResult());
        log.info("User-Service-FindByUsername");
        return user.isPresent() ? user.get() : null;
    }

    public List<User> findPrivateAcc() throws Exception {
        TypedQuery<User> query = entityManager.createNamedQuery("User.FindPrivateAccount", User.class);
        log.info("User-Service-FindPrivateAcc");
        return query.getResultList();
    }

    public List<User> findPublicAcc() throws Exception {
        TypedQuery<User> query = entityManager.createNamedQuery("User.FindPublicAccount", User.class);
        log.info("User-Service-FindPublicAcc");
        return query.getResultList();
    }

    public List<User> findOtherUsers(String currentUsername) {
        TypedQuery<User> query = entityManager
                .createQuery("select oo.username, oo.photo.filePath from userEntity oo where oo.username in :users and oo.username!=:currentUsername", User.class)
                .setParameter("users", SessionManager.getOnlineUsers())
                .setParameter("currentUsername", currentUsername);
        log.info("User-Service-FindOtherUsers");
        return query.getResultList();
    }

    public List<User> findOnlineUsers() {
        TypedQuery<User> query = entityManager
                .createQuery("select oo.username, oo.photo.filePath from userEntity oo where oo.username in :users and oo.privateAccount=false", User.class)
                .setParameter("users", SessionManager.getOnlineUsers());
        log.info("User-Service-FindOnlineUsers");
        return query.getResultList();
    }

    public List<User> findUsers() {
        TypedQuery<User> query = entityManager
                .createQuery("select oo.username, oo.photo.filePath from userEntity oo where oo.username in :users", User.class)
                .setParameter("users", SessionManager.getOnlineUsers());
        log.info("User-Service-FindUsers");
        return query.getResultList();
    }
}
