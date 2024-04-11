package com.arroganteIT.app.persistence.service;

import com.arroganteIT.app.persistence.entity.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    void save(Account account);

    void deleteById(Long id);

    Account updateById(Account account, Long id);

    List<Account> findAll();

    Account findById(Long id);

    Account findByUniqueKey(String uniqueKey);
}
