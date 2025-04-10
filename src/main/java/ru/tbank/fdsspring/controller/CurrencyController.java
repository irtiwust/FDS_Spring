package ru.tbank.fdsspring.controller;

import jakarta.validation.Valid;
import ru.tbank.fdsspring.dto.CurrencyRequest;
import ru.tbank.fdsspring.entity.Currency;
import ru.tbank.fdsspring.repository.CurrencyRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/currencies")
public class CurrencyController {

    private final CurrencyRepository repository;

    public CurrencyController(CurrencyRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Currency> getAll() {
        return repository.findAllActive();  // Только активные
    }

    @GetMapping("/{id}")
    public ResponseEntity<Currency> getById(@PathVariable Long id) {
        return repository.findActiveById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Currency create(@RequestBody Currency currency) {
        return repository.save(currency);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Currency> update(
            @PathVariable Long id,
            @RequestBody Currency currency
    ) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        currency.setId(id);
        return ResponseEntity.ok(repository.save(currency));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.softDeleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public Currency create(@Valid @RequestBody CurrencyRequest request) {
        Currency currency = new Currency();
        currency.setName(request.name());
        currency.setBaseCurrency(request.baseCurrency());
        currency.setPriceChangeRange(request.priceChangeRange());
        currency.setDescription(request.description());
        return repository.save(currency);
    }
}