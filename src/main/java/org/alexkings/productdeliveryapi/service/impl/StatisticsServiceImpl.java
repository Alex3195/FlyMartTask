package org.alexkings.productdeliveryapi.service.impl;

import org.alexkings.productdeliveryapi.model.StatisticsDto;
import org.alexkings.productdeliveryapi.repository.TransactionRepository;
import org.alexkings.productdeliveryapi.service.StatisticsService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StatisticsServiceImpl implements StatisticsService {
    private final TransactionRepository transactionRepository;

    public StatisticsServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<StatisticsDto> getStatistics() {
        return List.of();
    }
}
