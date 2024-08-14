package com.nony.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/*This class represents a custom exception for when a customer is not found.
The @Data annotation generates getters, setters, equals, hashCode, and toString methods
for the class's fields.
The 'message' field is declared as final, meaning its value cannot be changed
once it's initialized. This is a good practice for immutable objects.*/

@EqualsAndHashCode(callSuper = true)
@Data
public class CustomerNotFoundException extends RuntimeException {
    private final String message;
}
