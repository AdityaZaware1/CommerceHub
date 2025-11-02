package com.ben.Store.Service.repo;

import com.ben.Store.Service.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepo extends JpaRepository<Store, Long> {
    Store findByStoreName(String storeName);

    Store findByOwnerId(Long ownerId);
}
