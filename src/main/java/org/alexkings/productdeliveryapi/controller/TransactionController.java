package org.alexkings.productdeliveryapi.controller;

import org.alexkings.productdeliveryapi.entities.Transaction;
import org.alexkings.productdeliveryapi.model.TransactionDto;
import org.alexkings.productdeliveryapi.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/addTransaction")
    @PreAuthorize("hasPermission('TRANSACTION_CREATE') and hasAnyRole('ADMION','CARRIER')")
    public ResponseEntity<TransactionDto> createTransaction(@RequestBody TransactionDto transaction) {
        TransactionDto savedTransaction = transactionService.createTransaction(transaction);
        return ResponseEntity.ok(savedTransaction);
    }

    @GetMapping("/getTransactions")
    @PreAuthorize("hasAnyRole('ADMIN','CARRIE') and hasPermission('TRANSACTION_READ')")
    public ResponseEntity<List<TransactionDto>> getAllTransactions() {
        List<TransactionDto> transactions = transactionService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','CARRIE') and hasPermission('TRANSACTION_READ')")
    public ResponseEntity<TransactionDto> getTransactionById(@PathVariable Long id) {
        Optional<TransactionDto> transaction = transactionService.getTransactionById(id);
        return transaction.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','CARRIE') and hasPermission('TRANSACTION_UPDATE')")
    public ResponseEntity<TransactionDto> updateTransaction(@PathVariable Long id, @RequestBody TransactionDto transaction) {
        TransactionDto updatedTransaction = transactionService.updateTransaction(id, transaction);
        return ResponseEntity.ok(updatedTransaction);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','CARRIE') and hasPermission('TRANSACTION_DELETE')")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }
}
