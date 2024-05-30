package org.alexkings.productdeliveryapi.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "bind_permissions_to_role")
@Data
public class BindRoleToPermission {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bind_permission_to_role_seq")
    @SequenceGenerator(name = "bind_seq", sequenceName = "bind_seq")
    private Long id;

    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "permission_id")
    private Long permissionId;

}
