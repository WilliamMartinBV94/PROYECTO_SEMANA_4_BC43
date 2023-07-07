package com.nttdata.com.report_microservice.controller;

import com.nttdata.com.report_microservice.model.Reports;
import com.nttdata.com.report_microservice.service.ReportsService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/reports")
public class ReportsController {
    private final ReportsService reportsService;

    public ReportsController(ReportsService reportsService) {
        this.reportsService = reportsService;
    }

    @PostMapping("/daily")
    public Mono<Reports> generateDailyReport() {
        return reportsService.generateDailyReport();
    }

    @PostMapping("/commission")
    public Mono<Reports> generateCommissionReport() {
        return reportsService.generateCommissionReport();
    }
}
