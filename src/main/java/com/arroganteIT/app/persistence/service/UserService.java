package com.arroganteIT.app.persistence.service;

import com.arroganteIT.app.persistence.entity.User;

public interface UserService {

    User findByName(String name);
}
