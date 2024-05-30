package org.alexkings.productdeliveryapi.controller;

import org.alexkings.productdeliveryapi.model.OfferDto;
import org.alexkings.productdeliveryapi.service.OfferService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/offers")
public class OfferController {
    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/getOffers")
    @PreAuthorize("hasAnyRole('ADMIN','CARRIER') and hasPermission('OFFER_READ')")
    public ResponseEntity<List<OfferDto>> getOffers() {
        return ResponseEntity.ok(offerService.getOffers());
    }

    @GetMapping("/getOfferById/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','CARRIER') and hasPermission('OFFER_READ')")
    public ResponseEntity<OfferDto> getOfferById(@PathVariable long id) {
        return ResponseEntity.ok(offerService.getOffer(id));
    }


}
