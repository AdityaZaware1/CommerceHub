package com.ben.Inventory.Service.external;

import com.ben.Inventory.Service.external.resillence4j.StoreFallBack;
import com.ben.Inventory.Service.model.dto.StoreDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Store-Service", url = "${store.service.url}", fallback = StoreFallBack.class)
public interface StoreService {

    @GetMapping("/get/{storeId}")
    public StoreDto getStore(@PathVariable Long storeId);
}
