package org.alexkings.productdeliveryapi.service.impl;

import org.alexkings.productdeliveryapi.entities.Place;
import org.alexkings.productdeliveryapi.entities.Region;
import org.alexkings.productdeliveryapi.model.PlaceDto;
import org.alexkings.productdeliveryapi.model.RegionDto;
import org.alexkings.productdeliveryapi.repository.RegionRepository;
import org.alexkings.productdeliveryapi.service.RegionService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
public class RegionServiceImpl implements RegionService {
    private final RegionRepository regionRepository;

    public RegionServiceImpl(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    @Override
    public RegionDto createRegion(RegionDto region) {
        return regionEntityToDto(regionRepository.save(regionDtoToEntity(region)));
    }

    @Override
    public List<RegionDto> getAllRegions() {
        return regionRepository.findAll().stream().map(this::regionEntityToDto).toList();
    }

    @Override
    public RegionDto getRegionById(Long id) {
        return regionEntityToDto(regionRepository.getReferenceById(id));
    }

    @Override
    public RegionDto updateRegion(Long id, RegionDto updatedRegion) {
        return regionRepository.findById(id)
                .map(region -> {
                    region.setName(updatedRegion.getName());
                    return regionEntityToDto(regionRepository.save(region));
                })
                .orElseGet(() -> {
                    updatedRegion.setId(id);
                    return regionEntityToDto(regionRepository.save(regionDtoToEntity(updatedRegion)));
                });
    }

    @Override
    public void deleteRegion(Long id) {
        regionRepository.deleteById(id);
    }

    @Override
    public List<RegionDto> getRegionsAndPlaces() {
        List<Region> regions = regionRepository.findAll();
        List<RegionDto> result = regions.stream().sorted(new Comparator<Region>() {
            @Override
            public int compare(Region o1, Region o2) {
                return Math.toIntExact(o2.getId() - o1.getId());
            }
        }).map(i -> {
            List<Place> places =
                    i.getPlaces().stream().sorted(new Comparator<Place>() {
                        @Override
                        public int compare(Place o1, Place o2) {
                            return Math.toIntExact(o1.getId() - o2.getId());
                        }
                    }).toList();
            i.setPlaces(places);
            return regionEntityToDto(i);
        }).toList();
        return List.of();
    }

    private RegionDto regionEntityToDto(Region region) {
        RegionDto regionDto = new RegionDto();
        regionDto.setId(region.getId());
        regionDto.setName(region.getName());
        regionDto.setPlaces(region.getPlaces().stream().map(this::placeEntityToDto).toList());
        region.setUpdateDate(Date.from(Instant.now()));
        return regionDto;
    }

    private PlaceDto placeEntityToDto(Place place) {
        PlaceDto placeDto = new PlaceDto();
        placeDto.setId(place.getId());
        placeDto.setName(place.getName());

        return placeDto;
    }

    private Place placeDtoToPlace(PlaceDto placeDto) {
        Place place = new Place();
        place.setId(placeDto.getId());
        place.setName(placeDto.getName());
        return place;
    }

    private Region regionDtoToEntity(RegionDto regionDto) {
        Region region = new Region();
        region.setId(regionDto.getId());
        region.setName(regionDto.getName());
        region.setCreatedDate(Date.from(Instant.now()));
        region.setUpdateDate(Date.from(Instant.now()));
        List<Place> placeDtos = regionDto.getPlaces().stream().map(this::placeDtoToPlace).toList();
        region.setPlaces(placeDtos);
        return region;
    }
}
