package com.example.dgpayproject.mappers;

import com.example.dgpayproject.models.dtos.TransactionDto;
import com.example.dgpayproject.models.entities.TransactionEntity;
import com.example.dgpayproject.models.entities.WalletEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-13T16:28:54+0330",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 18.0.2.1 (Oracle Corporation)"
)
@Component
public class TransactionConverterImpl implements TransactionConverter {

    @Override
    public TransactionDto convertEntityToDto(TransactionEntity transactionEntity) {
        if ( transactionEntity == null ) {
            return null;
        }

        TransactionDto transactionDto = new TransactionDto();

        transactionDto.setWalletName( transactionEntityWalletEntityName( transactionEntity ) );
        transactionDto.setVersion( transactionEntity.getVersion() );
        transactionDto.setId( transactionEntity.getId() );
        transactionDto.setAccountNumber( transactionEntity.getAccountNumber() );
        transactionDto.setAmount( transactionEntity.getAmount() );
        transactionDto.setTransactionStatus( transactionEntity.getTransactionStatus() );
        transactionDto.setDescription( transactionEntity.getDescription() );

        return transactionDto;
    }

    @Override
    public TransactionEntity convertDtoToEntity(TransactionDto transactionDto) {
        if ( transactionDto == null ) {
            return null;
        }

        TransactionEntity transactionEntity = new TransactionEntity();

        transactionEntity.setWalletEntity( transactionDtoToWalletEntity( transactionDto ) );
        transactionEntity.setVersion( transactionDto.getVersion() );
        transactionEntity.setId( transactionDto.getId() );
        transactionEntity.setAccountNumber( transactionDto.getAccountNumber() );
        transactionEntity.setAmount( transactionDto.getAmount() );
        transactionEntity.setTransactionStatus( transactionDto.getTransactionStatus() );
        transactionEntity.setDescription( transactionDto.getDescription() );

        return transactionEntity;
    }

    @Override
    public List<TransactionDto> convertListEntityToListDto(List<TransactionEntity> transactions) {
        if ( transactions == null ) {
            return null;
        }

        List<TransactionDto> list = new ArrayList<TransactionDto>( transactions.size() );
        for ( TransactionEntity transactionEntity : transactions ) {
            list.add( convertEntityToDto( transactionEntity ) );
        }

        return list;
    }

    private String transactionEntityWalletEntityName(TransactionEntity transactionEntity) {
        if ( transactionEntity == null ) {
            return null;
        }
        WalletEntity walletEntity = transactionEntity.getWalletEntity();
        if ( walletEntity == null ) {
            return null;
        }
        String name = walletEntity.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    protected WalletEntity transactionDtoToWalletEntity(TransactionDto transactionDto) {
        if ( transactionDto == null ) {
            return null;
        }

        WalletEntity walletEntity = new WalletEntity();

        walletEntity.setName( transactionDto.getWalletName() );

        return walletEntity;
    }
}
