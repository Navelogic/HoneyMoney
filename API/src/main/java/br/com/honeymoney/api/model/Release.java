package br.com.honeymoney.api.model;



import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "tb_releases")
public class Release {
    // Long
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // String
    private String description;

    // LocalDate
    @NotNull(message = "O campo data de vencimento é obrigatório")
    @Column(name = "date_expiration")
    private LocalDate dateExpiration;
    @Column(name = "date_payment")
    private LocalDate datePayment;

    // BigDecimal
    @NotNull(message = "O campo valor é obrigatório")
    private BigDecimal price;

    // TupePiche
    @NotNull(message = "O campo tipo é obrigatório")
    @Enumerated(EnumType.STRING)
    private TypeRelease type;

    // Category
    @NotNull(message = "O campo categoria é obrigatório")
    @ManyToOne
    @JoinColumn(name = "id_category")
    private Category category;

    // Client
    @NotNull(message = "O campo cliente é obrigatório")
    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(LocalDate dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public LocalDate getDatePayment() {
        return datePayment;
    }

    public void setDatePayment(LocalDate datePayment) {
        this.datePayment = datePayment;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public TypeRelease getType() {
        return type;
    }

    public void setType(TypeRelease type) {
        this.type = type;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
