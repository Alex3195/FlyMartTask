package org.alexkings.productdeliveryapi.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class RegionDto {
    private Long id;
    @NotNull(message = "Region name could not be null")
    @Size(min = 3, max = 255, message = "name siz must be  at least 3 characters")
    private String name;
    private List<PlaceDto> places;
}
