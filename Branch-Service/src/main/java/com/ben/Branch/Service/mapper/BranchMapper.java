package com.ben.Branch.Service.mapper;

import com.ben.Branch.Service.entity.Branch;
import com.ben.Branch.Service.model.dto.BranchDto;

import java.util.List;

public class BranchMapper{

    public static BranchDto toDto(Branch branch) {

        return BranchDto.builder()
            .id(branch.getId())
                .name(branch.getName())
                .address(branch.getAddress())
                .phone(branch.getPhone())
                .email(branch.getEmail())
                .openTime(branch.getOpenTime())
                .closeTime(branch.getCloseTime())
                .createdAt(branch.getCreatedAt())
                .updatedAt(branch.getUpdatedAt())
                .storeId(branch.getStoreId())
                .managerId(branch.getManagerId())
                .build();

    }

    public static Branch toEntity(BranchDto branchDto) {

        return Branch.builder()
            .id(branchDto.getId())
                .name(branchDto.getName())
                .address(branchDto.getAddress())
                .phone(branchDto.getPhone())
                .email(branchDto.getEmail())
                .openTime(branchDto.getOpenTime())
                .closeTime(branchDto.getCloseTime())
                .createdAt(branchDto.getCreatedAt())
                .updatedAt(branchDto.getUpdatedAt())
                .storeId(branchDto.getStoreId())
                .managerId(branchDto.getManagerId())
                .build();

    }

    public static List<BranchDto> toDtoList(List<Branch> branches) {

        return branches.stream().map(BranchMapper::toDto).toList();
    }
}
