package org.alexkings.productdeliveryapi.repository;

import org.alexkings.productdeliveryapi.entities.Permissions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permissions, Long> {
}
