package org.alexkings.productdeliveryapi.controller;

import jakarta.validation.Valid;
import org.alexkings.productdeliveryapi.model.PlaceDto;
import org.alexkings.productdeliveryapi.service.PlaceService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/places")
public class PlaceController {

    private final PlaceService placeService;

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @PostMapping("/addPlace")
    @PreAuthorize("hasRole('ADMIN') and hasPermission('PLACE_CREATE')")
    public ResponseEntity<PlaceDto> createPlace(@RequestBody @Valid PlaceDto place) {
        PlaceDto savedPlace = placeService.createPlace(place);
        return ResponseEntity.ok(savedPlace);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PlaceDto>> getAllPlaces() {
        List<PlaceDto> places = placeService.getAllPlaces();
        return ResponseEntity.ok(places);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlaceDto> getPlaceById(@PathVariable Long id) {
        Optional<PlaceDto> place = placeService.getPlaceById(id);
        return place.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMION') and hasPermission('PLACE_EDIT')")
    public ResponseEntity<PlaceDto> updatePlace(@PathVariable Long id,@Valid @RequestBody PlaceDto place) {
        PlaceDto updatedPlace = placeService.updatePlace(id, place);
        return ResponseEntity.ok(updatedPlace);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') and hasPermission('PLACE_DELETE')")
    public ResponseEntity<Void> deletePlace(@PathVariable Long id) {
        placeService.deletePlace(id);
        return ResponseEntity.noContent().build();
    }
}
