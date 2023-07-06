package com.nttdata.com.bankingproduct_microservice.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class BankingProduct {
    private String id;
    private String name; // Nombre del producto bancario
    private String type; // Tipo de producto bancario (cuenta, crédito, tarjeta)
    private String subtype; // Subtipo específico (ahorro, cuenta corriente, plazo fijo, personal, empresarial)
    private String customerIds; // ID del cliente asociado al producto
    private BigDecimal balance; // Saldo actual del producto
    private BigDecimal creditLimit; // Límite de crédito (solo aplicable a tarjetas de crédito)
    private List<String> owners; // Lista de titulares del producto (solo aplicable a cuentas empresariales)
    private List<String> authorizedSigners; // Lista de firmantes autorizados (solo aplicable a cuentas empresariales)

}
