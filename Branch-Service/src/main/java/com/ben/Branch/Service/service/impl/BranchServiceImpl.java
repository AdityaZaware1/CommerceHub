package com.ben.Branch.Service.service.impl;

import com.ben.Branch.Service.entity.Branch;
import com.ben.Branch.Service.enums.UserRole;
import com.ben.Branch.Service.exception.BranchException;
import com.ben.Branch.Service.external.StoreService;
import com.ben.Branch.Service.external.UserService;
import com.ben.Branch.Service.mapper.BranchMapper;
import com.ben.Branch.Service.model.dto.BranchDto;
import com.ben.Branch.Service.model.dto.StoreDto;
import com.ben.Branch.Service.model.dto.UserDto;
import com.ben.Branch.Service.repo.BranchRepo;
import com.ben.Branch.Service.service.BranchService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {

    private final BranchRepo branchRepo;
    private final StoreService storeService;
    private final UserService userService;

    @Override
    public BranchDto createBranch(BranchDto branchDto, Long userId) throws BranchException {

        StoreDto storeDto = getStoreByOwnerId(userId);

        if(storeDto == null) {
            throw new BranchException("Store not found or user is not a store admin");
        }

        Branch branch = BranchMapper.toEntity(branchDto);
        branch.setStoreId(storeDto.getId());

        Branch savedBranch = branchRepo.save(branch);

        return BranchMapper.toDto(savedBranch);
    }

    @Override
    public BranchDto getBranch(Long branchId) throws BranchException {

        Branch branch = branchRepo.findById(branchId)
                .orElseThrow(() -> new BranchException("Branch not found"));

        return BranchMapper.toDto(branch);
    }

    @Override
    public List<BranchDto> getAllBranchesByStoreId(Long storeId) throws BranchException {

        List<Branch> branches = branchRepo.findByStoreId(storeId);

        if(!branches.isEmpty()) {
            throw new BranchException("Branches not found");
        }

        boolean currentUser = getCurrentUser().getRole() == UserRole.ADMIN || getCurrentUser().getRole() == UserRole.STORE_ADMIN;

        if (!currentUser) {
            throw new BranchException("User is not an admin or store admin");
        }

        return BranchMapper.toDtoList(branches);
    }

    @Override
    public BranchDto updateBranch(Long branchId, BranchDto branchDto, Long userId) throws BranchException {

        Branch branch = branchRepo.findById(branchId)
                .orElseThrow(() -> new BranchException("Branch not found"));

        boolean currentUser = getCurrentUser().getRole() == UserRole.ADMIN || getCurrentUser().getRole() == UserRole.STORE_ADMIN;

        if (!currentUser) {
            throw new BranchException("User is not an admin or store admin");
        }

        branch.setName(branchDto.getName());
        branch.setAddress(branchDto.getAddress());
        branch.setPhone(branchDto.getPhone());
        branch.setEmail(branchDto.getEmail());
        branch.setOpenTime(branchDto.getOpenTime());
        branch.setCloseTime(branchDto.getCloseTime());

        Branch savedBranch = branchRepo.save(branch);

        if (savedBranch != null) {
            return BranchMapper.toDto(savedBranch);
        }

        return null;
    }

    @Override
    public void deleteBranch(Long branchId) throws BranchException {

        Branch branch = branchRepo.findById(branchId)
                .orElseThrow(() -> new BranchException("Branch not found"));

        boolean currentUser = getCurrentUser().getRole() == UserRole.ADMIN || getCurrentUser().getRole() == UserRole.STORE_ADMIN;

        if (!currentUser) {
            throw new BranchException("User is not an admin or store admin");
        }

        branchRepo.delete(branch);
    }

    @CircuitBreaker(name = "storeService", fallbackMethod = "StoreFallBacks")
    public StoreDto getStoreByOwnerId(Long ownerId) {
        StoreDto storeDto = storeService.getStoreByOwnerId(ownerId);

        if (storeDto.getId() == 0l) {
            throw new RuntimeException("Store Service is not available");
        }

        return storeDto;
    }

    @CircuitBreaker(name = "userService", fallbackMethod = "UserFallBacks")
    public UserDto getCurrentUser() {

        UserDto user = userService.getCurrentUser();

        if(user.getId() == 0l) {
            throw new RuntimeException("User Service is not available");
        }

        return user;
    }

    public StoreDto StoreFallBacks(Long ownerId) {
        return new StoreDto(0l, null, null, null, null);
    }

    public UserDto UserFallBacks() {
        return new UserDto(0l,null,null,null,null);
    }
}
