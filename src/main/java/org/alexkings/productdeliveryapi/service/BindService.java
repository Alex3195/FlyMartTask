package org.alexkings.productdeliveryapi.service;

import java.util.List;

public interface BindService {
    void bindPermissionsToRole(Long roleId, List<Long> permissionIds);
    void bindPlacesToCarrier(Long carrierId, List<Long> paceIds);
}
