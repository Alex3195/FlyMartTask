package org.alexkings.productdeliveryapi.controller;

import org.alexkings.productdeliveryapi.service.BindService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/binding")
public class BindingController {
    private final BindService bindService;

    public BindingController(BindService bindService) {
        this.bindService = bindService;
    }

    @PostMapping("/bind-role-to-permission")
    private ResponseEntity<?> bindRoleToPermission(@RequestParam("role") Long roleId, @RequestParam("permission") List<Long> permissionIds) {
        bindService.bindPermissionsToRole(roleId, permissionIds);
        return ResponseEntity.ok().body("Success");
    }

    @PostMapping("/bind-place-to-carrier")
    private ResponseEntity<?> bindPlaceToCarrier(@RequestParam("carrier") Long carrier, @RequestParam("places") List<Long> places) {
        bindService.bindPlacesToCarrier(carrier, places);
        return ResponseEntity.ok().body("Success");
    }
}
