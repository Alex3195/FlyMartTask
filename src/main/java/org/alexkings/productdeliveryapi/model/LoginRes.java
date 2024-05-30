package org.alexkings.productdeliveryapi.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class LoginRes {
    private String userName;
    private String token;
}
