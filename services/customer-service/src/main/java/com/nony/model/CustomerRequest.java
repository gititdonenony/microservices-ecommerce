package com.nony.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public record CustomerRequest(
        Long id,
        @NotNull(message = "First Name must not be null")
        String firstName,
        @NotNull(message = "Last Name must not be null")
        String lastName,
        @NotNull(message = "Email must not be null")
        String email,
        Address address) {
}
