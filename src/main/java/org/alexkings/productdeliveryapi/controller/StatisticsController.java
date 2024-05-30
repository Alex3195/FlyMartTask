package org.alexkings.productdeliveryapi.controller;

import org.alexkings.productdeliveryapi.model.StatisticsDto;
import org.alexkings.productdeliveryapi.service.StatisticsService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {
    private final StatisticsService statisticsService;

    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping("/deliveryRegionsPerNT")
    @PreAuthorize("hasAnyRole('STATISTICS')")
    public ResponseEntity<List<StatisticsDto>> getDeliveryRegionsPerNT() {
        return ResponseEntity.ok().body(statisticsService.getStatistics());
    }

    @GetMapping("/numberOfTransactionsPerProduct)")
    @PreAuthorize("hasAnyRole('STATISTICS')")
    public ResponseEntity<List<Map<String, Object>>> getNumberOfTransactionsPerProduct() {
        return ResponseEntity.ok().body(statisticsService.getNumberOfTransactionsPerProduct());
    }

}
