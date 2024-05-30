package org.alexkings.productdeliveryapi.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
public class TransactionDto {
    private Long id;
    private String transactionCode;
    private String carrierUsername;
    private String requestCode;
    private String offerCode;
    private String deliveryLocation;
    private String pickupLocation;
    private int score;
}
