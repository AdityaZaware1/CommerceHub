package com.ben.Store.Service.service;

import com.ben.Store.Service.exception.StoreException;
import com.ben.Store.Service.model.dto.StoreDto;
import com.ben.Store.Service.model.dto.UserDto;
import com.ben.Store.Service.model.request.StoreEmployee;
import com.ben.Store.Service.model.response.PaymentResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StoreService {

    StoreDto createStore(Long ownerId,StoreDto storeDto) throws StoreException;
    StoreDto updateStore(Long storeId, StoreDto storeDto) throws StoreException;
    StoreDto getStoreByName(String storeName) throws StoreException;
    StoreDto getStoreByOwnerId(Long ownerId) throws StoreException;
    void addEmployee(Long storeId, UserDto userDto) throws StoreException;
    PaymentResponse enbleSubScription(Long userId, Long subscriptionId) throws StoreException;
    StoreDto getStore(Long storeId) throws StoreException;
    void deleteStore(Long storeId) throws StoreException;

    StoreEmployee getAllEmployees(Long storeId) throws StoreException;
}
