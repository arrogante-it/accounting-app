package com.arroganteIT.app.persistence.converter;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserNameFields implements Serializable {

    private String name;

    private String lastName;
}
