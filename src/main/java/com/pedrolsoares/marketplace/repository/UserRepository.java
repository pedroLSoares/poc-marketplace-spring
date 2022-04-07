package com.pedrolsoares.marketplace.repository;

import com.pedrolsoares.marketplace.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByEmail(String email);

    @Query("SELECT u FROM AppUser u WHERE u.user_name = ?1")
    Optional<AppUser> findByUser_name(String userName);
}
