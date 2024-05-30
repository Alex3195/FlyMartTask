package org.alexkings.productdeliveryapi.repository;

import org.alexkings.productdeliveryapi.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByRequestCodeAndOfferCode(String requestCode, String offerCode);

    Transaction findByTransactionCode(String transactionCode);

    List<Transaction> findByRequestCode(String requestCode);

}
