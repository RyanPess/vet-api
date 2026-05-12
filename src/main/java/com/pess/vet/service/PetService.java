package com.pess.vet.service;

import com.pess.vet.dto.PetRequest;
import com.pess.vet.dto.PetResponse;
import com.pess.vet.model.Pet;
import com.pess.vet.repository.OwnerRepository;
import com.pess.vet.repository.PetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PetService {
    PetRepository petRepository;
    OwnerRepository ownerRepository;
    public PetService(PetRepository petRepository, OwnerRepository ownerRepository) {
        this.petRepository = petRepository;
        this.ownerRepository = ownerRepository;
    }

    //Create
    public void addPet(UUID ownerId, PetRequest request){
        Pet pet = new Pet();
        pet.setName(request.name());
        pet.setSpecie(request.specie());
        pet.setBreed(request.breed());
        pet.setAge(request.age());
        pet.setOwner(ownerRepository.findById(ownerId).orElseThrow(() -> new RuntimeException("Owner not found")));

        petRepository.save(pet);
    }

    //Getters
    public List<PetResponse> listPetsByOwnerId(UUID ownerId){
        List<Pet> pets = petRepository.findByOwnerId(ownerId);
        return pets.stream()
                .map(pet -> new PetResponse(
                        pet.getId(),
                        pet.getName(),
                        pet.getSpecie(),
                        pet.getBreed(),
                        pet.getAge()
                ))
                .toList();
    }

    public PetResponse getPetById(UUID petId){
        Pet pet = petRepository.findById(petId).orElseThrow(() -> new RuntimeException("Pet not found"));
        return new PetResponse(
                pet.getId(),
                pet.getName(),
                pet.getSpecie(),
                pet.getBreed(),
                pet.getAge()
        );
    }

    public List<PetResponse> listAllPets(){
        List<Pet> pets = petRepository.findAll();
        return pets.stream()
                .map(pet -> new PetResponse(
                        pet.getId(),
                        pet.getName(),
                        pet.getSpecie(),
                        pet.getBreed(),
                        pet.getAge()
                ))
                .toList();
    }
    //Delete
    public void deletePet(UUID petId){
        Pet pet = petRepository.findById(petId).orElseThrow(() -> new RuntimeException("Pet not found"));
        petRepository.delete(pet);
    }
}


