package com.arroganteIT.app.persistence.repository;

import com.arroganteIT.app.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByNameIsNotNull(String name);

    @Query("select u from User u where lower(u.email) = lower(:email)")
    User retrieveByEmail(@Param("email") String email);

    @Query("select u form User u where u.status = 1")
    Collection<User> findAllActiveUsers();
}
