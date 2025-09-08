package com.example.UserManagement.Repository;

import com.example.UserManagement.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserRepo extends JpaRepository<User,Long> {
    @Modifying
    @Query(value="Alter Table users Auto_Increment=1",nativeQuery = true)
    void resetAutoIncrement();

    @Modifying
    @Query(value="Truncate Table users")
    void truncateUsers();
}
