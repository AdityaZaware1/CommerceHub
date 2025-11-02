package com.ben.Inventory.Service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String quantity;

    private String description;

    private Double price;

    private Double sellingPrice;

    private Double brand;

    @ManyToOne
    private Category category;

    private Long storeId;
    private Long branchId;
}
