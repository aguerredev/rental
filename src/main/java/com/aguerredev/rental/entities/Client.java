package com.aguerredev.rental.entities;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

public class Client {
    private static AtomicInteger CLIENT_ID_GENERATOR = new AtomicInteger(1000);

    private final Integer clientId;
    private String name;
    private LocalDate birthday;

    public Client(String name, LocalDate birthDay) {
        this.clientId = CLIENT_ID_GENERATOR.getAndIncrement();
        this.name = name;
        this.birthday = birthDay;
    }

    public Integer getClientId() {
        return clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
}
