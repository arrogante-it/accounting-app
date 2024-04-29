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
    public ExchangeRatesUser update(ExchangeRatesUser user) {

        //todo change email on unique key
        ExchangeRatesUser newExchangeRatesUser = exchangeRatesUserRepository.retrieveByEmail(user.getEmail());

        if (newExchangeRatesUser == null) {
            exchangeRatesUserRepository.save(user);
        } else {
            newExchangeRatesUser.setStatus(user.getStatus());
            newExchangeRatesUser.setName(user.getName());
            newExchangeRatesUser.setAddress(user.getAddress());
            newExchangeRatesUser.setPhoneNumber(user.getPhoneNumber());
            newExchangeRatesUser.setBirthday(user.getBirthday());
            newExchangeRatesUser.setAge(user.getAge());
            newExchangeRatesUser.setGender(user.getGender());

            exchangeRatesUserRepository.save(newExchangeRatesUser);
        }
        return newExchangeRatesUser;
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
