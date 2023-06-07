package br.com.honeymoney.api.model;

public class Erro {
    private String userMessage;
    private String devMessage;

    public Erro(String userMessage, String devMessage) {
        this.userMessage = userMessage;
        this.devMessage = devMessage;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public String getDevMessage() {
        return devMessage;
    }

    public void setDevMessage(String devMessage) {
        this.devMessage = devMessage;
    }
}
