package com.arroganteIT.app.persistence.service.serviceImpl;

import com.arroganteIT.app.persistence.entity.Account;
import com.arroganteIT.app.persistence.repository.AccountRepository;
import com.arroganteIT.app.persistence.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    @Override
    public void save(Account account) {

        accountRepository.save(account);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {

        accountRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Account update(Account newAccount) {

        Account existingAccount = accountRepository.findByUniqueKey(newAccount.getUniqueKey());

        if (existingAccount == null) {
            accountRepository.save(newAccount);
        }else {
            existingAccount.setCreatingDate(newAccount.getCreatingDate());
            existingAccount.setBalance(newAccount.getBalance());
            existingAccount.setAccountType(newAccount.getAccountType());
            existingAccount.setAccountStatus(newAccount.getAccountStatus());

            accountRepository.save(existingAccount);
        }

        return existingAccount;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Account> findAll() {

        return accountRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Account> findById(Long id) {

        return accountRepository.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Account findByUniqueKey(String uniqueKey) {

        return accountRepository.findByUniqueKey(uniqueKey);
    }
}
