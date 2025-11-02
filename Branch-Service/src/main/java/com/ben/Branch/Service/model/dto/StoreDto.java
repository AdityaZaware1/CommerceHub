package com.ben.Branch.Service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreDto {

    private Long id;
    private String storeName;
    private String storeType;
    private String ownerName;
    private String ownerEmail;
}
