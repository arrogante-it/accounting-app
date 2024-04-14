package com.arroganteIT.app.persistence.service.serviceImpl;

import com.arroganteIT.app.persistence.entity.ExchangeRatesUser;
import com.arroganteIT.app.persistence.repository.ExchangeRatesUserRepository;
import com.arroganteIT.app.persistence.service.ExchangeRatesUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ExchangeRatesUserServiceImpl implements ExchangeRatesUserService {

    private ExchangeRatesUserRepository userRepository;

    @Autowired
    public ExchangeRatesUserServiceImpl(ExchangeRatesUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public void save(ExchangeRatesUser user) {
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

//    @Transactional
//    @Override
//    public User updateById(User user, Long id) {
//        User existingUser = userRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("no such user in DB with id = " + id));
//
//        existingUser.setUserName(user.getUserName());
//        existingUser.setAge(user.getAge());
//        existingUser.setStatus(user.getStatus());
//        existingUser.setAddress(user.getAddress());
//        existingUser.setEmail(user.getEmail());
//        existingUser.setBirthday(user.getBirthday());
//        existingUser.setGender(user.getGender());
//        existingUser.setPhoneNumber(user.getPhoneNumber());
//
//        return userRepository.save(existingUser);
//
//    }

    @Override
    public Optional<ExchangeRatesUser> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<ExchangeRatesUser> findAll() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public ExchangeRatesUser findByName(String name) {
        return userRepository.findByName(name);
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
//
//    @Transactional(readOnly = true)
//    @Override
//    public User retrieveByEmail(String email) {
//        return userRepository.retrieveByEmail(email);
//    }
//
//    @Transactional(readOnly = true)
//    @Override
//    public Collection<User> findAllActiveUsers() {
//        return userRepository.findAllActiveUsers();
//    }
}
