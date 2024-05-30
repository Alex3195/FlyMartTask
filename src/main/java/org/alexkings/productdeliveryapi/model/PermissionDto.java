package org.alexkings.productdeliveryapi.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
public class PermissionDto {
    private Long id;
    private String name;
    private RoleDto role;
}
