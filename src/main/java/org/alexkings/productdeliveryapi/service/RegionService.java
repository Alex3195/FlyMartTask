package org.alexkings.productdeliveryapi.service;

import org.alexkings.productdeliveryapi.model.RegionDto;

import java.util.List;

public interface RegionService {
    RegionDto createRegion(RegionDto region);

    List<RegionDto> getAllRegions();

    RegionDto getRegionById(Long id);

    RegionDto updateRegion(Long id, RegionDto updatedRegion);

    void deleteRegion(Long id);
}
