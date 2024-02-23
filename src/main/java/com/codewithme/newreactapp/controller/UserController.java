package com.codewithme.newreactapp.controller;

import com.codewithme.newreactapp.exception.UserNotFoundException;
import com.codewithme.newreactapp.model.User;
import com.codewithme.newreactapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {
    @Autowired
    private UserRepository repository;

    @GetMapping("/users")
    List<User> getAllUsers(){
        return repository.findAll();
    }
    @PostMapping("/user")
    User newUser(@RequestBody User newUser){
        return repository.save(newUser);
    }

    @GetMapping("/user/{id}")
    Optional<User> getUserById(@PathVariable Long id){
        return Optional.ofNullable(repository.findById(id).orElseThrow(() -> new UserNotFoundException(id)));
    }
    @PutMapping("/user/{id}")
    Optional<User> updateUser(@RequestBody User newUser, @PathVariable Long id){
        return Optional.ofNullable(repository.findById(id)
                .map(user -> {
                    user.setUsername(newUser.getUsername());
                    user.setName(newUser.getName());
                    user.setEmail(newUser.getEmail());
                    return repository.save(user);
                }).orElseThrow(() -> new UserNotFoundException(id)));
    }
    @DeleteMapping("/user/{id}")
    String deleteUser(@PathVariable Long id){
        if (!repository.existsById(id)){
            throw new UserNotFoundException(id);
        }
        repository.deleteById(id);
        return "User with id " + id + " was deleted successfully";
    }
}
