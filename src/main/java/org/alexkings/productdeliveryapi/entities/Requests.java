package org.alexkings.productdeliveryapi.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

@Entity
@Table
@Data
@ToString
@EqualsAndHashCode
public class Requests {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reuquest_seq")
    @SequenceGenerator(name = "request_seq", sequenceName = "request_seq")
    private Long id;

    @Column(name = "request_code", unique = true)
    private String requestCode;

    @Column(name = "product_code")
    private String productCode;

    @Column(name = "place_name")
    private String placeName;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "updated_by")
    private Long updatedBy;

    @Basic(optional = false)
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Basic(optional = false)
    @Column(name = "updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
}
