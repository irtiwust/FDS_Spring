package ru.tbank.fdsspring.dto.cbr;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CbrCurrency {
    @JsonProperty("CharCode")
    private String code;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Value")
    private double value;

    @JsonProperty("Previous")
    private double previous;
}