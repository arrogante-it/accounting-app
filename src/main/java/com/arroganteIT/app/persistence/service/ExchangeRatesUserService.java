package com.arroganteIT.app.persistence.service;

import com.arroganteIT.app.persistence.entity.ExchangeRatesUser;

import java.util.List;
import java.util.Optional;

public interface ExchangeRatesUserService {

    void save(ExchangeRatesUser user);

    void deleteById(Long id);

 //   User updateById(User user, Long id);

    Optional<ExchangeRatesUser> findById(Long id);

    List<ExchangeRatesUser> findAll();

    ExchangeRatesUser findByName(String name);

//    List<User> findAllSortByName();
//
//    List<User> findAllSortByNameLength();
//
//    User retrieveByEmail(String email);
//
//    Collection<User> findAllActiveUsers();
}
