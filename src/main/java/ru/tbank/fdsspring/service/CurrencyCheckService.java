package ru.tbank.fdsspring.service;

import ru.tbank.fdsspring.dto.cbr.CbrCurrency;
import ru.tbank.fdsspring.dto.cbr.CbrCurrencyResponse;
import ru.tbank.fdsspring.entity.Currency;
import ru.tbank.fdsspring.repository.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyCheckService {

    private static final String CBR_API_URL = "https://www.cbr-xml-daily.ru/daily_json.js";
    private final CurrencyRepository currencyRepository;
    private final RestTemplate restTemplate;

    @Scheduled(cron = "0 0 * * * *") // Каждый час
    public void checkCurrencyChanges() {
        CbrCurrencyResponse response = restTemplate.getForObject(CBR_API_URL, CbrCurrencyResponse.class);
        List<Currency> trackedCurrencies = currencyRepository.findAllActive();

        trackedCurrencies.forEach(tracked -> {
            CbrCurrency cbrCurrency = response.getValutes().get(tracked.getName());
            if (cbrCurrency != null) {
                checkChange(tracked, cbrCurrency);
            }
        });
    }

    private void checkChange(Currency tracked, CbrCurrency cbrCurrency) {

        double changePercent = ((cbrCurrency.getPrevious() - cbrCurrency.getValue()) / cbrCurrency.getPrevious()) * 100;
        String[] ranges = tracked.getPriceChangeRange().split("/");

        double upThreshold = parseThreshold(ranges[0]);    // "+10%"
        double downThreshold = parseThreshold(ranges[1]);  // "-5%"

        if (changePercent >= upThreshold || changePercent <= downThreshold) {
            String message = String.format(
                    "%s изменился на %.2f%% (порог: %s)",
                    tracked.getName(),
                    changePercent,
                    tracked.getPriceChangeRange()
            );
            System.out.println(message);
        }
    }

    private double parseThreshold(String threshold) {
        return Double.parseDouble(threshold.replace("%", ""));
    }
}