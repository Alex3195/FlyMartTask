package org.alexkings.productdeliveryapi.controller;

import org.alexkings.productdeliveryapi.model.PermissionDto;
import org.alexkings.productdeliveryapi.service.PermissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/permissions")
public class PermissionController {
    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<PermissionDto>> getAllPermissions() {
        return ResponseEntity.ok(permissionService.getAllPermissions());
    }
    @PostMapping("/add")
    public ResponseEntity<PermissionDto> addPermission(@RequestBody PermissionDto permissionDto) {
        return ResponseEntity.ok(permissionService.addPermission(permissionDto));
    }
}
