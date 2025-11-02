package com.ben.Inventory.Service.external.resillence4j;

import com.ben.Inventory.Service.external.BranchService;
import com.ben.Inventory.Service.model.dto.BranchDto;
import org.springframework.stereotype.Component;

@Component
public class BranchFallBack implements BranchService {
    @Override
    public BranchDto getBranch(Long branchId) {
        return new BranchDto();
    }
}
