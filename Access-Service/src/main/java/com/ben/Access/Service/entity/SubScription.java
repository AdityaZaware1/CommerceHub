package com.ben.Access.Service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class SubScription {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long plan;
    private Long planType;
}
