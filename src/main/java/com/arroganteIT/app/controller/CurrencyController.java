package com.arroganteIT.app.controller;

import com.arroganteIT.app.persistence.entity.Currency;
import com.arroganteIT.app.persistence.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequestMapping(value = "/api/v1/currency")
public class CurrencyController {

    private CurrencyService currencyService;

    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @PostMapping(value = "/save")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody Currency currency) {

        currencyService.save(currency);
    }

    @PatchMapping(value = "/update-by-code")
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestParam("code") String code, @RequestParam("rate") double newRate) {

        currencyService.updateExchangeRateByCode(code, newRate);
    }

    @DeleteMapping(value = "/delete-by-id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable("id") Long id) {

        currencyService.deleteById(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Currency>> findById(@PathVariable("id") Long id) {

        Optional<Currency> currency = currencyService.findById(id);

        if (currency.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(currency);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Currency>> findAll() {

        List<Currency> currencyList = currencyService.findAllCurrencies();

        if (currencyList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(currencyList);
    }

    @GetMapping("/by-code")
    public ResponseEntity<Currency> findByCode(@RequestParam("code") String code) {

        Currency currency = currencyService.findByCode(code);

        if (currency == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(currency);
    }
}
