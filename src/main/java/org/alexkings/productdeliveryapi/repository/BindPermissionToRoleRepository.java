package org.alexkings.productdeliveryapi.repository;

import org.alexkings.productdeliveryapi.entities.BindRoleToPermission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BindPermissionToRoleRepository extends JpaRepository<BindRoleToPermission, Long> {
}
