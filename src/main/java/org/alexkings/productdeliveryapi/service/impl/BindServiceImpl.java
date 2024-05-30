package org.alexkings.productdeliveryapi.service.impl;

import org.alexkings.productdeliveryapi.entities.BindCarriersToPlaces;
import org.alexkings.productdeliveryapi.entities.BindRoleToPermission;
import org.alexkings.productdeliveryapi.entities.Place;
import org.alexkings.productdeliveryapi.repository.BindCarriersToPlaceRepository;
import org.alexkings.productdeliveryapi.repository.BindPermissionToRoleRepository;
import org.alexkings.productdeliveryapi.repository.PlaceRepository;
import org.alexkings.productdeliveryapi.service.BindService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BindServiceImpl implements BindService {
    private final BindCarriersToPlaceRepository bindCarriersToPlaceRepository;
    private final BindPermissionToRoleRepository bindPermissionToRoleRepository;
    private final PlaceRepository placeRepository;

    public BindServiceImpl(BindCarriersToPlaceRepository placeRepository, BindPermissionToRoleRepository bindPermissionToRoleRepository, PlaceRepository placeRepository1) {
        this.bindCarriersToPlaceRepository = placeRepository;
        this.bindPermissionToRoleRepository = bindPermissionToRoleRepository;
        this.placeRepository = placeRepository1;
    }

    @Override
    public void bindPermissionsToRole(Long roleId, List<Long> permissionIds) {
        permissionIds.forEach(i -> {
            BindRoleToPermission bindRoleToPermission = new BindRoleToPermission();
            bindRoleToPermission.setRoleId(roleId);
            bindRoleToPermission.setPermissionId(i);
            bindPermissionToRoleRepository.save(bindRoleToPermission);
        });
    }

    @Override
    public void bindPlacesToCarrier(Long carrierId, List<Long> placeIds) {
        placeIds.forEach(i -> {
            BindCarriersToPlaces bindCarriersToPlaces = new BindCarriersToPlaces();
            bindCarriersToPlaces.setCarrierId(carrierId);
            Place place = placeRepository.getReferenceById(i);
            bindCarriersToPlaces.setPlaceId(i);
            bindCarriersToPlaces.setRegionId(place.getRegion().getId());
            bindCarriersToPlaceRepository.save(bindCarriersToPlaces);
        });
    }
}
