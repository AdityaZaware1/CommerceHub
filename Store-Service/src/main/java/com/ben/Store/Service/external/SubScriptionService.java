package com.ben.Store.Service.external;

import com.ben.Store.Service.external.resillence4j.SubscriptionFallback;
import com.ben.Store.Service.model.dto.SubScription;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Subscription-Service",
            url = "${subscription.service.url}",
            fallback = SubscriptionFallback.class)
public interface SubScriptionService {

    @GetMapping("/get/{id}")
    public SubScription getSubScription(@PathVariable Long id);
}
