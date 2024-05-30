package org.alexkings.productdeliveryapi.service;

import org.alexkings.productdeliveryapi.model.StatisticsDto;

import java.util.List;
import java.util.Map;

public interface StatisticsService {
    List<StatisticsDto> getStatistics();

    List<Map<String,Object>> getNumberOfTransactionsPerProduct();
}
