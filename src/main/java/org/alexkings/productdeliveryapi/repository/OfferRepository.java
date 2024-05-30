package org.alexkings.productdeliveryapi.repository;

import org.alexkings.productdeliveryapi.entities.Offers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepository extends JpaRepository<Offers,Long> {
    Offers findByOfferCode(String offerCode);
}
