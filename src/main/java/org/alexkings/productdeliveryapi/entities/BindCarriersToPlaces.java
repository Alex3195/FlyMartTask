package org.alexkings.productdeliveryapi.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "bind_carriers_to_places")
@Data
public class BindCarriersToPlaces {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bind_car_to_place_seq")
    @SequenceGenerator(name = "bind_car_to_place_seq", sequenceName = "bind_car_to_place_seq")
    private Long id;

    @Column(name = "carrier_id")
    private Long carrierId;

    @Column(name = "place_id")
    private Long placeId;

    @Column(name = "region_id")
    private Long regionId;

    @Basic(optional = false)
    @Column(name = "created_date", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Basic(optional = false)
    @Column(name = "updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

}
