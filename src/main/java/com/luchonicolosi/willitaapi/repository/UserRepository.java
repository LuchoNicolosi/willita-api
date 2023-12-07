package com.luchonicolosi.willitaapi.repository;

import com.luchonicolosi.willitaapi.model.Role;
import com.luchonicolosi.willitaapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Query("SELECT r FROM User u JOIN u.roles r WHERE u.id = :userId")
    Set<Role> findRolesByUserId(@Param("userId") Long userId);

}
