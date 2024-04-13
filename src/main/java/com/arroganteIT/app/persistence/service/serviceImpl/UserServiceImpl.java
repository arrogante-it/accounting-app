package com.arroganteIT.app.persistence.service.serviceImpl;

import com.arroganteIT.app.persistence.entity.User;
import com.arroganteIT.app.persistence.repository.UserRepository;
import com.arroganteIT.app.persistence.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    @Override
    public User updateById(User user, Long id) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("no such user in DB with id = " + id));

        existingUser.setUserName(user.getUserName());
        existingUser.setAge(user.getAge());
        existingUser.setStatus(user.getStatus());
        existingUser.setAddress(user.getAddress());
        existingUser.setEmail(user.getEmail());
        existingUser.setBirthday(user.getBirthday());
        existingUser.setGender(user.getGender());
        existingUser.setPhoneNumber(user.getPhoneNumber());

        return userRepository.save(existingUser);

    }

    @Transactional(readOnly = true)
    @Override
    public User findByNameIsNotNull(String name) {
        return userRepository.findByNameIsNotNull(name);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> findAllSortByName() {
        return userRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> findAllSortByNameLength() {
        return userRepository.findAll(JpaSort.unsafe("length(name)"));
    }

    @Transactional(readOnly = true)
    @Override
    public User retrieveByEmail(String email) {
        return userRepository.retrieveByEmail(email);
    }

    @Transactional(readOnly = true)
    @Override
    public Collection<User> findAllActiveUsers() {
        return userRepository.findAllActiveUsers();
    }
}
