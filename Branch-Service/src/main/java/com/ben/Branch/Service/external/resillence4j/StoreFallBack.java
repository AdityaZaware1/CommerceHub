package com.ben.Branch.Service.external.resillence4j;

import com.ben.Branch.Service.external.StoreService;
import com.ben.Branch.Service.model.dto.StoreDto;
import org.springframework.stereotype.Component;

@Component
public class StoreFallBack implements StoreService {
    @Override
    public StoreDto getStoreByOwnerId(Long ownerId) {
        return new StoreDto();
    }
}
