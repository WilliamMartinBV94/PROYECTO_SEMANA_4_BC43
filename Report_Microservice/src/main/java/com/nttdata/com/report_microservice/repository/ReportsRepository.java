package com.nttdata.com.report_microservice.repository;

import com.nttdata.com.report_microservice.model.Reports;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ReportsRepository extends ReactiveMongoRepository<Reports, String> {

}
