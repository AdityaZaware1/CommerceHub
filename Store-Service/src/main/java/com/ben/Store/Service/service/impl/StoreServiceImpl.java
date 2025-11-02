package com.ben.Store.Service.service.impl;

import com.ben.Store.Service.entity.Store;
import com.ben.Store.Service.enums.UserRole;
import com.ben.Store.Service.exception.StoreException;
import com.ben.Store.Service.external.AuthService;
import com.ben.Store.Service.external.PaymentService;
import com.ben.Store.Service.external.SubScriptionService;
import com.ben.Store.Service.external.UserService;
import com.ben.Store.Service.mapper.StoreMapper;
import com.ben.Store.Service.model.dto.PaymentDto;
import com.ben.Store.Service.model.dto.StoreDto;
import com.ben.Store.Service.model.dto.SubScription;
import com.ben.Store.Service.model.dto.UserDto;
import com.ben.Store.Service.model.request.StoreEmployee;
import com.ben.Store.Service.model.response.AuthResponse;
import com.ben.Store.Service.model.response.PaymentResponse;
import com.ben.Store.Service.repo.StoreRepo;
import com.ben.Store.Service.service.StoreService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepo storeRepo;
    private final UserService userService;
    private final AuthService authService;
    private final PaymentService paymentService;
    private final SubScriptionService service;

    @Override
    public StoreDto createStore(Long ownerId, StoreDto storeDto) throws StoreException {

        Store store = storeRepo.findByStoreName(storeDto.getStoreName());

        if(store != null) {
            throw new StoreException("Store already exists");
        }

        UserDto userDto = getUserById(ownerId);

        if (userDto.getRole() != UserRole.STORE_ADMIN) {
            throw new StoreException("User is not a store admin");
        }
        store = StoreMapper.toEntity(storeDto);
        storeRepo.save(store);
        return StoreMapper.toDto(store);
    }

    @Override
    public StoreDto updateStore(Long storeId,StoreDto storeDto) throws StoreException {

        Store store = storeRepo.findById(storeId).orElse(null);

        if(store == null) {
            throw new StoreException("Store not found");
        }
        store = StoreMapper.toEntity(storeDto);
        storeRepo.save(store);
        return StoreMapper.toDto(store);
    }

    @Override
    public StoreDto getStoreByName(String storeName) throws StoreException {

        Store store = storeRepo.findByStoreName(storeName);

        if(store == null) {
            throw new StoreException("Store not found");
        }
        return StoreMapper.toDto(store);
    }

    @Override
    public StoreDto getStoreByOwnerId(Long ownerId) throws StoreException {

        Store store = storeRepo.findByOwnerId(ownerId);

        if(store == null) {
            throw new StoreException("Store not found");
        }
        return StoreMapper.toDto(store);
    }

    @Override
    public void addEmployee(Long storeId, UserDto userDto) throws StoreException {

        AuthResponse response = registerUser(userDto);

        if(!response.getMessage().equals("User registered successfully")) {
            throw new StoreException("Please try again");
        }

        Store store = storeRepo.findById(storeId).orElse(null);

        if(store == null) {
            throw new StoreException("Store not found");
        }

        store.getEmployees().add(userDto);
    }

    @Override
    public PaymentResponse enbleSubScription(Long userId, Long subscriptionId) throws StoreException {

        UserDto userDto = getUserById(userId);

        StoreDto storeDto = getStoreByOwnerId(userDto.getId());

        if (storeDto == null) {
            throw new StoreException("Store not found");
        }

        SubScription subScription = getSubScription(subscriptionId);

        if (subScription == null) {
            throw new StoreException("SubScription not found");
        }

        PaymentDto paymentDto = PaymentDto.builder()
                .userId(userId)
                .orderId(storeDto.getId())
                .amount(subScription.getPlan())
                .build();

        PaymentResponse paymentResponse = createPayment(paymentDto);

        return paymentResponse;
    }

    @Override
    public StoreDto getStore(Long storeId) throws StoreException {

        Store store = storeRepo.findById(storeId).orElse(null);

        if(store == null) {
            throw new StoreException("Store not found");
        }
        return StoreMapper.toDto(store);
    }

    @Override
    public void deleteStore(Long storeId) throws StoreException {

        Store store = storeRepo.findById(storeId).orElse(null);

        if(store == null) {
            throw new StoreException("Store not found");
        }
        storeRepo.delete(store);
    }

    @Override
    public StoreEmployee getAllEmployees(Long storeId) throws StoreException {

        Store store = storeRepo.findById(storeId).orElse(null);

        if(store == null) {
            throw new StoreException("Store not found");
        }

        StoreEmployee storeEmployee = StoreMapper.toStoreEmployee(store);

        return storeEmployee;
    }

    @CircuitBreaker(name = "userService", fallbackMethod = "UserFallBack")
    public UserDto getUserById(Long id) throws StoreException {

        UserDto userDto = userService.getUserById(id);

        if(userDto == null) {
            throw new StoreException("Service not available");
        }

        return userService.getUserById(id);
    }

    @CircuitBreaker(name = "userService", fallbackMethod = "UserFallBack")
    public AuthResponse registerUser(UserDto userDto) {
        return authService.register(userDto);
    }

    @CircuitBreaker(name = "paymentService", fallbackMethod = "PayFallBack")
    public PaymentResponse createPayment(PaymentDto paymentDto) {

        PaymentResponse paymentResponse = paymentService.createPayment(paymentDto);

        if (paymentResponse.getPaymentUrl().equals("Payment Failed")) {
            throw new RuntimeException("Service in not available");
        }

        return paymentResponse;
    }

    @CircuitBreaker(name = "subScriptionService", fallbackMethod = "SubScriFallBack")
    public SubScription getSubScription(Long id) {

        SubScription subScription = service.getSubScription(id);

        if (subScription.getId() == 0l) {
            throw new RuntimeException("Service in not available");
        }

        return subScription;
    }

    public PaymentResponse PayFallBack(PaymentDto paymentDto) {

        return new PaymentResponse("Payment Failed", "Failed");
    }

    public SubScription SubScriFallBack(Long id) {

        return new SubScription(0l, 0l, 0l);
    }
}
