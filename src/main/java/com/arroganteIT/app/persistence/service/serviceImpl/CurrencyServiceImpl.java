package com.arroganteIT.app.persistence.service.serviceImpl;

import com.arroganteIT.app.persistence.entity.Currency;
import com.arroganteIT.app.persistence.repository.CurrencyRepository;
import com.arroganteIT.app.persistence.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private CurrencyRepository currencyRepository;

    @Autowired
    public CurrencyServiceImpl(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Transactional
    @Override
    public void save(Currency currency) {

        currencyRepository.save(currency);
    }

    @Transactional
    @Override
    public void updateExchangeRateByCode(String code, double newRate) {

        Currency existingCurrency = currencyRepository.findByCode(code);

        if (existingCurrency == null) {
            currencyRepository.save(currencyRepository.findByCode(code));
        }else {
            existingCurrency.setExchangeRate(newRate);
            currencyRepository.save(existingCurrency);
        }
    }

    @Transactional
    @Override
    public void deleteById(Long id) {

        currencyRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Currency> findById(Long id) {

        return currencyRepository.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Currency findByCode(String code) {

        return currencyRepository.findByCode(code);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Currency> findAllCurrencies() {

        return currencyRepository.findAll();
    }
}
