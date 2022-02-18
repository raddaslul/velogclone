package com.sparta.velogclone.repository;

import com.sparta.velogclone.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}