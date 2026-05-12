package com.pess.vet.dto;

import com.pess.vet.model.Owner;
import com.pess.vet.model.Pet;
import com.pess.vet.model.Specie;

import java.util.UUID;

public record PetResponse(UUID id, String name, Specie specie, String breed, int age) {
}
