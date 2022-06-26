package com.aguerredev.rental.requests;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class ClientRequestObject {
    private String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate birthday;

    public ClientRequestObject(String name, LocalDate birthDay) {
        this.name = name;
        this.birthday = birthDay;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }
}
