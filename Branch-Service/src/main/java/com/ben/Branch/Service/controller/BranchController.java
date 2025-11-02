package com.ben.Branch.Service.controller;

import com.ben.Branch.Service.exception.BranchException;
import com.ben.Branch.Service.model.dto.BranchDto;
import com.ben.Branch.Service.service.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/branch")
public class BranchController {

    private final BranchService branchService;

    @PostMapping("/create/{ownerId}")
    public ResponseEntity<BranchDto> createBranch(
            @RequestBody BranchDto branchDto,
            @PathVariable Long ownerId) throws BranchException {
        return ResponseEntity.ok(branchService.createBranch(branchDto, ownerId));
    }

    @GetMapping("/get/{branchId}")
    public ResponseEntity<BranchDto> getBranch(
            @PathVariable Long branchId) throws BranchException {
        return ResponseEntity.ok(branchService.getBranch(branchId));
    }

    @PutMapping("/update/{branchId}/{ownerId}")
    public ResponseEntity<BranchDto> updateBranch(
            @PathVariable Long branchId,
            @RequestBody BranchDto branchDto,
            @PathVariable Long ownerId) throws BranchException {
        return ResponseEntity.ok(branchService.updateBranch(branchId, branchDto, ownerId));
    }

    @GetMapping("/getByStoreId/{storeId}")
    public ResponseEntity<List<BranchDto>> getAllBranchesByStoreId(
            @PathVariable Long storeId) throws BranchException {
        return ResponseEntity.ok(branchService.getAllBranchesByStoreId(storeId));
    }
}
