package org.alexkings.productdeliveryapi.model;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PlaceDto {
    private Long id;
    @Size(min = 3, max = 255)
    private String name;
    private RegionDto region;
}
