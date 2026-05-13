package com.pess.vet.dto;


public record OwnerUpdateRequest(
        String name,
        String email,
        String numberPhone,
        String address) {
}
