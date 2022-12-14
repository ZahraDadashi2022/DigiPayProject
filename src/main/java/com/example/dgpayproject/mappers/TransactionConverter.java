package com.example.dgpayproject.mappers;

import com.example.dgpayproject.models.dtos.TransactionDto;
import com.example.dgpayproject.models.entities.TransactionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionConverter  {

    @Mapping(target = "walletName", source = "walletEntity.name")
    TransactionDto convertEntityToDto(TransactionEntity transactionEntity);
    @Mapping(target = "walletEntity.name", source = "walletName")
    TransactionEntity convertDtoToEntity(TransactionDto transactionDto);
    List<TransactionDto> convertListEntityToListDto(List<TransactionEntity> transactions);

}