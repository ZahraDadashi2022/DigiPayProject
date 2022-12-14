package com.example.dgpayproject.base;

import java.util.List;

public interface BaseMapper<T , D> {

    T convertDtoToT(D d);

    D convertTToDto(T t);

    List<T> convertListDtoToListEntity(List<D> dList);

    List<D> convertListEntityToListDto(List<T> tList);

}
