package com.ben.Store.Service.mapper;

import com.ben.Store.Service.entity.Store;
import com.ben.Store.Service.model.dto.StoreDto;
import com.ben.Store.Service.model.request.StoreEmployee;

public class StoreMapper {

    public static StoreDto toDto(Store store) {
        return StoreDto.builder()
                .id(store.getId())
                .storeName(store.getStoreName())
                .storeType(store.getStoreType())
                .ownerName(store.getOwnerName())
                .status(store.getStatus())
                .build();
    }

    public static Store toEntity(StoreDto storeDto) {
        return Store.builder()
                .id(storeDto.getId())
                .storeName(storeDto.getStoreName())
                .storeType(storeDto.getStoreType())
                .ownerName(storeDto.getOwnerName())
                .status(storeDto.getStatus())
                .build();
    }

    public static StoreEmployee toStoreEmployee(Store storeDto) {
        return StoreEmployee.builder()
                .storeId(storeDto.getId())
                .storeName(storeDto.getStoreName())
                .employees(storeDto.getEmployees())
                .build();
    }
}
