package com.example.UserManagement.Repository;

//import com.example.UserManagement.Entity.User;
import com.example.UserManagement.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepo extends JpaRepository<UserEntity,Long> {
    @Modifying
    @Query(value="Alter Table users Auto_Increment=1",nativeQuery = true)
    void resetAutoIncrement();

    @Modifying
    @Query(value="Truncate Table users", nativeQuery = true)
    void truncateUsers();

   Optional<UserEntity> findByUsername(String username);

}
