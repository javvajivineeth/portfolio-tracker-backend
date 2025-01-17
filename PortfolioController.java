package com.example.portfolio.controller;

import com.example.portfolio.model.Stock;
import com.example.portfolio.model.User;
import com.example.portfolio.model.UserStock;
import com.example.portfolio.model.dto.StockRequestDTO;
import com.example.portfolio.repository.StockRepository;
import com.example.portfolio.repository.UserRepository;
import com.example.portfolio.repository.UserStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/portfolio")
public class PortfolioController 
{

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserStockRepository userStockRepository;

    @GetMapping("/userstock/{userId}")
    public ResponseEntity<List<UserStock>> getUserStocks(@PathVariable int userId) 
	{
        List<UserStock> userStocks = userStockRepository.findAllByUserId(userId);
        if (userStocks.isEmpty()) 
		{
            return ResponseEntity.noContent().build();
        } 
		else 
		{
            return ResponseEntity.ok(userStocks);
        }
    }

    @PostMapping("/userstock")
    public ResponseEntity<String> createUserStock(@RequestBody StockRequestDTO stockRequestDTO) 
	{
        Optional<User> user = userRepository.findById(stockRequestDTO.getUserId());
        Optional<Stock> stock = stockRepository.findById(stockRequestDTO.getStockId());

        if (user.isPresent() && stock.isPresent()) 
		{
            UserStock userStock = new UserStock();
            userStock.setUserId(stockRequestDTO.getUserId());
            userStock.setStockId(stockRequestDTO.getStockId());
            userStockRepository.save(userStock);
            return ResponseEntity.ok("User Stock created");
        }
        return ResponseEntity.badRequest().body("User or Stock not found");
    }
}
    //@DeleteMapping("/userstock/{id}")
    //public ResponseEntity<String> deleteUserStock(@PathVariable int id) 
	//{
     //Optional<UserStock> userStock = userStockRepository.findById(id);
	//if (userStock.isPresent()) 
	//	}


	
