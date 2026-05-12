package com.pess.vet.dto;


import com.pess.vet.model.Specie;

public record PetRequest(String name, Specie specie, String breed, int age) {
}

