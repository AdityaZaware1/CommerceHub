package com.ben.Store.Service.model.request;

import com.ben.Store.Service.model.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreEmployee {

    private Long storeId;
    private String storeName;
    private List<UserDto> employees;
}
