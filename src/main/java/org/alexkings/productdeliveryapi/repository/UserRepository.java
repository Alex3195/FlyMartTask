package org.alexkings.productdeliveryapi.repository;

import org.alexkings.productdeliveryapi.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUsername(String username);

    Optional<Users> findByEmail(String email);

    List<Users> findByIdIn(List<Long> ids);
}