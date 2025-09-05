package com.example.UserManagement.Service;

import com.example.UserManagement.Entity.User;
import com.example.UserManagement.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public void createUser(User user) {
        userRepo.save(user);
    }

    public Optional<User> getUserById(Long id) {
        return userRepo.findById(id);
    }

    public void updateUserById(Long id,User user) {
        userRepo.save(user);
    }

    public void deleteUserById(Long id) {
        userRepo.deleteById(id);
    }

    public void deleteAllUser() {
        userRepo.deleteAll();
    }

    public void createUsers(List<User> users) {
        userRepo.saveAll(users);
    }
}
