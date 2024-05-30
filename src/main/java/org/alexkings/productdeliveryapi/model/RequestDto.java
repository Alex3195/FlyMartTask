package org.alexkings.productdeliveryapi.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
public class RequestDto {
    private Long id;
    private String requestCode;
    private String productCode;
    private String placeName;
}
