package com.asusoftware.myTransporter.user.repository;

import com.asusoftware.myTransporter.user.model.User;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

   // @Query("SELECT u FROM User u WHERE u.country=?1 AND u.county=?2 AND u.city=?3")
    @Query("SELECT u FROM Users u  WHERE u.address.country=?1 AND u.address.county=?2 AND u.address.city=?3")
    List<User> findUsersByAddressId(String country, String county, String city);

    @Query("SELECT u.followers FROM Users u  WHERE u.id=?1")
    List<User> findTransporterFollowersWithPagination(UUID id, PageRequest pageRequest);

    @Query("SELECT u FROM Users u  WHERE u.invitationLink.token=?1")
    Optional<User> findUserByToken(String token);
}
