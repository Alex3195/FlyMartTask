package org.alexkings.productdeliveryapi.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import org.alexkings.productdeliveryapi.auth.JwtUtil;
import org.alexkings.productdeliveryapi.entities.*;
import org.alexkings.productdeliveryapi.model.TransactionDto;
import org.alexkings.productdeliveryapi.model.UserDto;
import org.alexkings.productdeliveryapi.repository.*;
import org.alexkings.productdeliveryapi.service.TransactionService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final RequestRepository requestRepository;
    private final OfferRepository offerRepository;
    private final PlaceRepository placeRepository;
    private final BindCarriersToPlaceRepository bindCarriersToPlaceRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public TransactionServiceImpl(TransactionRepository transactionRepository, RequestRepository requestRepository, OfferRepository offerRepository, PlaceRepository placeRepository, BindCarriersToPlaceRepository bindCarriersToPlaceRepository, UserRepository userRepository, JwtUtil jwtUtil) {
        this.transactionRepository = transactionRepository;
        this.requestRepository = requestRepository;
        this.offerRepository = offerRepository;
        this.placeRepository = placeRepository;
        this.bindCarriersToPlaceRepository = bindCarriersToPlaceRepository;
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    public TransactionDto createTransaction(TransactionDto transaction) {

        if (isAvailable(transaction)) {
            Transaction e = dtoToEntity(transaction);
            return entityToDto(transactionRepository.save(e));
        } else return null;

    }

    private boolean isAvailable(TransactionDto transaction) {
        List<Transaction> tr = transactionRepository.findByRequestCodeAndOfferCode(transaction.getRequestCode(), transaction.getOfferCode());
        Requests requests = requestRepository.findByRequestCode(transaction.getRequestCode());
        Offers offers = offerRepository.findByOfferCode(transaction.getOfferCode());
        Optional<Users> carrier = userRepository.findByUsername(transaction.getCarrierUsername());
        Place deliveryLocation = placeRepository.findByName(transaction.getDeliveryLocation());
        Place pickupLocation = placeRepository.findByName(transaction.getPickupLocation());
        boolean isPlaceRelatedToCarrier = false;
        boolean isProductCodeSame = requests.getProductCode().equalsIgnoreCase(offers.getProductCode());
        if (carrier.isPresent()) {
            List<BindCarriersToPlaces> list = bindCarriersToPlaceRepository.findByCarrierId(carrier.get().getId());
            isPlaceRelatedToCarrier = list.stream().map(BindCarriersToPlaces::getPlaceId).toList().containsAll(Arrays.asList(pickupLocation.getId(), deliveryLocation.getId()));
        }
        return tr.isEmpty() && isPlaceRelatedToCarrier && isProductCodeSame;


    }

    public List<TransactionDto> getAllTransactions() {
        return transactionRepository.findAll().stream().map(this::entityToDto).toList();
    }

    public Optional<TransactionDto> getTransactionById(Long id) {
        return transactionRepository.findById(id).map(this::entityToDto);
    }

    public TransactionDto updateTransaction(Long id, TransactionDto updatedTransaction) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        if (transaction.isPresent()) {
            Transaction t = transaction.get();
            t.setTransactionCode(updatedTransaction.getTransactionCode());
            t.setCarrierUsername(updatedTransaction.getCarrierUsername());
            t.setRequestCode(updatedTransaction.getRequestCode());
            t.setOfferCode(updatedTransaction.getOfferCode());
            t.setScore(updatedTransaction.getScore());
            t.setUpdateDate(Date.from(Instant.now()));
            return entityToDto(transactionRepository.save(t));
        }
        return null;
    }

    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }

    @Override
    public void evaluateTransaction(String transactionCode, Integer score) {
        Transaction e = transactionRepository.findByTransactionCode(transactionCode);
        e.setScore(score);
        e.setUpdateDate(Date.from(Instant.now()));
        transactionRepository.save(e);
    }

    @Override
    public List<TransactionDto> getUserTransactions(HttpServletRequest request) {
        UserDto user = jwtUtil.parseToken(request);
        Requests requests = requestRepository.findByCreatedBy(user.getId());
        List<Transaction> list = transactionRepository.findByRequestCode(requests.getRequestCode());
        return list.stream().map(this::entityToDto).toList();
    }

    private TransactionDto entityToDto(Transaction transaction) {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setTransactionCode(transaction.getTransactionCode());
        transactionDto.setCarrierUsername(transaction.getCarrierUsername());
        transactionDto.setRequestCode(transaction.getRequestCode());
        transactionDto.setOfferCode(transaction.getOfferCode());
        transactionDto.setScore(transaction.getScore());
        transactionDto.setProductCode(transaction.getProductCode());
        transactionDto.setDeliveryLocation(transaction.getDeliveryLocation());
        transactionDto.setPickupLocation(transaction.getPickupLocation());
        return transactionDto;
    }

    private Transaction dtoToEntity(TransactionDto transactionDto) {
        Transaction transaction = new Transaction();
        transaction.setTransactionCode(transactionDto.getTransactionCode());
        transaction.setCarrierUsername(transactionDto.getCarrierUsername());
        transaction.setRequestCode(transactionDto.getRequestCode());
        transaction.setOfferCode(transactionDto.getOfferCode());
        transaction.setScore(transactionDto.getScore());
        transaction.setProductCode(transactionDto.getProductCode());
        transaction.setDeliveryLocation(transactionDto.getDeliveryLocation());
        transaction.setPickupLocation(transactionDto.getPickupLocation());
        if (transaction.getId() == null)
            transaction.setCreatedDate(Date.from(Instant.now()));
        transaction.setUpdateDate(Date.from(Instant.now()));
        return transaction;
    }
}
