package com.pess.vet.service;


import com.pess.vet.dto.OwnerRequest;
import com.pess.vet.dto.OwnerResponse;
import com.pess.vet.dto.OwnerUpdateRequest;
import com.pess.vet.model.Owner;
import com.pess.vet.repository.OwnerRepository;
import org.springframework.stereotype.Service;

import com.pess.vet.exception.*;

import java.util.List;
import java.util.UUID;

@Service
public class OwnerService {
    OwnerRepository ownerRepository;

    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    //Getters

    public List<OwnerResponse> listAllOwners(){
        List<Owner> owners = ownerRepository.findAll();
        return owners.stream().map(owner -> new OwnerResponse(
                owner.getId(),
                owner.getName(),
                owner.getCpf(),
                owner.getEmail(),
                owner.getNumberPhone(),
                owner.getAddress()
        )).toList();
    }


    public OwnerResponse getOwnerById(UUID ownerId){
        Owner ownerFound = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new OwnerNotExitsException("this owner does not exist"));

        return new OwnerResponse(
                ownerFound.getId(),
                ownerFound.getName(),
                ownerFound.getCpf(),
                ownerFound.getEmail(),
                ownerFound.getNumberPhone(),
                ownerFound.getAddress()
        );
    }


    public OwnerResponse getOwnerByCpf(String cpf){
        Owner ownerFound = ownerRepository.findByCpf(cpf)
                .orElseThrow(() -> new OwnerNotExitsException("this owner does not exist"));

        return new OwnerResponse(
                ownerFound.getId(),
                ownerFound.getName(),
                ownerFound.getCpf(),
                ownerFound.getEmail(),
                ownerFound.getNumberPhone(),
                ownerFound.getAddress()
        );
    }

    //Setters

    public void addOwner(OwnerRequest request){

        if(ownerRepository.findByCpf(request.cpf()).isPresent()){
            throw new OwnerAlreadyRegisteredException("Owner Already Registered");
        }

        Owner owner = new Owner();
        owner.setName(request.name());
        owner.setCpf(request.cpf());
        owner.setEmail(request.email());
        owner.setNumberPhone(request.numberPhone());
        owner.setAddress(request.address());

        ownerRepository.save(owner);
    };

    //Edit

    public OwnerResponse editOwnerAttribute(UUID ownerId, OwnerUpdateRequest request){

        Owner ownerFound = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new OwnerNotExitsException("this owner does not exist"));

        //Antes de atualizar, verifico se algum atributo foi passado como vazio;
        validateIfAttributeUpdateNotEmpty(request.name(), "name");
        validateIfAttributeUpdateNotEmpty(request.email(), "email");
        validateIfAttributeUpdateNotEmpty(request.numberPhone(), "numberPhone");
        validateIfAttributeUpdateNotEmpty(request.address(), "address");

        //Aqui eu aprendi um conceito muito importante: Builder
        Owner ownerUpdated = Owner.builder() //Criei um builder que verifica se o argumento está vazio
                .id(ownerFound.getId())
                .name(request.name() != null ? request.name() : ownerFound.getName())
                .cpf(ownerFound.getCpf()) //--> O CPF é um atributo que não pode ser editado, então não tem sentido colocar ele aqui.
                .email(request.email() != null ? request.email() : ownerFound.getEmail())
                .numberPhone(request.numberPhone() != null ? request.numberPhone() : ownerFound.getNumberPhone())
                .address(request.address() != null ? request.address() : ownerFound.getAddress())
                .build();
        //Este tem a responsabilidade de não apagar atributos que não foram passados na requisição.
//        ownerUpdated.setName(request.name());
//        ownerUpdated.setCpf(request.cpf());
//        ownerUpdated.setEmail(request.email());
//        ownerUpdated.setNumberPhone(request.numberPhone());
//        ownerUpdated.setAddress(request.address());


        ownerRepository.saveAndFlush(ownerUpdated);
        return new OwnerResponse(
                ownerUpdated.getId(),
                ownerUpdated.getName(),
                ownerUpdated.getCpf(),
                ownerUpdated.getEmail(),
                ownerUpdated.getNumberPhone(),
                ownerUpdated.getAddress()
        );
    }

    // Delete

    public void deleteOwner(UUID ownerId){
        ownerRepository.deleteById(ownerId);
    }

    // Other methods
    private void validateIfAttributeUpdateNotEmpty(String value, String attributeName) {
        if (value != null && value.isEmpty()) {
            throw new InvalidOwnerAttributeException(attributeName + " cannot be empty");
        }
    }

}
