package org.alexkings.productdeliveryapi.model;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class CustomUser extends User {
    private Long userId;
    private String username;
    private String password;
    private RoleDto role;

    public CustomUser(Long userId, RoleDto role, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.userId = userId;
        this.role = role;
        this.username = username;
        this.password = password;
    }
}
