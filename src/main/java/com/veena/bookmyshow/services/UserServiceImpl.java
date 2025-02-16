package com.veena.bookmyshow.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.veena.bookmyshow.models.User;
import com.veena.bookmyshow.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepo){
        this.userRepo = userRepo;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public User signupUser(String name, String email, String password) throws Exception{
        Optional<User> uOptional = userRepo.findByEmail(email);
        if(uOptional.isPresent()){
            throw new RuntimeException("You have already signed up!");
        }
        String encodedPassword = passwordEncoder.encode(password);
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(encodedPassword);
        return userRepo.save(user);
    }

    public boolean login(String email, String password) throws Exception{
        Optional<User> uOptional = userRepo.findByEmail(email);
        if(uOptional.isEmpty()){
            throw new RuntimeException("User not signed up!");
        }
        User user = uOptional.get();
        return passwordEncoder.matches(password, user.getPassword());
    }
}
