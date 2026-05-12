package com.pess.vet.repository;

import com.pess.vet.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PetRepository extends JpaRepository<Pet, UUID> {

    public List<Pet> findByOwnerId(UUID ownerId);
}
