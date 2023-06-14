package br.com.honeymoney.api.dao.filter;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class ReleaseFilter {
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateExpirationFrom;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateExpirationTo;

    // Getters and Setters
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateExpirationFrom() {
        return dateExpirationFrom;
    }

    public void setDateExpirationFrom(LocalDate dateExpirationFrom) {
        this.dateExpirationFrom = dateExpirationFrom;
    }

    public LocalDate getDateExpirationTo() {
        return dateExpirationTo;
    }

    public void setDateExpirationTo(LocalDate dateExpirationTo) {
        this.dateExpirationTo = dateExpirationTo;
    }
}
