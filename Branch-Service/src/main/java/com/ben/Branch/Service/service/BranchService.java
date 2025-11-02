package com.ben.Branch.Service.service;

import com.ben.Branch.Service.exception.BranchException;
import com.ben.Branch.Service.model.dto.BranchDto;

import java.util.List;

public interface BranchService {

    BranchDto createBranch(BranchDto branchDto, Long userId) throws BranchException;

    BranchDto getBranch(Long branchId) throws BranchException;

    List<BranchDto> getAllBranchesByStoreId(Long storeId) throws BranchException;

    BranchDto updateBranch(Long branchId, BranchDto branchDto, Long userId) throws BranchException;


    void deleteBranch(Long branchId) throws BranchException;
}
