package net.javaguides.springboot.controller;

import lombok.AllArgsConstructor;
import net.javaguides.springboot.dto.UserDto;
import net.javaguides.springboot.entity.User;
import net.javaguides.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Build create user REST API
    @PostMapping
    // http://localhost:8080/api/users
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user) {
       UserDto savedUser =  userService.createUser(user);
       return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    // Build get user by id REST API
    // http://localhost:8080/api/users/1
    @GetMapping("{id}")
    public  ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId) {
      UserDto user = userService.getUserById(userId);
      return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // Build get all users REST API
    // http://localhost:8080/api/users
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
      List<UserDto> users = userService.getAllUsers();
      return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Build Update User Rest API
    @PutMapping("{id}")
    // http://localhost:8080/api/users/1
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long userId,@RequestBody UserDto user) {
      user.setId(userId);
      UserDto updatedUser =  userService.updateUser(user);
      return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    // Build Delete User REST API
    @DeleteMapping("{id}")
    // http://localhost:8080/api/users/1
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>("User successfully Deleted!", HttpStatus.OK);
    }
}
