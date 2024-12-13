package com.example.BankingManagementSystem.Category;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@Data
@Entity(name="CategoryDataBase")
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountNumber;
    private String name;
    private String Gmail;
    private String password;
    private Long pin;
    @Min(value = 0,message = "Amount must be greater than or equal to 0")
    private Double amount;
}