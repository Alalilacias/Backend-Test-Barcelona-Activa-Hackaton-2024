package com.api.appactivitats.entities.users.controller;

import com.api.appactivitats.entities.users.domain.User;
import com.api.appactivitats.entities.users.dto.UserDTO;
import com.api.appactivitats.entities.users.dto.UserMapper;
import com.api.appactivitats.entities.users.dto.UserRequest;
import com.api.appactivitats.entities.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserRequest userRequest) {
        User user = new User(
                userRequest.name(),
                userRequest.surname(),
                userRequest.contactInformation()
        );

        userService.createUser(user);
        return ResponseEntity.ok(UserMapper.toDTO(user));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> readUser(@PathVariable String id) {
        User user = userService.readUser(id);
        return ResponseEntity.ok(UserMapper.toDTO(user));
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> readAllUsers() {
        List<UserDTO> users = userService.readAllUsers().stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(users);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable String id, @RequestBody UserRequest userRequest) {
        userService.updateUser(id, userRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
