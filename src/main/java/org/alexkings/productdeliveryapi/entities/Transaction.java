package org.alexkings.productdeliveryapi.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "transactions")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "transaction_code", nullable = false)
    private String transactionCode;

    @Column(name = "carrier_username", nullable = false)
    private String carrierUsername;

    @Column(name = "request_code", nullable = false)
    private String requestCode;

    @Column(name = "offer_code", nullable = false)
    private String offerCode;

    @Column(name = "delivery_location")
    private String deliveryLocation;

    @Column(name = "_pickup_location")
    private String pickupLocation;

    @Basic(optional = false)
    @Column(name = "created_date", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Basic(optional = false)
    @Column(name = "updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    @Column(name = "score")
    private Integer score = 0;

}
