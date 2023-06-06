package br.com.honeymoney.api.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "categorys")
public class Category {
    // Long
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // String
    @NotNull(message = "Nome da categoria não pode ser nulo")
    @NotEmpty(message = "Nome da categoria é obrigatório")
    private String name;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Equals and HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        return id.equals(category.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
