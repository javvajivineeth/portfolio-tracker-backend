package com.example.portfolio.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user_stocks")
@Data
public class UserStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "stock_id")
    private int stockId;

    // ... other fields, getters, and setters
}