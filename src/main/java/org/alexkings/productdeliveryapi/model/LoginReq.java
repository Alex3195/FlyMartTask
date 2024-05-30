package org.alexkings.productdeliveryapi.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class LoginReq {
    private String username;
    private String password;
}
