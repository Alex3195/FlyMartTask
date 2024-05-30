package org.alexkings.productdeliveryapi.service;

import org.alexkings.productdeliveryapi.model.OfferDto;

import java.util.List;

public interface OfferService {
    OfferDto addOffer(OfferDto offer);

    OfferDto updateOffer(OfferDto offer, Long id);

    OfferDto getOffer(Long id);

    List<OfferDto> getOffers();

    void deleteOffer(Long id);
}
