package com.ben.Order.Service.repo;

import com.ben.Order.Service.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepo extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long userId);

    List<Order> findByBranchId(Long branchId);
}
