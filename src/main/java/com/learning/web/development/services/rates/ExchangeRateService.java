package com.learning.web.development.services.rates;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@Service
public class ExchangeRateService {

    private static final Logger log = LoggerFactory.getLogger(ExchangeRateService.class);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private String ratesXML;
    private ExchangeRatesQuery exchangeRatesQuery;

    public ExchangeRateService(ExchangeRatesQuery exchangeRatesQuery) {
        this.exchangeRatesQuery = exchangeRatesQuery;
    }

    public void loadRates() {
        try {
            ratesXML = getCourses();
        } catch (Exception exp) {
            throw new IllegalStateException("Does not find any valid rates from CB API after "
                    + exp.getMessage() + " attempts");
        }
    }

    @Retryable(
            value = {IllegalStateException.class},
            maxAttempts = 5,
            backoff = @Backoff(delay = 5000))
    public String getCourses() {
        return exchangeRatesQuery.getCourses();
    }
}
