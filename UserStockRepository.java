package com.example.portfolio.repository;

import com.example.portfolio.model.UserStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserStockRepository extends JpaRepository<UserStock, Integer> {
    List<UserStock> findAllByUserId(int userId);
}