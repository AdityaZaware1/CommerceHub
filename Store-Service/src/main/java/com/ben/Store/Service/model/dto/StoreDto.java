package com.ben.Store.Service.model.dto;

import com.ben.Store.Service.enums.StoreStatus;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
