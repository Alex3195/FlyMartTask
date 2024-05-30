package org.alexkings.productdeliveryapi.service;

import org.alexkings.productdeliveryapi.model.StatisticsDto;

import java.util.List;

public interface StatisticsService {
    List<StatisticsDto> getStatistics();
}
