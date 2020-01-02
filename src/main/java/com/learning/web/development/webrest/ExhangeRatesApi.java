package com.learning.web.development.webrest;

import com.learning.web.development.services.rates.ExchangeRateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExhangeRatesApi {

    private static final Logger logger = LoggerFactory.getLogger(ExhangeRatesApi.class);
    private ExchangeRateService exchangeRateService;

    public ExhangeRatesApi(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    @RequestMapping("/rates/get-course")
    public void getCBRates() {
        logger.debug("Start the Exchange rates functions");
        exchangeRateService.getCourses();
        logger.debug("End of the Exchange rates functions");
    }
}
