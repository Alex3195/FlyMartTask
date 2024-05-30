package org.alexkings.productdeliveryapi.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@EqualsAndHashCode
public class OfferDto implements Serializable {
    private static final long serialVersionUID = 10L;
    private Long id;
    private String offerCode;
    private String productCode;
    private String placeName;
}
