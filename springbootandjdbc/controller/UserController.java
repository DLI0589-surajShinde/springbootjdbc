package com.example.springbootandjdbc.controller;
import com.example.springbootandjdbc.entity.User;
import com.example.springbootandjdbc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public void addUser(@RequestBody User user) {
        userService.addUser(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id){
        return userService.getUserById(id);
    }

    @PutMapping("/{id}/email/{email}")
    public User updateUserEmailByUserId(@PathVariable int id, @PathVariable String email) {
        return userService.updateUserEmailByUserId(id,email);
    }

    @DeleteMapping("/{id}")
    public User deleteUserByUserId(@PathVariable int id) {
        return userService.deleteUserByUserId(id);
    }

    @PostMapping("/batchInsert")
    public List<User> addUsers(@RequestBody List<User> users) {
        return userService.addUsers(users);
    }

    @PutMapping("/batchUpdate")
    public List<User> updateUserEmails(@RequestBody List<User> users) {
        return userService.updateUserEmails(users);
    }


    @GetMapping("/domain")
    public List<User> getUsersByEmailDomain(@RequestParam String domain) {
        return userService.getUsersByEmailDomain(domain);
    }


    @GetMapping("/count")
    public int getCount(){
        return userService.getCount();
    }

    @GetMapping("/firstchar/{charr}")
    public List<User> addUserIfNotExist(@PathVariable char charr) {
           return userService.findUsersByFirstChar(charr);
    }


}