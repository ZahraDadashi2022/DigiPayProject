package com.example.dgpayproject.base;

import java.io.Serializable;

public interface BaseService<T , ID extends Serializable> {
    void save(T t);
}
