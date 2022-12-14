package com.example.dgpayproject.controllers;

import com.example.dgpayproject.mappers.TransactionConverter;
import com.example.dgpayproject.models.dtos.TransactionDto;
import com.example.dgpayproject.models.dtos.WalletTransfer;
import com.example.dgpayproject.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {
    public static final Logger LOGGER = LoggerFactory.getLogger(TransactionController.class);

    private final TransactionService transactionService;
    private final TransactionConverter transactionConverter;

    @PostMapping
    public void saveTransaction(@Valid @RequestBody TransactionDto transactionDto) {
        LOGGER.info("transaction is saved:{}",transactionDto);
        transactionService.save(transactionConverter.convertDtoToEntity(transactionDto));
    }

    @PostMapping("/withdraw")
    public void withdraw(@Valid @RequestBody TransactionDto transactionDto) {
        LOGGER.info("withdraw from wallet:{}",transactionDto);
        transactionService.withdraw(transactionConverter.convertDtoToEntity(transactionDto));
    }
    @GetMapping("/{id}")
    public List<TransactionDto> transactionList(@PathVariable Long id){
        LOGGER.info("fetching transactions of one wallet");
        return transactionConverter.convertListEntityToListDto(transactionService.findByWalletEntity(id));
    }
    @GetMapping("/transferWalletToWallet")
    public void moneyTransfer (@RequestBody WalletTransfer walletTransfer){
        LOGGER.info("transfer money from source wallet to target wallet");
        transactionService.transferWalletToWallet(walletTransfer);
    }
}
