package org.alexkings.productdeliveryapi.model;

import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Set;

@Data
@ToString
@EqualsAndHashCode
public class RoleDto {
    private Long id;
    @Size(min = 3)
    private String name;
    private Set<PermissionDto> permission;
}
