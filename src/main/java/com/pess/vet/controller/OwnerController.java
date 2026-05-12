package com.pess.vet.controller;

import com.pess.vet.dto.OwnerRequest;
import com.pess.vet.dto.OwnerResponse;
import com.pess.vet.service.OwnerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/owners")
public class OwnerController {

    public OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @PostMapping
    public ResponseEntity<Void> addOwner(@RequestBody @Valid OwnerRequest request){
        ownerService.addOwner(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{ownerId}/edit")
    public ResponseEntity<OwnerResponse> editOwnerAttribute(@PathVariable UUID ownerId,
                                                            @RequestBody OwnerRequest request){
        OwnerResponse ownerUpdated = ownerService.editOwnerAttribute(ownerId, request);
        return ResponseEntity.ok(ownerUpdated);

    }

    @DeleteMapping("/{ownerId}")
    public ResponseEntity<Void> deleteOwnerById(@PathVariable UUID ownerId){
        ownerService.deleteOwner(ownerId);
        return ResponseEntity.noContent().build();
    }
    //Getters

    @GetMapping
    public ResponseEntity<List<OwnerResponse>> getAllOwners(){
        return ResponseEntity.ok(ownerService.listAllOwners());
    }

    @GetMapping("/{ownerId}")
    public ResponseEntity<OwnerResponse> getOwnerById(@PathVariable UUID ownerId){
        return ResponseEntity.ok(ownerService.getOwnerById(ownerId));
    }

    //@GetMapping("/{ownerCpf}")
    public ResponseEntity<OwnerResponse> getOwnerByCpf(@PathVariable String ownerCpf){
        return ResponseEntity.ok(ownerService.getOwnerByCpf(ownerCpf));
    }




}
