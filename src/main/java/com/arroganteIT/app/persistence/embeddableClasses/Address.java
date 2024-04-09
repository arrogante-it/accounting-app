package com.arroganteIT.app.persistence.embeddableClasses;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

@Embeddable
@Data
public class Address {

    @Size(min = 1, max = 50)
    private String country;

    @Size(min = 1, max = 50)
    private String city;

    @Size(min = 1, max = 50)
    private String street;
}
