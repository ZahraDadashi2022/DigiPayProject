package com.example.dgpayproject.mappers;

import com.example.dgpayproject.models.dtos.UserDto;
import com.example.dgpayproject.models.entities.UserEntity;
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
public class UserConverterImpl implements UserConverter {

    @Override
    public UserEntity convertDtoToT(UserDto d) {
        if ( d == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setVersion( d.getVersion() );
        userEntity.setId( d.getId() );
        userEntity.setFname( d.getFname() );
        userEntity.setLname( d.getLname() );
        userEntity.setUsername( d.getUsername() );
        userEntity.setPassword( d.getPassword() );

        return userEntity;
    }

    @Override
    public UserDto convertTToDto(UserEntity t) {
        if ( t == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setVersion( t.getVersion() );
        userDto.setId( t.getId() );
        userDto.setFname( t.getFname() );
        userDto.setLname( t.getLname() );
        userDto.setUsername( t.getUsername() );
        userDto.setPassword( t.getPassword() );

        return userDto;
    }

    @Override
    public List<UserEntity> convertListDtoToListEntity(List<UserDto> dList) {
        if ( dList == null ) {
            return null;
        }

        List<UserEntity> list = new ArrayList<UserEntity>( dList.size() );
        for ( UserDto userDto : dList ) {
            list.add( convertDtoToT( userDto ) );
        }

        return list;
    }

    @Override
    public List<UserDto> convertListEntityToListDto(List<UserEntity> tList) {
        if ( tList == null ) {
            return null;
        }

        List<UserDto> list = new ArrayList<UserDto>( tList.size() );
        for ( UserEntity userEntity : tList ) {
            list.add( convertTToDto( userEntity ) );
        }

        return list;
    }
}
