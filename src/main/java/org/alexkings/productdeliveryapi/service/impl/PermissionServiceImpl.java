package org.alexkings.productdeliveryapi.service.impl;

import org.alexkings.productdeliveryapi.entities.Permissions;
import org.alexkings.productdeliveryapi.model.PermissionDto;
import org.alexkings.productdeliveryapi.repository.PermissionRepository;
import org.alexkings.productdeliveryapi.service.PermissionService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {
    private final PermissionRepository permissionRepository;

    public PermissionServiceImpl(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    public List<PermissionDto> getAllPermissions() {
        return permissionRepository.findAll().stream().map(this::entityToDto).toList();
    }

    @Override
    public PermissionDto addPermission(PermissionDto permission) {
        Permissions entity = dtoToEntity(permission);
        return entityToDto(permissionRepository.save(entity));
    }

    @Override
    public PermissionDto updatePermission(Long id, PermissionDto permission) {
        Permissions entity = permissionRepository.getReferenceById(id);
        entity.setName(permission.getName());
        return entityToDto(permissionRepository.save(entity));
    }

    @Override
    public void deletePermission(Long id) {
        permissionRepository.deleteById(id);
    }

    private Permissions dtoToEntity(PermissionDto dto) {
        Permissions permissions = new Permissions();
        permissions.setId(dto.getId());
        permissions.setName(dto.getName());
        permissions.setCreatedDate(Date.from(Instant.now()));
        permissions.setUpdateDate(Date.from(Instant.now()));
        return permissions;
    }

    private PermissionDto entityToDto(Permissions permissions) {
        PermissionDto permissionDto = new PermissionDto();
        permissionDto.setId(permissions.getId());
        permissionDto.setName(permissions.getName());
        return permissionDto;
    }
}
