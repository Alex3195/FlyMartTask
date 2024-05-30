package org.alexkings.productdeliveryapi.service.impl;

import org.alexkings.productdeliveryapi.entities.Requests;
import org.alexkings.productdeliveryapi.model.RequestDto;
import org.alexkings.productdeliveryapi.repository.RequestRepository;
import org.alexkings.productdeliveryapi.service.RequestService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {
    private final RequestRepository requestRepository;

    public RequestServiceImpl(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @Override
    public RequestDto saveRequest(RequestDto request) {
        Requests entity = dtoToEntity(request);
        return entityToDto(requestRepository.save(entity));
    }

    @Override
    public RequestDto getRequest(String requestCode) {
        Requests entity = requestRepository.findByRequestCode(requestCode);
        if (entity == null) {
            return null;
        }
        return entityToDto(entity);
    }

    @Override
    public void deleteRequest(Long id) {
        requestRepository.deleteById(id);
    }

    @Override
    public RequestDto updateRequest(RequestDto request, Long id) {
        Requests e = requestRepository.getReferenceById(id);
        if (e == null) {
            return null;
        }
        e.setPlaceName(request.getPlaceName());
        e.setProductCode(request.getProductCode());
        e.setPlaceName(request.getPlaceName());
        e.setUpdateDate(Date.from(Instant.now()));
        return entityToDto(e);
    }

    @Override
    public List<RequestDto> getAllRequests() {
        return requestRepository.findAll().stream().map(this::entityToDto).toList();
    }

    @Override
    public RequestDto getRequestById(Long id) {
        Requests e = requestRepository.getReferenceById(id);
        if (e == null) {
            return null;
        }
        return entityToDto(e);
    }

    private Requests dtoToEntity(RequestDto requestDto) {
        Requests requests = new Requests();
        requests.setId(requestDto.getId());
        requests.setRequestCode(requestDto.getRequestCode());
        requests.setProductCode(requestDto.getProductCode());
        requests.setPlaceName(requestDto.getPlaceName());
        requests.setCreatedDate(Date.from(Instant.now()));
        requests.setUpdateDate(Date.from(Instant.now()));
        return requests;
    }

    private RequestDto entityToDto(Requests request) {
        RequestDto requestDto = new RequestDto();
        requestDto.setId(request.getId());
        requestDto.setRequestCode(request.getRequestCode());
        requestDto.setProductCode(request.getProductCode());
        requestDto.setPlaceName(request.getPlaceName());
        return requestDto;
    }
}
