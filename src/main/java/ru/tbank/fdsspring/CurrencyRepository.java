package ru.tbank.fdsspring;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tbank.fdsspring.Currency;

public interface CurrencyRepository extends JpaRepository<Currency, String> {

}