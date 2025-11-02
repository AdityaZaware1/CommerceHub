package com.ben.Store.Service.external.resillence4j;

import com.ben.Store.Service.external.SubScriptionService;
import com.ben.Store.Service.model.dto.SubScription;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionFallback implements SubScriptionService {

    @Override
    public SubScription getSubScription(Long id) {
        return new SubScription();
    }
}
