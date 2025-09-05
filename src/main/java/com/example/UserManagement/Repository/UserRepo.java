package com.example.UserManagement.Repository;

import com.example.UserManagement.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {

}
