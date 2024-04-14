package com.arroganteIT.app.persistence.repository;

import com.arroganteIT.app.persistence.entity.ExchangeRatesUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeRatesUserRepository extends JpaRepository<ExchangeRatesUser, Long> {

    ExchangeRatesUser findByName(String name);
//
//    @Query("select u from User u where lower(u.email) = lower(:email)")
//    User retrieveByEmail(@Param("email") String email);
//
//    @Query("select u from User u where u.status = 1")
//    Collection<User> findAllActiveUsers();
}
