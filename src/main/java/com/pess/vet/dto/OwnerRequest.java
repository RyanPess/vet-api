package com.pess.vet.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.NumberFormat;

public record OwnerRequest(
        @NotBlank
        String name,

        @NotBlank
        @CPF
        String cpf,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String numberPhone,

        @NotBlank
        String address) {
}

/*
* {
*   "name": "John Doe",
*   "cpf": "12345678900",
*   "email": "johndoe@email.com",
*   "numberPhone": "1234567890",
*   "address": "123 Main St, Anytown, USA"
* }
* */
/*
* {
*   "name": "Lucas Silva",
*   "cpf": "987.654.321-00",
*   "email": lucassilva@gmail.com",
*   "numberPhone": "9876543210",
*   "address": "456 Elm St, Othertown, USA"
* }
* */