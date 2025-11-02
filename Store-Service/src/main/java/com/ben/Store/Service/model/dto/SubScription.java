package com.ben.Store.Service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubScription {
    private Long id;
    private Long plan;
    private Long planType;
}
