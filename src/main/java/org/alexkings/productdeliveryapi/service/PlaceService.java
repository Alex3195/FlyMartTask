package org.alexkings.productdeliveryapi.service;

import org.alexkings.productdeliveryapi.model.PlaceDto;

import java.util.List;
import java.util.Optional;

public interface PlaceService {
    PlaceDto createPlace(PlaceDto place);

    List<PlaceDto> getAllPlaces();

    Optional<PlaceDto> getPlaceById(Long id);

    PlaceDto updatePlace(Long id, PlaceDto updatedPlace);

    void deletePlace(Long id);
}
