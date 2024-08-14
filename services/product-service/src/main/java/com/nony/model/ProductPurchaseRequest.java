package com.nony.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductPurchaseRequest(
        Long id,
        @NotNull(message = "Product name must not be null")
        String name,
        @NotNull(message = "Product description must not be null")
        String description,
        @Positive(message = "Product quantity cannot be negative")
        double quantity,
        @Positive(message = "Product price cannot be negative")
        BigDecimal price,
        @NotNull(message = "Product category required")
        Long categoryId
) {
}
