package com.fc.service;

import com.fc.entity.Users;

public interface LoginService {
    Iterable<Users> findAll();

    Users findByUsername(String username);

    void save(Users users);
}
