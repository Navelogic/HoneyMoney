package br.com.honeymoney.api.model;

import java.time.LocalDateTime;

public class User {
    // Long
    private Long id;

    // String
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String address;
    private String phone;
    private String title;
    private String bio;
    private String imageUrl;

    // Boolean
    private boolean enabled;
    private boolean nonLocked;
    private boolean usingMfa;

    // LocalDateTime
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime lastLogin;
}
