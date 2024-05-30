package org.alexkings.productdeliveryapi.repository;

import org.alexkings.productdeliveryapi.entities.Requests;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Requests, Long> {
    Requests findByRequestCode(String requestCode);

    Requests findByCreatedBy(Long userId);

    void deleteByRequestCode(String requestCode);
}
