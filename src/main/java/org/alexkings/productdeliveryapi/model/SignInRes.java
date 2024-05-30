package org.alexkings.productdeliveryapi.model;

import lombok.*;

@Data
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SignInRes {
    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
}
