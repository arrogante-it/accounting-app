package com.arroganteIT.app.controller;

import com.arroganteIT.app.persistence.entity.ExchangeRatesUser;
import com.arroganteIT.app.persistence.service.ExchangeRatesUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/ex-user")
public class ExchangeRatesUserController {

    private ExchangeRatesUserService exchangeRatesUserService;

    @Autowired
    public ExchangeRatesUserController(ExchangeRatesUserService userService) {
        this.exchangeRatesUserService = userService;
    }

    @PostMapping(value = "/save")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody ExchangeRatesUser user) {

        exchangeRatesUserService.save(user);
    }

    @PatchMapping(value = "/update")
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody ExchangeRatesUser newUser) {

        exchangeRatesUserService.update(newUser);
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable("id") Long id) {

        exchangeRatesUserService.deleteById(id);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<ExchangeRatesUser>> findById(@PathVariable Long id) {

        Optional<ExchangeRatesUser> exchangeRatesUser = exchangeRatesUserService.findById(id);

        if (exchangeRatesUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(exchangeRatesUser);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ExchangeRatesUser>> findAll() {

        List<ExchangeRatesUser> exchangeRatesUserList = exchangeRatesUserService.findAll();

        if (exchangeRatesUserList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(exchangeRatesUserList);
    }

    @GetMapping(value = "/by-name", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<ExchangeRatesUser>> findByFirstNameAndLastName(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            Pageable pageable) {

        Page<ExchangeRatesUser> byFirstNameAndLastName =
                exchangeRatesUserService.findByFirstNameAndLastName(firstName, lastName, pageable);

        if (byFirstNameAndLastName.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(byFirstNameAndLastName);
    }

    @GetMapping(value = "/all-active", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<ExchangeRatesUser>> findAllActiveUsers() {

        Collection<ExchangeRatesUser> exchangeRatesUsers = exchangeRatesUserService.findAllActiveUsers();

        if (exchangeRatesUsers.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(exchangeRatesUsers);
    }

    @GetMapping(value = "/by-email", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ExchangeRatesUser> retrieveByEmail(@RequestParam("email") String email) {

        ExchangeRatesUser exchangeRatesUser = exchangeRatesUserService.retrieveByEmail(email);

        if (exchangeRatesUser == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(exchangeRatesUser);
    }
}
