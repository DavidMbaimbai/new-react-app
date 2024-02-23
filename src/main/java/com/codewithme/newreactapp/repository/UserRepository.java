package com.codewithme.newreactapp.repository;

import com.codewithme.newreactapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
