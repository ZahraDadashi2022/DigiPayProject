package com.example.dgpayproject.models.dtos;


import com.example.dgpayproject.base.BaseDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import javax.validation.constraints.*;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class UserDto extends BaseDto {
    private Long id;
    @JsonProperty("firstName")
    private String fname;
    @JsonProperty("lastName")
    private String lname;
    @NotBlank(message = "Username is mandatory")
    @NotEmpty
    @JsonProperty("username")
    private String username;
    @NotEmpty
    @JsonProperty("password")
    private String password;

}
