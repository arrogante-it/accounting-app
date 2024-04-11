package com.arroganteIT.app.persistence.service.serviceImpl;

import com.arroganteIT.app.persistence.entity.Account;
import com.arroganteIT.app.persistence.repository.AccountRepository;
import com.arroganteIT.app.persistence.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

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
    public Account updateById(Account account, Long id) {
        Account existingAccount = accountRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("no such account in DB with id = " + id));

        existingAccount.setUniqueKey(account.getUniqueKey());
        existingAccount.setAccountStatus(account.getAccountStatus());
        existingAccount.setAccountType(account.getAccountType());
        existingAccount.setBalance(account.getBalance());
        existingAccount.setCreatingDate(account.getCreatingDate());
        existingAccount.setUser(account.getUser());

        return existingAccount;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Account findById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("no such account in DB with id = " + id));
    }

    @Transactional(readOnly = true)
    @Override
    public Account findByUniqueKey(String uniqueKey) {
        return accountRepository.findByUniqueKey(uniqueKey);
    }
}
