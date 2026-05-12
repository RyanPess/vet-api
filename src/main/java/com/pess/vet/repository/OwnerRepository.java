package com.pess.vet.repository;

import com.pess.vet.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OwnerRepository extends JpaRepository<Owner, UUID> {
        public Optional<Owner> findByCpf(String cpf);

}
