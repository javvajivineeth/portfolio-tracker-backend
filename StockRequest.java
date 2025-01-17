package com.example.portfolio.model.dto;

public class StockRequest { // Added class declaration
    private int id;       // Added private and semicolon
    private String symbol; // Added private and semicolon
    private String name;   // Added private and semicolon

    public void setId(int id) { // Correct syntax
        this.id = id;
    }

    public String getSymbol() { // Correct syntax
        return symbol;
    }

    public String getName() { // Correct syntax
        return name;
    }
}
