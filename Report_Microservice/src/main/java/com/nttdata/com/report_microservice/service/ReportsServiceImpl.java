package com.nttdata.com.report_microservice.service;

import com.nttdata.com.report_microservice.model.Reports;
import com.nttdata.com.report_microservice.repository.ReportsRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class ReportsServiceImpl implements ReportsService {
    private final ReportsRepository reportsRepository;

    public ReportsServiceImpl(ReportsRepository reportsRepository) {
        this.reportsRepository = reportsRepository;
    }

    @Override
    public Mono<Reports> generateDailyReport() {
        // Obtener la fecha actual
        LocalDate currentDate = LocalDate.now();

        // Obtener los movimientos de transacciones del día actual
        Flux<Transaction> dailyTransactions = transactionsService.getTransactionsByDate(currentDate);

        // Calcular el saldo promedio diario
        Mono<BigDecimal> averageDailyBalance = dailyTransactions
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .map(totalAmount -> totalAmount.divide(BigDecimal.valueOf(dailyTransactions.count().block()), RoundingMode.HALF_UP));

        // Generar el reporte con los datos obtenidos
        return averageDailyBalance.map(balance -> new Report(currentDate, balance))
                .flatMap(report -> reportsRepository.save(report));
    }

    @Override
    public Mono<Reports> generateCommissionReport() {
        // Obtener la fecha actual
        LocalDate currentDate = LocalDate.now();

        // Filtrar los movimientos de comisiones del mes actual
        Flux<Transaction> commissionTransactions = transactionsService.getAllTransactions()
                .filter(transaction -> transaction.getType() == TransactionType.COMMISSION)
                .filter(transaction -> transaction.getDate().getMonth() == currentDate.getMonth());

        // Calcular las comisiones totales por producto
        Mono<Map<String, BigDecimal>> commissionsByProduct = commissionTransactions
                .groupBy(Transaction::getProductId)
                .flatMap(groupedFlux -> groupedFlux
                        .reduce(BigDecimal.ZERO, (total, transaction) -> total.add(transaction.getAmount())))
                .collectMap(Transaction::getProductId, amount -> amount);

        // Obtener la información de los productos bancarios
        Flux<BankingProduct> bankingProducts = bankingProductsService.getAllBankingProducts();

        // Combinar la información de comisiones y productos bancarios para generar el reporte
        return commissionsByProduct.flatMap(commissions -> bankingProducts
                        .filter(product -> commissions.containsKey(product.getId()))
                        .map(product -> new CommissionReportEntry(product.getName(), commissions.get(product.getId()))))
                .collectList()
                .map(entries -> new Report(entries));
    }
}
