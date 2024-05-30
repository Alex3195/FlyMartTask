package org.alexkings.productdeliveryapi.service.impl;

import org.alexkings.productdeliveryapi.entities.Place;
import org.alexkings.productdeliveryapi.model.PlaceDto;
import org.alexkings.productdeliveryapi.repository.PlaceRepository;
import org.alexkings.productdeliveryapi.service.PlaceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaceServiceImpl implements PlaceService {
    private final PlaceRepository placeRepository;

    public PlaceServiceImpl(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    @Override
    public PlaceDto createPlace(PlaceDto place) {
        return entityToDto(placeRepository.save(dtoToEntity(place)));
    }

    @Override
    public List<PlaceDto> getAllPlaces() {
        return placeRepository.findAll().stream().map(this::entityToDto).toList();
    }

    @Override
    public Optional<PlaceDto> getPlaceById(Long id) {
        return Optional.of(entityToDto(placeRepository.getReferenceById(id)));
    }

    @Override
    public PlaceDto updatePlace(Long id, PlaceDto updatedPlace) {
        Place e = placeRepository.getReferenceById(id);
        if (e != null) {
            e.setName(updatedPlace.getName());
            e = placeRepository.save(e);
            return entityToDto(e);
        }
        return null;
    }

    @Override
    public void deletePlace(Long id) {
        placeRepository.deleteById(id);
    }

    private PlaceDto entityToDto(Place place) {
        PlaceDto placeDto = new PlaceDto();
        placeDto.setId(place.getId());
        placeDto.setName(place.getName());
        return placeDto;
    }

    private Place dtoToEntity(PlaceDto placeDto) {
        Place place = new Place();
        place.setId(placeDto.getId());
        place.setName(placeDto.getName());
        return place;
    }
}