package com.example.dgpayproject.controllers;

import com.example.dgpayproject.exceptionhandlers.GlobalException;
import com.example.dgpayproject.mappers.WalletConverter;
import com.example.dgpayproject.models.dtos.WalletDtoForSave;
import com.example.dgpayproject.models.dtos.WalletDtoForUpdate;
import com.example.dgpayproject.services.WalletService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/wallets")
@RequiredArgsConstructor
public class WalletController {
    public static final Logger LOGGER = LoggerFactory.getLogger(WalletController.class);

    private final WalletService walletService;
    private final WalletConverter walletConverter;

    @PostMapping
    public void saveWallet(@Valid @RequestBody WalletDtoForSave walletDto) {
        LOGGER.info("saving wallet is done successfully:{}", walletDto);
        walletService.save(walletConverter.convertToEntity(walletDto));
    }

    @PutMapping
    public void updateWallet(@Valid @RequestBody WalletDtoForUpdate walletDto) {
        if (walletDto.getId() == null) throw new GlobalException("id.does.not.exist.in.database");
        LOGGER.info("updating wallet is done successfully:{}", walletDto);
        walletService.save(walletConverter.convertDtoForUpdateToEntity(walletDto));
    }

    @GetMapping
    public List<WalletDtoForSave> getWallets() {
        LOGGER.info("fetching all wallets  of user logged in");
        return walletConverter.convertEntityToDtoList(walletService.findByUserEntity());
    }

}
