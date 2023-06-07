package br.com.honeymoney.api.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "persons")
public class Person {
    // Long
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Address
    @Embedded
    private Address address;

    // String
    @NotNull(message = "Primeiro nome não pode ser nulo")
    @NotEmpty(message = "Primeiro nome não pode ser vazio")
    private String firstName;
    private String lastName;
    @NotNull(message = "E-mail não pode ser nulo")
    @NotEmpty(message = "E-mail não pode ser vazio")
    @Email(message = "E-mail inválido")
    private String email;
    private String password;
    @NotNull(message = "Telefone não pode ser nulo")
    @NotEmpty(message = "Telefone não pode ser vazio")
    private String phone;
    private String bio;
    private String photoUrl;
    private String jobTitle;
    private String jobDescription;

    // Boolean
    private boolean active = true;

    // LocalDateTime
    private LocalDateTime createdAt;

}
