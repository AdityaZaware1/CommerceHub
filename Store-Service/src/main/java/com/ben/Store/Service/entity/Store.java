package com.ben.Store.Service.entity;

import com.ben.Store.Service.enums.StoreStatus;
import com.ben.Store.Service.model.dto.UserDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Store {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;

    private String storeName;
    private String storeType;
    private String ownerName;
    private Long ownerId;

    @Enumerated(EnumType.STRING)
    private StoreStatus status = StoreStatus.PENDING;

    @ElementCollection
    private List<UserDto> employees;
}
