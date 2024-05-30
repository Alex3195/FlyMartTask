package org.alexkings.productdeliveryapi.service;

import jakarta.servlet.http.HttpServletRequest;
import org.alexkings.productdeliveryapi.model.RoleDto;

import java.util.List;

public interface RoleService {
    RoleDto addRole(RoleDto role, HttpServletRequest request);

    RoleDto updateRole(RoleDto role, Long id,HttpServletRequest request);

    RoleDto getRole(Long id,HttpServletRequest request);

    List<RoleDto> getRoles(HttpServletRequest request);

    void deleteRole(Long id,HttpServletRequest request);
}
