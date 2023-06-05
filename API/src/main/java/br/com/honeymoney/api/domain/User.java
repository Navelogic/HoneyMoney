package br.com.honeymoney.api.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class User {
    private Long id;

    @NotEmpty(message = "Primeiro nome é obrigatório!")
    private String firstName;

    @NotEmpty(message = "Sobrenome é obrigatório!")
    private String lastName;

    @NotEmpty(message = "E-mail é obrigatório!")
    @Email(message = "E-mail inválido! Por favor, passe um e-mail válido!")
    private String email;

    @NotEmpty(message = "Senha não pode ser vazia!")
    private String password;

    private String address;
    private String phone;
    private String title;
    private String bio;
    private String imageUrl;

    private boolean enabled;
    private boolean nonLocked;
    private boolean usingMfa;

    private LocalDateTime createdAt;
}
