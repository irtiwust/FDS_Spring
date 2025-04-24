package ru.tbank.fdsspring.dto;

import jakarta.validation.constraints.*;

public record CurrencyRequest(
        @NotBlank @Size(max = 50) String name,
        @NotBlank @Size(min = 3, max = 3) String baseCurrency,
        @NotBlank @Pattern(regexp = "^[+-]\\d+%/[+-]\\d+%$") String priceChangeRange,
        @Size(max = 255) String description
) {}