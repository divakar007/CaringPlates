package com.application.caringplates.repository;

import com.application.caringplates.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmailId(String email);
    User findByUserId(Long id);
}
