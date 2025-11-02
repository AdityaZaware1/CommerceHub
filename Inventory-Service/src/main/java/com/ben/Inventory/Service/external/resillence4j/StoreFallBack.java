package com.ben.Inventory.Service.external.resillence4j;

import com.ben.Inventory.Service.external.StoreService;
import com.ben.Inventory.Service.model.dto.StoreDto;
import org.springframework.stereotype.Component;

@Component
public class StoreFallBack implements StoreService {

    @Override
    public StoreDto getStore(Long storeId) {
        return new StoreDto();
    }
}
