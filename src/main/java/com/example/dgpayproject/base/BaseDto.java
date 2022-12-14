package com.example.dgpayproject.base;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public abstract class BaseDto{
    @JsonProperty("version")
    private Integer version;

}
