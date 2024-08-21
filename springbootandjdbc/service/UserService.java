package com.example.springbootandjdbc.service;

import com.example.springbootandjdbc.entity.User;
import com.example.springbootandjdbc.repository.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yaml.snakeyaml.constructor.DuplicateKeyException;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserManager userDao;

    public void addUser(User user) {
        userDao.addUser(user);
    }

    public List<User> getAllUsers() {
        return userDao.findAllUsers();
    }

    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    public User updateUserEmailByUserId(int id, String email) {
        return userDao.updateUserEmail(id, email);
    }

    public User deleteUserByUserId(int id) {
        return userDao.deleteUserById(id);
    }

    public List<User> addUsers(List<User> users) {
        return userDao.addUsers(users);
    }

    public List<User> updateUserEmails(List<User> users) {
        return userDao.updateUserEmails(users);
    }

    public List<User> getUsersByEmailDomain(String domain) {
        return userDao.findByEmailDomain(domain);
    }

    public int getCount(){
        return userDao.getUserCount();
    }

    public List<User> findUsersByFirstChar(char c){
        return userDao.findUsersByFirstChar(c);
    }



}
