package com.ben.Inventory.Service.external;

import com.ben.Inventory.Service.external.resillence4j.BranchFallBack;
import com.ben.Inventory.Service.model.dto.BranchDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Branch-Service", url = "${branch.service.url}", fallback = BranchFallBack.class)
public interface BranchService {

    @GetMapping("/get/{branchId}")
    public BranchDto getBranch(@PathVariable Long branchId);
}
