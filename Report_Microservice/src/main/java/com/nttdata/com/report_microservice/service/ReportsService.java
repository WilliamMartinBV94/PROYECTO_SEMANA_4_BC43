package com.nttdata.com.report_microservice.service;

import com.nttdata.com.report_microservice.model.Reports;
import reactor.core.publisher.Mono;

public interface ReportsService {
    Mono<Reports> generateDailyReport();
    Mono<Reports> generateCommissionReport();
}
