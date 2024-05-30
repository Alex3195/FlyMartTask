package org.alexkings.productdeliveryapi.service.impl;

import org.alexkings.productdeliveryapi.entities.Transaction;
import org.alexkings.productdeliveryapi.model.StatisticsDto;
import org.alexkings.productdeliveryapi.repository.TransactionRepository;
import org.alexkings.productdeliveryapi.service.StatisticsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Override
    public List<Map<String, Object>> getNumberOfTransactionsPerProduct() {
        List<Transaction> all = transactionRepository.findAll();
        Map<String, List<Transaction>> groupByProductCode = all.stream().filter(i -> i.getScore() > 0).collect(Collectors.groupingBy(Transaction::getProductCode));
        List<Map<String, Object>> result = new ArrayList<>();
        groupByProductCode.forEach((key, value) -> {
            Map<String, Object> map = new HashMap<>();
            map.put("productCode", key);
            map.put("transactionCount", value.size());
            result.add(map);
        });
        return result;
    }
}
