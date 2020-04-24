package com.vickylin.propertyweb.controller;

import com.vickylin.propertyweb.entity.User;
import com.vickylin.propertyweb.exception.InvalidUserException;
import com.vickylin.propertyweb.exception.ResourceNotFoundException;
import com.vickylin.propertyweb.exception.UserPermissionException;
import com.vickylin.propertyweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public User createUser (
            @Valid @RequestBody User user) throws Exception {
        return userService.createUser(user);
    }

    @PatchMapping(path = "/{id}")
    public User updateUser(
            @PathVariable Long id,
            @RequestBody User user) throws Exception {
        return userService.updateUser(id, user);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteUser(
            @RequestHeader("user_type") String userType,
            @PathVariable Long id) throws Exception {
        if(!userType.equals(User.UserType.admin.name())) {
            throw new UserPermissionException();
        }
        userService.deleteUser(id);
    }

    @PostMapping(path = "/login")
    public User login(
            @RequestParam String email, String token) throws InvalidUserException {
        return userService.login(email, token);
    }

    @PostMapping(path = "/logout/{id}")
    public User logout(
            @PathVariable Long id) throws ResourceNotFoundException {
        return userService.logout(id);
    }
}
