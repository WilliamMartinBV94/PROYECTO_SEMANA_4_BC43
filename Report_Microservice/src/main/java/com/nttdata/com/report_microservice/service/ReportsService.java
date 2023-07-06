package com.nttdata.com.report_microservice.service;

import com.nttdata.com.report_microservice.model.Reports;
import reactor.core.publisher.Mono;

public interface ReportsService {
    //Mono<Reports> generateAccountSummaryReport(String customerId);
    //Mono<Reports> generateCommissionReport();
    //Mono<Reports> generateCardTransactionsReport(String cardType, int limit);
    Mono<Reports> generateDailyReport();
    Mono<Reports> generateCommissionReport();
}
