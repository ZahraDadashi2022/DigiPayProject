package com.example.dgpayproject.mappers;
import com.example.dgpayproject.models.dtos.WalletDtoForSave;
import com.example.dgpayproject.models.dtos.WalletDtoForUpdate;
import com.example.dgpayproject.models.entities.WalletEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WalletConverter  {

    WalletEntity convertToEntity(WalletDtoForSave walletDto);
    WalletEntity convertDtoForUpdateToEntity(WalletDtoForUpdate walletDtoWithoutBalance);
    List<WalletDtoForSave> convertEntityToDtoList(List<WalletEntity> wallets);
}
