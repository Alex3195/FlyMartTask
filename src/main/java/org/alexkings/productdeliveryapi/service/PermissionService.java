package org.alexkings.productdeliveryapi.service;

import org.alexkings.productdeliveryapi.model.PermissionDto;

import java.util.List;

public interface PermissionService {
    List<PermissionDto> getAllPermissions();

    PermissionDto addPermission(PermissionDto permission);

    PermissionDto updatePermission(Long id,PermissionDto permission);

    void deletePermission(Long id);

}
