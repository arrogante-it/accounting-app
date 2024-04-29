package com.arroganteIT.app.persistence.service;

import com.arroganteIT.app.persistence.entity.ExchangeRatesUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ExchangeRatesUserService {

    void save(ExchangeRatesUser user);

    void deleteById(Long id);

    ExchangeRatesUser update(ExchangeRatesUser user);

    Optional<ExchangeRatesUser> findById(Long id);

    List<ExchangeRatesUser> findAll();

    Page<ExchangeRatesUser> findByFirstNameAndLastName(String firstName, String lastName, Pageable pageable);

    //    List<User> findAllSortByName();
//
//    List<User> findAllSortByNameLength();
//
    ExchangeRatesUser retrieveByEmail(String email);

    Collection<ExchangeRatesUser> findAllActiveUsers();
}
