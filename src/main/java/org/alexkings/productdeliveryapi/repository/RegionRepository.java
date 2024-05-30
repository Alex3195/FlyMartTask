package org.alexkings.productdeliveryapi.repository;

import org.alexkings.productdeliveryapi.entities.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region, Long> {
}
