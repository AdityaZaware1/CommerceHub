package com.ben.Store.Service.controller;

import com.ben.Store.Service.exception.StoreException;
import com.ben.Store.Service.model.dto.StoreDto;
import com.ben.Store.Service.model.dto.UserDto;
import com.ben.Store.Service.model.request.StoreEmployee;
import com.ben.Store.Service.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/store")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @PostMapping("/create/{ownerId}")
    public ResponseEntity<StoreDto> createStore(
           @PathVariable Long ownerId, @RequestBody StoreDto storeDto) throws StoreException {
        return ResponseEntity.ok(storeService.createStore(ownerId, storeDto));
    }

    @PutMapping("/update/{storeId}")
    public ResponseEntity<StoreDto> updateStore(
            @PathVariable Long storeId, @RequestBody StoreDto storeDto) throws StoreException {
        return ResponseEntity.ok(storeService.updateStore(storeId, storeDto));
    }

    @DeleteMapping("/delete/{storeId}")
    public ResponseEntity<StoreDto> deleteStore(
            @PathVariable Long storeId) throws StoreException {
        storeService.deleteStore(storeId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get/{storeId}")
    public ResponseEntity<StoreDto> getStore(
            @PathVariable Long storeId) throws StoreException {
        return ResponseEntity.ok(storeService.getStore(storeId));
    }

    @GetMapping("/getByName/{storeName}")
    public ResponseEntity<StoreDto> getStoreByName(
            @PathVariable String storeName) throws StoreException {
        return ResponseEntity.ok(storeService.getStoreByName(storeName));
    }

    @GetMapping("/getByOwnerId/{ownerId}")
    public ResponseEntity<StoreDto> getStoreByOwnerId(
            @PathVariable Long ownerId) throws StoreException {
        return ResponseEntity.ok(storeService.getStoreByOwnerId(ownerId));
    }

    @PutMapping("/addEmployee/{storeId}")
    public ResponseEntity<StoreDto> addEmployee(
            @PathVariable Long storeId,
            @RequestBody UserDto userDto) throws StoreException {
        storeService.addEmployee(storeId, userDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/storeId/{storeId}/employees")
    public ResponseEntity<StoreEmployee> getAllStoreEmployees(
            @PathVariable Long storeId) throws StoreException {
        return ResponseEntity.ok(storeService.getAllEmployees(storeId));
    }

}
