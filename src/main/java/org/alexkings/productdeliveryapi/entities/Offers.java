package org.alexkings.productdeliveryapi.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "offers")
@Data
@ToString
@EqualsAndHashCode
public class Offers implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "offer_seq")
    @SequenceGenerator(name = "offer_seq", sequenceName = "offer_seq")
    private Long id;

    @Column(name = "offer_code", unique = true)
    private String offerCode;

    @Column(name = "place_name")
    private String placeName;

    @Column(name = "product_code")
    private String productCode;

    @Column(name = "created_by")
    private Long createdBy;

    @Basic(optional = false)
    @Column(name = "created_date", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Basic(optional = false)
    @Column(name = "updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
}
