package com.ben.Inventory.Service.model.dto;

import com.ben.Inventory.Service.enums.StoreStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreDto {

    private Long id;
    private String storeName;
    private String storeType;
    private String ownerName;
    private String ownerEmail;
    private StoreStatus status;
}
