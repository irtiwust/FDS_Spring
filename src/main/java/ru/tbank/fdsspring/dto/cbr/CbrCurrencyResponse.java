package ru.tbank.fdsspring.dto.cbr;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.Map;

@Data
public class CbrCurrencyResponse {

    @JsonProperty("Valute")
    private Map<String, CbrCurrency> valutes;
}