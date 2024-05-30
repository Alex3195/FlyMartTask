package org.alexkings.productdeliveryapi.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.alexkings.productdeliveryapi.model.RoleDto;
import org.alexkings.productdeliveryapi.service.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/role")
public class RoleController {
    private final RoleService roleService;


    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addRole(@RequestBody @Valid RoleDto role, HttpServletRequest request) {

        try {
            return ResponseEntity.ok(roleService.addRole(role, request));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editRole(@PathVariable long id, @RequestBody @Valid RoleDto role, HttpServletRequest request) {
        try {
            return ResponseEntity.ok(roleService.updateRole(role, id, request));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/byId/{id}")
    public ResponseEntity<?> getRoleById(@PathVariable long id, HttpServletRequest request) {
        try {
            return ResponseEntity.ok(roleService.getRole(id, request));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllRoles(HttpServletRequest request) {
        try {
            return ResponseEntity.ok(roleService.getRoles(request));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable long id, HttpServletRequest request) {
        roleService.deleteRole(id, request);
        return ResponseEntity.ok("Role deleted by id: " + id);
    }
}
