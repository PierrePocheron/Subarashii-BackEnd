package com.jfam.subarashii.services;

import com.jfam.subarashii.entities.Role;
import com.jfam.subarashii.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class StatsMetricsService {

    @Autowired
    private UserRepository userRepository;

    public Integer getCountUsersRole(String role){
        return userRepository.countByRole(role);
    }
}
