package org.alexkings.productdeliveryapi.service;

import org.alexkings.productdeliveryapi.model.TransactionDto;

import java.util.List;
import java.util.Optional;

public interface TransactionService {
    TransactionDto createTransaction(TransactionDto transaction);

    List<TransactionDto> getAllTransactions();

    Optional<TransactionDto> getTransactionById(Long id);

    TransactionDto updateTransaction(Long id, TransactionDto updatedTransaction);

    void deleteTransaction(Long id);
}
