package com.arroganteIT.app.controller;

import com.arroganteIT.app.persistence.entity.Account;
import com.arroganteIT.app.persistence.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/account")
public class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping(value = "/save")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody Account account) {

        accountService.save(account);
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable("id") Long id) {

        accountService.deleteById(id);
    }

    @PatchMapping(value = "/update")
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody Account account) {

        accountService.update(account);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<Account>> findById(@PathVariable("id") Long id) {

        Optional<Account> account = accountService.findById(id);

        if (account.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(account);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Account>> findAll() {

        List<Account> accountList = accountService.findAll();

        if (accountList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(accountList);
    }

    @GetMapping(value = "/by-key", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Account> findByUniqueKey(@RequestParam("key") String uniqueKey) {

        Account account = accountService.findByUniqueKey(uniqueKey);

        if (account == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(account);
    }
}
