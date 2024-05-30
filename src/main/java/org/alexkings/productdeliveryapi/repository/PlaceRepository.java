package org.alexkings.productdeliveryapi.repository;

import org.alexkings.productdeliveryapi.entities.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, Long> {
    Place findByName(String name);
}
