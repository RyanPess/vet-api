package com.pess.vet.controller;


import com.pess.vet.dto.PetRequest;
import com.pess.vet.dto.PetResponse;
import com.pess.vet.model.Pet;
import com.pess.vet.service.PetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/owners")
public class PetController {

    PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping("/{ownerId}/pets")
    public ResponseEntity<PetRequest> addPet(@PathVariable UUID ownerId, @RequestBody PetRequest request){
        petService.addPet(ownerId, request);
        return ResponseEntity.ok().body(request);
    }

    @GetMapping("/pet/{petId}")
    public ResponseEntity<PetResponse> getPetById(@PathVariable UUID petId){
        return ResponseEntity.ok().body(petService.getPetById(petId));
    }

    @GetMapping("/{ownerId}/pets")
    public ResponseEntity<List<PetResponse>> getPetsByOwnerId(@PathVariable UUID ownerId){
        return ResponseEntity.ok().body(petService.listPetsByOwnerId(ownerId));
    }

    @GetMapping("/pets")
    public ResponseEntity<List<PetResponse>> getAllPets(){
        return ResponseEntity.ok().body(petService.listAllPets());
    }

    @DeleteMapping("/deletePet/{petId}")
    public ResponseEntity<Void> deletePetById(@PathVariable UUID petId){
        petService.deletePet(petId);
        return ResponseEntity.noContent().build();
    }
}
