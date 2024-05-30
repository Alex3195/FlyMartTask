package org.alexkings.productdeliveryapi.repository;

import org.alexkings.productdeliveryapi.entities.BindCarriersToPlaces;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BindCarriersToPlaceRepository extends JpaRepository<BindCarriersToPlaces, Long> {
    List<BindCarriersToPlaces> findByPlaceIdAndRegionId(Long placeId, Long regionId);

    List<BindCarriersToPlaces> findByCarrierId(Long carrierId);
}
