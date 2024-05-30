package org.alexkings.productdeliveryapi.repository;

import org.alexkings.productdeliveryapi.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Roles, Long> {
    Roles findByRole(String name);
}
