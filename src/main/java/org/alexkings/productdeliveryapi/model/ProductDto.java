package org.alexkings.productdeliveryapi.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString
public class ProductDto {
    private Long id;
    private String name;
    private BigDecimal price;
    private String image;
    private Long createdBy;
    private Long updatedBy;
    private Date createdAt;
    private Date updatedAt;
}
