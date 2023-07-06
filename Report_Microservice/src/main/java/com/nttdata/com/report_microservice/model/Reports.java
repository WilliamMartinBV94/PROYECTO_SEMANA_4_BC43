package com.nttdata.com.report_microservice.model;

import lombok.Data;

import java.time.LocalDate;
@Data
public class Reports {
    private String id;
    private String type;
    private LocalDate date;
    private String data;
}
