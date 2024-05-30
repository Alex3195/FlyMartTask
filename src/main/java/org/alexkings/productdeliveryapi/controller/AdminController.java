package org.alexkings.productdeliveryapi.controller;

import jakarta.validation.Valid;
import org.alexkings.productdeliveryapi.exceptions.UserException;
import org.alexkings.productdeliveryapi.model.SignInReq;
import org.alexkings.productdeliveryapi.model.SignInRes;
import org.alexkings.productdeliveryapi.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createAdminUser(@RequestBody @Valid SignInReq signInReq) throws UserException {
        UserDetails u = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SignInRes admin = userService.createAdminUser(signInReq);
        return ResponseEntity.ok(admin);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> updateAdminUser(@RequestBody @Valid SignInReq signInReq, @PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.updateAdminData(id, signInReq));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAdminUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return ResponseEntity.ok("Deleted admin user with id " + id);
    }
    @PostMapping("/updateStatus/{id}/{status}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateAdminUserStatus(@PathVariable("id")Long id, @PathVariable("status")Boolean status) {
        return ResponseEntity.ok(userService.updateStatusByIdAndStatus(id,status));

    }

}
