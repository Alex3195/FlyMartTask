package org.alexkings.productdeliveryapi.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.alexkings.productdeliveryapi.model.RegionDto;
import org.alexkings.productdeliveryapi.service.RegionService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/regions")
@PreAuthorize("hasAnyRole('ADMIN') and hasPermission('')")
public class RegionController {

    private final RegionService regionService;

    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @PostMapping("/addRegion")
    @PreAuthorize("hasRole('ADMIN') and hasPermission('REGION_CREATE') and hasPermission('PLACE_CREATE')")
    public ResponseEntity<RegionDto> createRegion(@RequestBody RegionDto region) {
        RegionDto savedRegion = regionService.createRegion(region);
        return ResponseEntity.ok(savedRegion);
    }

    @GetMapping
    public ResponseEntity<List<RegionDto>> getAllRegions() {
        List<RegionDto> regions = regionService.getAllRegions();
        return ResponseEntity.ok(regions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegionDto> getRegionById(@PathVariable Long id) {
        RegionDto region = regionService.getRegionById(id);
        return ResponseEntity.ok(region);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') and hasPermission('REGION_EDIT')")
    public ResponseEntity<RegionDto> updateRegion(@PathVariable Long id, @RequestBody RegionDto region) {
        RegionDto updatedRegion = regionService.updateRegion(id, region);
        return ResponseEntity.ok(updatedRegion);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMION') and hasPermission('REGION_DELETE')")
    public ResponseEntity<Void> deleteRegion(@PathVariable Long id) {
        regionService.deleteRegion(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getRegionsAndPlaces")
    public ResponseEntity<List<RegionDto>> getAllRegionsAndPlaces(HttpServletRequest request) {
        return ResponseEntity.ok(regionService.getRegionsAndPlaces());
    }
}
