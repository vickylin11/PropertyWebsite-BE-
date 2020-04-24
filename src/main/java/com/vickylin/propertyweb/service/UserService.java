package com.vickylin.propertyweb.service;

import com.vickylin.propertyweb.entity.User;
import com.vickylin.propertyweb.exception.AuthorizationException;
import com.vickylin.propertyweb.exception.InvalidUserException;
import com.vickylin.propertyweb.exception.ResourceNotFoundException;
import com.vickylin.propertyweb.repository.UserRepository;
import com.vickylin.propertyweb.utils.NullAwareBeanUtilsBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NullAwareBeanUtilsBean beanUtils;

    public User createUser(User user) throws Exception {
        Optional<User> opt = userRepository.findByEmail(user.getEmail());

        if(opt.isPresent()) {
            throw new Exception("User exists. Please use other email address.");
        }

        user.setLogin(true);
        return userRepository.save(user);
    }

    public User updateUser(Long id, User user) throws Exception {
        User originalUser = getUserById(id);

        if(!originalUser.isLogin()) {
            throw new AuthorizationException();
        }

        beanUtils.copyProperties(originalUser, user);
        return userRepository.save(originalUser);
    }

    public void deleteUser(Long id)  {
        userRepository.deleteById(id);
    }

    public User login(String email, String token) throws InvalidUserException {
        Optional<User> opt = userRepository.findByEmail(email);

        if(!opt.isPresent() || !opt.get().getPassword().equals(token)) {
            throw new InvalidUserException();
        }

        User user = opt.get();
        user.setLogin(true);
        return userRepository.save(user);
    }

    public User logout(Long id) throws ResourceNotFoundException {
        User user = getUserById(id);
        user.setLogin(false);
        return userRepository.save(user);
    }

    public User getUserById(Long id) throws ResourceNotFoundException {
        Optional<User> opt = userRepository.findById(id);

        if(!opt.isPresent()) {
            throw new ResourceNotFoundException();
        }

        return opt.get();
    }
}
