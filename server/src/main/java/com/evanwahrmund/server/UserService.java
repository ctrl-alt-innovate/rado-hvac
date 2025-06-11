package com.evanwahrmund.server;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public boolean validateCredentials(String username, String password){
        Optional<User> optionalUser = userRepository.findByUsername(username);
        return optionalUser.isPresent() && optionalUser.get().getPassword().equals(password);
    }

}
