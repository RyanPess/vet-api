package com.pess.vet.dto;

import java.util.UUID;

public record OwnerResponse(UUID id, String name, String cpf, String email, String numberPhone, String address) {
}
