package com.asusoftware.myTransporter.user.repository;

import com.asusoftware.myTransporter.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    @Query("SELECT u FROM User u WHERE u.country=?1 AND u.county=?2 AND u.city=?3")
    List<User> findUsersFromAddress(String country, String county, String city);
}
