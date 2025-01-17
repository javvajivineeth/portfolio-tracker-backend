package com.example.portfolio.service;

import com.example.portfolio.model.dto.StockRequestDTO;

public interface PortfolioService {

    void addStockToPortfolio(StockRequestDTO stockRequestDTO); // Semicolon added
    void removeStockFromPortfolio(StockRequestDTO stockRequestDTO);

}