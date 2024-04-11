package com.arroganteIT.app.persistence.service;

import com.arroganteIT.app.persistence.entity.User;

import java.util.Collection;
import java.util.List;

public interface UserService {

    void save(User user);

    void deleteById(Long id);

    User updateById(User user, Long id);

    User findByName(String name);

    List<User> findAllSortByName();

    List<User> findAllSortByNameLength();

    User retrieveByEmail(String email);

    Collection<User> findAllActiveUsers();
}
