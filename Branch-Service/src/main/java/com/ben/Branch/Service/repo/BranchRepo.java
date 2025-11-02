package com.ben.Branch.Service.repo;


import com.ben.Branch.Service.entity.Branch;
import com.ben.Branch.Service.model.dto.BranchDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BranchRepo extends JpaRepository<Branch, Long> {
    List<Branch> findByStoreId(Long storeId);
}
