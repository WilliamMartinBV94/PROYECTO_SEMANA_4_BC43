package com.nttdata.com.report_microservice.service;

import com.nttdata.com.report_microservice.model.Reports;
import com.nttdata.com.report_microservice.repository.ReportsRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ReportsServiceImpl implements ReportsService {
    private final ReportsRepository reportsRepository;

    public ReportsServiceImpl(ReportsRepository reportsRepository) {
        this.reportsRepository = reportsRepository;
    }

    @Override
    public Mono<Reports> generateDailyReport() {
        // Lógica para generar el reporte diario
        Reports dailyReport = new Reports();
        // ...
        return reportsRepository.save(dailyReport);
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
