package com.arroganteIT.app.persistence.embeddableClasses;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Embeddable
@Data
public class UserName {

    @Size(min = 1, max = 75)
    @NotNull
    private String firstName;

    @Size(min = 1, max = 75)
    @NotNull
    private String lastName;
}
