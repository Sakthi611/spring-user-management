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
    public ResponseEntity<String> createUser(@RequestBody User user){
        userService.createUser(user);
        return  ResponseEntity.ok("Created User Successfully");
    }

    @PostMapping("/create/users")
    public ResponseEntity<String> createUsers(@RequestBody List<User> users){
        userService.createUsers(users);
        return ResponseEntity.ok().body( "Created Users Successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUserById(@PathVariable("id") Long id, @RequestBody User user){
        userService.updateUserById(id,user);
        return ResponseEntity.ok().body( "Updated the User Successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") Long id){
        userService.deleteUserById(id);
        return ResponseEntity.ok().body("Deleted User Successfully");
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAllUser(){
        userService.deleteAllUser();
        return ResponseEntity.ok().body("All Users Deleted Successfully");
    }

    //Delete All User and reset to 1
    @DeleteMapping("/reset")
    public ResponseEntity<String> deleteAllUsersAndResetId(){
        userService.deleteAllUsersAndResetId();
        return ResponseEntity.ok("Reset Table Successfully");
    }

    @DeleteMapping("/truncate")
    public ResponseEntity<String> truncateUsers(){
        userService.truncateUsers();
        return ResponseEntity.ok("All Users are Deleted and Id Counter reset to 1 using TRUNCATE");
    }


}
