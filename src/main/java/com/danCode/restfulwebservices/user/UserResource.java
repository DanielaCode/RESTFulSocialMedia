package com.danCode.restfulwebservices.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserResource {
    private UsersDaoService service;

    public UserResource(UsersDaoService service) {
        this.service = service;
    }

    //Get all users
    @GetMapping("/users")
    public List<User> retrieveAll(){
        return service.findALL();
    }
    //Get user by ID
    @GetMapping("/users/{id}")

    public User findById(@PathVariable int id){
        User user = service.findById(id);
        if (user == null) {
            throw new UserNotFoundException("id: "+id);//this will be the handler for status user not found
        }
        return user;
    }
    //creating new user
    @PostMapping("/users")
    public ResponseEntity<User> save(@RequestBody User user){
        User savedUser= service.save(user);
        URI location= ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(savedUser.getId())
                        .toUri();
        //correct code status when creating is created 201
        //even better the location of the created object is returned in location header response
        return ResponseEntity.created(location).build();
    }
}
