package ru.tbank.fdsspring;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Currency {

    @Id
    private String id;
    private String name;
    private String baseCurrency;
    private String priceChangeRange;
    private String description;

}