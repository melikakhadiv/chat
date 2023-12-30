package com.chat.model.service.impl;

import com.chat.model.entity.User;
import com.chat.model.entity.UserRole;
import jakarta.transaction.Transactional;

import java.util.List;

public interface ServiceImpl<T,I>{
    public T save(T t) throws Exception;
    public T edit(T t) throws Exception;
    public T remove(I id) throws Exception;
    public List<T> findAll() throws Exception;
    public T findById(I id) throws Exception;

}
