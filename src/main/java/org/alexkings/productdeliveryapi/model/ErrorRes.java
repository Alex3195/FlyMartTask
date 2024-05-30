package org.alexkings.productdeliveryapi.model;

import lombok.*;
import org.springframework.http.HttpStatus;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ErrorRes {
    private HttpStatus httpStatus;
    private String message;

}
