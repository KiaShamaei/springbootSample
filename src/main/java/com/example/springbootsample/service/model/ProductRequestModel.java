package com.example.springbootsample.service.model;


import com.example.springbootsample.validation.CustomeAnnotation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestModel {

    @NotBlank(message = "نام نمی تواند خالی باشد ")
    private String name ;
    @CustomeAnnotation(message = "this is must start with off")
    private String description;
    @NotNull(message = "price must be fill ...")
    private Long price;

}
