package com.project.my.module.sns.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.my.module.userRole.repository.UserRoleRepository;

@Service
public class UserRoleService {
    private final UserRoleRepository userRoleRepository;
    @Autowired
    public UserRoleService(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    } 
}
