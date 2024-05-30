package org.alexkings.productdeliveryapi.controller;

import org.alexkings.productdeliveryapi.model.RequestDto;
import org.alexkings.productdeliveryapi.service.RequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/requests")
public class RequestController {
    private final RequestService requestService;

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping("/getRequests")
    @PreAuthorize("hasPermission('REQUEST_READ') and hasAnyRole('ADMIN','CARRIER')")
    public ResponseEntity<List<RequestDto>> getRequests() {
        return ResponseEntity.ok(requestService.getAllRequests());
    }

    @GetMapping("/getRequestById/{id}")
    @PreAuthorize("hasPermission('REQUEST_READ') and hasAnyRole('ADMIN','CARRIER')")
    public ResponseEntity<RequestDto> getRequestById(@PathVariable Long id) {
        return ResponseEntity.ok(requestService.getRequestById(id));
    }

    @PostMapping("/add")
    @PreAuthorize("hasPermission('REQUEST_CREATE') and hasAnyRole('ADMIN','CARRIER')")
    public ResponseEntity<RequestDto> addRequest(@RequestBody RequestDto requestDto) {
        return ResponseEntity.ok(requestService.saveRequest(requestDto));
    }

    @PutMapping("/edit/{id}")
    @PreAuthorize("hasPermission('REQUEST_EDIT') and hasAnyRole('ADMIN','CARRIER')")
    public ResponseEntity<RequestDto> editRequest(@PathVariable Long id, @RequestBody RequestDto requestDto) {
        return ResponseEntity.ok(requestService.updateRequest(requestDto, id));
    }

    @DeleteMapping("/deleteById/{id}")
    @PreAuthorize("hasPermission('REQUEST_DELETE') and hasAnyRole('ADMIN','CARRIER')")
    public ResponseEntity<String> deleteRequest(@PathVariable Long id) {
        requestService.deleteRequest(id);
        return ResponseEntity.ok("Request deleted by id:" + id);
    }
}
