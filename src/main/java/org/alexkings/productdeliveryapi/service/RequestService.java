package org.alexkings.productdeliveryapi.service;

import org.alexkings.productdeliveryapi.model.RequestDto;

import java.util.List;

public interface RequestService {
    RequestDto saveRequest(RequestDto request);

    RequestDto getRequest(String id);

    void deleteRequest(Long id);

    RequestDto updateRequest(RequestDto request, Long requestCode);

    List<RequestDto> getAllRequests();


    RequestDto getRequestById(Long id);
}
