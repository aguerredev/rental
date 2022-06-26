package com.aguerredev.rental.records;

import java.time.LocalDate;

public record ClientRecord(Integer clientId, String name, LocalDate birthday) {
}
