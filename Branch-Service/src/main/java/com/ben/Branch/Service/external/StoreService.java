package com.ben.Branch.Service.external;

import com.ben.Branch.Service.external.resillence4j.StoreFallBack;
import com.ben.Branch.Service.model.dto.StoreDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Store-Service",
            url = "${store.service.url}",
            fallback = StoreFallBack.class)
public interface StoreService {

    @GetMapping("/getByOwnerId/{ownerId}")
    public StoreDto getStoreByOwnerId(@PathVariable Long ownerId);
}
