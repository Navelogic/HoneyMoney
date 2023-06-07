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
    @Column(name = "first_name")
    @NotNull(message = "Primeiro nome não pode ser nulo")
    @NotEmpty(message = "Primeiro nome não pode ser vazio")
    private String firstName;
    @Column(name = "last_name")
    @NotNull(message = "Sobrenome não pode ser nulo")
    @NotEmpty(message = "Sobrenome nome não pode ser vazio")
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
    @Column(name = "photo_url")
    private String photoUrl;
    @Column(name = "job_title")
    private String jobTitle;
    @Column(name = "job_description")
    private String jobDescription;

    // Boolean
    private boolean active = true;

    // LocalDateTime
    @Column(insertable = false, updatable = false)
    private LocalDateTime createdAt;

    // Constructors
    public Person() {
    }

    public Person(Long id, Address address, String firstName, String lastName, String email, String password, String phone, String bio, String photoUrl, String jobTitle, String jobDescription, boolean active, LocalDateTime createdAt) {
        this.id = id;
        this.address = address;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.bio = bio;
        this.photoUrl = photoUrl;
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
        this.active = active;
        this.createdAt = createdAt;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
