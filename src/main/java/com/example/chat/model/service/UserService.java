package com.example.chat.model.service;

import com.example.chat.model.entity.User;
import com.example.chat.model.service.impl.ServiceImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.List;

public class UserService implements ServiceImpl<User , Long> {
    @PersistenceContext(unitName = "mft")
    private EntityManager entityManager;

    @Override
    public User save(User user) throws Exception {
        user.setActive(true);
        entityManager.persist(user);
        return user;
    }

    @Override
    public User edit(User user) throws Exception {
       entityManager.merge(user);
       return user;
    }

    @Override
    public User remove(Long id) throws Exception {
        User user =findById(id);
        if (user != null){
            user.setActive(false);
            return user;
        }else {
            return null;
        }
    }

    @Override
    public List<User> findAll() throws Exception {
        Query query = entityManager.createQuery("select oo from userEntity oo");
        return query.getResultList();
    }

    @Override
    public User findById(Long id) throws Exception {
        return entityManager.find(User.class , id);
    }
    
    public User login(String username , String password) throws Exception{
        Query query = entityManager.createNamedQuery("User.FindByUsernameAndPassWord")
                .setParameter("username" , username)
                .setParameter("password" , password);
        return (User) query.getSingleResult();
    }

    public List<User> privateAcc() throws Exception{
        Query query = entityManager.createNamedQuery("User.FindPrivateAccount");
        return query.getResultList();
    }

    public List<User> publicAcc() throws Exception{
        Query query = entityManager.createNamedQuery("User.FindByPublicAccount");
        return query.getResultList();
    }
}
