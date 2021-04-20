package com.vaibhav.notes.repository;

import com.vaibhav.notes.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository("userRepo")
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT s FROM user_table s WHERE s.email = ?1")
    Optional<User> findByEmail(String email);
}
