package com.ben.Store.Service.kafka;

import com.ben.Store.Service.entity.Store;
import com.ben.Store.Service.enums.StoreStatus;
import com.ben.Store.Service.model.dto.StoreDto;
import com.ben.Store.Service.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumer {

    private final StoreService storeService;

    @KafkaListener(topics = "payment-topic", groupId = "store-group")
    public void enableSubscription(String storeId) {

        Long sId = Long.parseLong(storeId);
        StoreDto store = storeService.getStore(sId);
        store.setStatus(StoreStatus.ACTIVE);
        storeService.updateStore(sId, store);
    }
}
