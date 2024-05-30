package org.alexkings.productdeliveryapi.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.alexkings.productdeliveryapi.annotaions.ValidAlphaNumericUnderscore;
import org.alexkings.productdeliveryapi.annotaions.ValidPassword;
import org.alexkings.productdeliveryapi.annotaions.ValidString;

@Data
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class SignInReq {
    @NotNull(message = "username can not be null!")
    @ValidAlphaNumericUnderscore(message = "Invalid user name! Username must have at least 3 characters and it must contains letters, numbers and underscore (_). ")
    private String username;
    @NotNull
    @Size(min = 8, max = 255)
    @ValidPassword(message = "Invalid password! Password must be at least 8 characters and in it must have at least one capital letter, one number, one non capital letter and one special character (!#@$*) ")
    private String password;
    @Email(message = "Invalid email")
    @NotNull(message = "Email can not be null")
    private String email;
    @NotNull(message = "firstname can not be null")
    @Size(min = 3, max = 255)
    @ValidString(message = "Invalid first name it must have only letters and there should not be three identical letter in a row.")
    private String firstName;
    @NotNull(message = "lastname can not be null")
    @Size(min = 3, max = 255)
    @ValidString(message = "Invalid first name it must have only letters and there should not be three identical letter in a row.")
    private String lastName;
    @NotNull(message = "Encoding type can not be empty! It must be BCRYPT or SCRYPT")
    private String encodingType;
    private String image;
}
