package com.example.BankingManagementSystem.Repository;

import com.example.BankingManagementSystem.Category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category ,Long> {

    Optional<Category> findByAccountNumber(Long accountNumber);
}