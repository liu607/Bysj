package com.fc.service.impl;

import com.fc.entity.Users;
import com.fc.service.LoginService;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl  implements LoginService {
    @Override
    public Iterable<Users> findAll() {
        return null;

    }

    @Override
    public Users findByUsername(String username) {
        return null;
    }

    @Override
    public void save(Users users) {

    }
}
