package com.ben.Access.Service.controller;

import com.ben.Access.Service.entity.SubScription;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/subscription")
public class SubScriptionController {


    @PostMapping("/create")
    public ResponseEntity<SubScription> createSubScription(
            @RequestBody SubScription subScription) {
        return ResponseEntity.ok(subScription);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<SubScription> getSubScription(@PathVariable Long id) {
        return ResponseEntity.ok(new SubScription());
    }

    @GetMapping("/getAll")
    public ResponseEntity<SubScription> getAllSubScription() {
        return ResponseEntity.ok(new SubScription());
    }
}
