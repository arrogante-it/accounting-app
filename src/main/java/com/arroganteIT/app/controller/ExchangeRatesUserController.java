package com.arroganteIT.app.controller;

import com.arroganteIT.app.persistence.entity.ExchangeRatesUser;
import com.arroganteIT.app.persistence.service.ExchangeRatesUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping("/{id}")
    public Optional<ExchangeRatesUser> findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("/all")
    public List<ExchangeRatesUser> findAll() {
        return userService.findAll();
    }
}
