package com.example.UserManagement.Controller;

import com.example.UserManagement.Entity.User;
import com.example.UserManagement.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

@RestController
@RequestMapping("/api/users")
public class UserController {
@Autowired
    private UserService userService;
    @GetMapping("/welcome")
    public String welcome(){

        return "Hello World";
    }

    @GetMapping("/")
    public ResponseEntity<?> getUsers(){
        List<User> users=userService.getAllUsers();
        if(users.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No user is found in the Database");
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(users);
    }
    @GetMapping("{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Long id){
        Optional<User> user=userService.getUserById(id);
        if(user.isPresent()){
            return ResponseEntity.ok(user.get());
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No User Found with Id :"+id);
        }
    }

    @PostMapping("/create")
    public String createUser(@RequestBody User user){
        userService.createUser(user);
        return "Created User Successfully";
    }

    @PostMapping("/create/users")
    public String createUsers(@RequestBody List<User> users){
        userService.createUsers(users);
        return "Created Users Successfully";
    }

    @PutMapping("/update/{id}")
    public String updateUserById(@PathVariable("id") Long id, @RequestBody User user){
        userService.updateUserById(id,user);
        return "Updated the User Successfully";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUserById(@PathVariable("id") Long id){
        userService.deleteUserById(id);
        return "Deleted User Successfully";
    }

    @DeleteMapping("/deleteAll")
    public String deleteAllUser(){
        userService.deleteAllUser();
        return "All Users Deleted Successfully";
    }
}
