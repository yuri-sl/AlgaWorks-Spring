package com.algaworks.algadelivery.couriermanagement.domain.model;


import lombok.*;


@AllArgsConstructor
@Builder
@EqualsAndHashCode()
@Getter
//ValueObject não pode ter seus valores alterados, por isso n pode ter @Data nele
public class ContactPoint {
    private String zipCode;
    private String street;
    private String number;
    private String complement;
    private String phone;
    private  String name;
}
