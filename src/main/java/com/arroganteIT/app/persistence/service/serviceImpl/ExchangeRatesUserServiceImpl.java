package com.arroganteIT.app.persistence.service.serviceImpl;

import com.arroganteIT.app.persistence.entity.ExchangeRatesUser;
import com.arroganteIT.app.persistence.repository.ExchangeRatesUserRepository;
import com.arroganteIT.app.persistence.service.ExchangeRatesUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ExchangeRatesUserServiceImpl implements ExchangeRatesUserService {

    private ExchangeRatesUserRepository exchangeRatesUserRepository;

    @Autowired
    public ExchangeRatesUserServiceImpl(ExchangeRatesUserRepository userRepository) {
        this.exchangeRatesUserRepository = userRepository;
    }

    @Transactional
    @Override
    public void save(ExchangeRatesUser user) {
        exchangeRatesUserRepository.save(user);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        exchangeRatesUserRepository.deleteById(id);
    }

    @Transactional
    @Override
    public ExchangeRatesUser update(ExchangeRatesUser newUser) {

        //todo change email on unique key
        ExchangeRatesUser existingExchangeRatesUser = exchangeRatesUserRepository.retrieveByEmail(newUser.getEmail());

        if (existingExchangeRatesUser == null) {
            exchangeRatesUserRepository.save(newUser);
        } else {
            existingExchangeRatesUser.setStatus(newUser.getStatus());
            existingExchangeRatesUser.setName(newUser.getName());
            existingExchangeRatesUser.setAddress(newUser.getAddress());
            existingExchangeRatesUser.setPhoneNumber(newUser.getPhoneNumber());
            existingExchangeRatesUser.setBirthday(newUser.getBirthday());
            existingExchangeRatesUser.setAge(newUser.getAge());
            existingExchangeRatesUser.setGender(newUser.getGender());

            exchangeRatesUserRepository.save(existingExchangeRatesUser);
        }
        return existingExchangeRatesUser;
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<ExchangeRatesUser> findById(Long id) {
        return exchangeRatesUserRepository.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ExchangeRatesUser> findAll() {
        return exchangeRatesUserRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<ExchangeRatesUser> findByFirstNameAndLastName(String firstName, String lastName, Pageable pageable) {
        return exchangeRatesUserRepository.findByFirstNameAndLastName(firstName, lastName, pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public ExchangeRatesUser retrieveByEmail(String email) {
        return exchangeRatesUserRepository.retrieveByEmail(email);
    }

    @Transactional(readOnly = true)
    @Override
    public Collection<ExchangeRatesUser> findAllActiveUsers() {
        return exchangeRatesUserRepository.findAllActiveUsers();
    }

//    @Transactional(readOnly = true)
//    @Override
//    public List<User> findAllSortByName() {
//        return userRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
//    }
//
//    @Transactional(readOnly = true)
//    @Override
//    public List<User> findAllSortByNameLength() {
//        return userRepository.findAll(JpaSort.unsafe("length(name)"));
//    }
}
