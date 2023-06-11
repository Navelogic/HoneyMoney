package br.com.honeymoney.api.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_clients")
public class Client {
    // Long
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Address
    private Address address;

    // String
    @NotNull(message = "Nome não pode ser nulo")
    @NotEmpty(message = "Nome é obrigatório")
    private String name;
    @NotNull(message = "Email não pode ser nulo")
    @NotEmpty(message = "Email é obrigatório")
    private String email;
    private String phone;
    @NotNull(message = "CPF/CNPJ não pode ser nulo")
    @NotEmpty(message = "CPF/CNPJ é obrigatório")
    private String register;
    private String description;

    // Constructors
    public Client() {
    }
    public Client(Long id, Address address, String name, String email, String phone, String register, String description) {
        this.id = id;
        this.address = address;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.register = register;
        this.description = description;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRegister() {
        return register;
    }

    public void setRegister(String cpf_cnpj) {
        this.register = cpf_cnpj;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}