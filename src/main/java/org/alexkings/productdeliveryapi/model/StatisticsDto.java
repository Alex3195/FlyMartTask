package org.alexkings.productdeliveryapi.model;

import lombok.Data;

import java.util.List;

@Data
public class StatisticsDto {
    private Integer transactionNumber;
    private List<RegionDto> regions;
}
