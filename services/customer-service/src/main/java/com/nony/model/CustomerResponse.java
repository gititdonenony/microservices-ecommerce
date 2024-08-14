package com.nony.model;

public record CustomerResponse(
        Long id,
        String name,
        String email,
        String customerEmail, Address address
) {
}
