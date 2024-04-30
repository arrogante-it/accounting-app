package com.arroganteIT.app.persistence.service;

import com.arroganteIT.app.persistence.entity.Currency;

import java.util.List;
import java.util.Optional;

public interface CurrencyService {

    void save(Currency currency);

    void updateExchangeRateByCode(String code, double newRate);

    void deleteById(Long id);

    Optional<Currency> findById(Long id);

    Currency findByCode(String code);

    List<Currency> findAllCurrencies();
}
