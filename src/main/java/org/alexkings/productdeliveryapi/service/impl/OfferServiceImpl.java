package org.alexkings.productdeliveryapi.service.impl;

import org.alexkings.productdeliveryapi.entities.Offers;
import org.alexkings.productdeliveryapi.model.OfferDto;
import org.alexkings.productdeliveryapi.repository.OfferRepository;
import org.alexkings.productdeliveryapi.service.OfferService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;

    public OfferServiceImpl(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public OfferDto addOffer(OfferDto offer) {
        return null;
    }

    @Override
    public OfferDto updateOffer(OfferDto offer, Long id) {
        return null;
    }

    @Override
    public OfferDto getOffer(Long id) {
        return null;
    }

    @Override
    public List<OfferDto> getOffers() {
        return List.of();
    }

    @Override
    public void deleteOffer(Long id) {

    }

    private Offers dtoToOffers(OfferDto dto) {
        Offers offers = new Offers();
        offers.setOfferCode(dto.getOfferCode());
        offers.setProductCode(dto.getProductCode());
        offers.setPlaceName(dto.getPlaceName());
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return offers;
    }
}
