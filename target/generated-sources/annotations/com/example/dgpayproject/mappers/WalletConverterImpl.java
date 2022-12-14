package com.example.dgpayproject.mappers;

import com.example.dgpayproject.models.dtos.WalletDtoForSave;
import com.example.dgpayproject.models.dtos.WalletDtoForUpdate;
import com.example.dgpayproject.models.entities.WalletEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-13T16:28:55+0330",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 18.0.2.1 (Oracle Corporation)"
)
@Component
public class WalletConverterImpl implements WalletConverter {

    @Override
    public WalletEntity convertToEntity(WalletDtoForSave walletDto) {
        if ( walletDto == null ) {
            return null;
        }

        WalletEntity walletEntity = new WalletEntity();

        walletEntity.setVersion( walletDto.getVersion() );
        walletEntity.setId( walletDto.getId() );
        walletEntity.setName( walletDto.getName() );

        return walletEntity;
    }

    @Override
    public WalletEntity convertDtoForUpdateToEntity(WalletDtoForUpdate walletDtoWithoutBalance) {
        if ( walletDtoWithoutBalance == null ) {
            return null;
        }

        WalletEntity walletEntity = new WalletEntity();

        walletEntity.setVersion( walletDtoWithoutBalance.getVersion() );
        walletEntity.setId( walletDtoWithoutBalance.getId() );
        walletEntity.setName( walletDtoWithoutBalance.getName() );
        walletEntity.setWalletStatus( walletDtoWithoutBalance.getWalletStatus() );

        return walletEntity;
    }

    @Override
    public List<WalletDtoForSave> convertEntityToDtoList(List<WalletEntity> wallets) {
        if ( wallets == null ) {
            return null;
        }

        List<WalletDtoForSave> list = new ArrayList<WalletDtoForSave>( wallets.size() );
        for ( WalletEntity walletEntity : wallets ) {
            list.add( walletEntityToWalletDtoForSave( walletEntity ) );
        }

        return list;
    }

    protected WalletDtoForSave walletEntityToWalletDtoForSave(WalletEntity walletEntity) {
        if ( walletEntity == null ) {
            return null;
        }

        WalletDtoForSave walletDtoForSave = new WalletDtoForSave();

        walletDtoForSave.setVersion( walletEntity.getVersion() );
        walletDtoForSave.setId( walletEntity.getId() );
        walletDtoForSave.setName( walletEntity.getName() );

        return walletDtoForSave;
    }
}
