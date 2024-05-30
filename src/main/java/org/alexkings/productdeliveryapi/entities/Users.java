package org.alexkings.productdeliveryapi.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@EqualsAndHashCode
@ToString
@Entity
public class Users implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, max = 255)
    @Column(name = "first_name")
    private String firstname;

    @Size(min = 3, max = 255)
    @Column(name = "last_name")
    private String lastname;

    @Size(min = 3, max = 255)
    @Column(name = "user_name", unique = true, nullable = false)
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "is_enabled")
    private boolean enabled;

    @Column(name = "image")
    private String image;

    @Column(name = "role")
    private String role;

    @Column(name = "encoding_type", nullable = false)
    private String encodingType;

    @Basic(optional = false)
    @Column(name = "created_date", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Basic(optional = false)
    @Column(name = "updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

}



