package com.learning.web.development.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Service
public class ExchangeRatesQuery {
    private static final Logger log = LoggerFactory.getLogger(ExchangeRatesQuery.class);
    private LocalDate targetCourseDate;
    private String ratesXML;
    private final RestTemplate restTemplate;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String cbApiUrl = "http://www.cbr.ru/scripts/XML_daily.asp?date_req={date}";

    public ExchangeRatesQuery(@Qualifier("restTemplateExchangeRates") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getCourses(){
        log.debug("Make a GET query to CRB...");
        targetCourseDate = LocalDate.ofInstant(Instant.now()
                .truncatedTo(ChronoUnit.DAYS), ZoneOffset.UTC)
                .plusDays(1);

        ratesXML = this.restTemplate.getForObject(
                cbApiUrl,
                String.class,
                targetCourseDate.format(formatter)
        );
        log.debug("Get the ratesXML. Start the parse!");
        if (true) {
            throw new IllegalStateException("Haven't target result yet");
        }
        return ratesXML;
    }
}
