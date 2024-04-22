package com.arroganteIT.app.controller;

import com.arroganteIT.app.persistence.entity.ExchangeRatesUser;
import com.arroganteIT.app.persistence.service.ExchangeRatesUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/api/v1/ex-user")
public class ExchangeRatesUserController {

    private ExchangeRatesUserService userService;

    @Autowired
    public ExchangeRatesUserController(ExchangeRatesUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody ExchangeRatesUser user) {

        userService.save(user);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<ExchangeRatesUser>> findById(@PathVariable Long id) {

        Optional<ExchangeRatesUser> exchangeRatesUser = userService.findById(id);

        if (exchangeRatesUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(exchangeRatesUser);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ExchangeRatesUser>> findAll() {

        List<ExchangeRatesUser> exchangeRatesUserList = userService.findAll();

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
                userService.findByFirstNameAndLastName(firstName, lastName, pageable);

        if (byFirstNameAndLastName.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(byFirstNameAndLastName);
    }
}
