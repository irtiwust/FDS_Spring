package ru.tbank.fdsspring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tbank.fdsspring.Currency;
import ru.tbank.fdsspring.CurrencyRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyService {

    private final CurrencyRepository currencyRepository;

    public List<Currency> getAllCurrencies() {
        return currencyRepository.findAll();
    }

    public Currency addCurrency(Currency currency) {
        return currencyRepository.save(currency);
    }

    public Currency getCurrencyById(String id) {
        return currencyRepository.findById(id).orElseThrow(() -> new RuntimeException("Валюта не найдена"));
    }

    public Currency updateCurrency(String id, Currency currency) {
        currency.setId(id);
        return currencyRepository.save(currency);
    }

    public void deleteCurrency(String id) {
        currencyRepository.deleteById(id);
    }
}