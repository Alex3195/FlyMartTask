package org.alexkings.productdeliveryapi.controller;

import org.alexkings.productdeliveryapi.model.UserDto;
import org.alexkings.productdeliveryapi.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/all")
    @PreAuthorize("hasPermission('USER_READ') and hasRole('ADMIN')")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
    @PostMapping("/addCarrier)")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDto> addCarrier(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.addCarrier(userDto));
    }
    @GetMapping("/getCarriersForRegion/{name}")
    public ResponseEntity<List<UserDto>> getCarriersForRegion(@PathVariable String name) {
        return ResponseEntity.ok(userService.getCarriersByPlaceName(name));
    }
}
