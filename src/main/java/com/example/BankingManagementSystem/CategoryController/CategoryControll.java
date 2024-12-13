package com.example.BankingManagementSystem.CategoryController;

import com.example.BankingManagementSystem.Category.Category;
import com.example.BankingManagementSystem.CategoryImplementation.CategoryImplementation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.ArrayList;
@RestController
@RequestMapping
public class CategoryControll {
    @Autowired
    private CategoryImplementation categoryImplementation;

    @GetMapping("/api/public/categories")
    public ResponseEntity<List<Category>> getAllCategories(){
        List<Category>categories=categoryImplementation.getCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
    @PostMapping("/api/public/categories")
    public ResponseEntity<String>PostCategory(@Valid @RequestBody Category category){
        return new ResponseEntity<>(categoryImplementation.postCategories(category),HttpStatus.CREATED);
    }

    @DeleteMapping("/api/admin/categories/{AccountNumber}")
    public ResponseEntity<String>DeleteCategory(@PathVariable Long AccountNumber){
        try{
            String message=categoryImplementation.deleteCategory(AccountNumber);
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }catch (ResponseStatusException e){
            return new ResponseEntity<>(e.getReason(),e.getStatusCode());
        }
    }
    @PutMapping("/api/public/categories/addamount/{AccountNumber}")
    public ResponseEntity<String>addAmount(@RequestBody Category category,@PathVariable Long AccountNumber){
        try{
            String message=categoryImplementation.AddAmount(category,AccountNumber);
            return new ResponseEntity<>(message,HttpStatus.OK);
        }catch (ResponseStatusException e){
            return new ResponseEntity<>(e.getReason(),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/api/public/categories/withdrawamount/{AccountNumber}")
    public ResponseEntity<String> WithDrawAmount(@RequestBody Category category
            ,@PathVariable Long AccountNumber){
        try{
            String Message=categoryImplementation.withDrawAmount(category,AccountNumber);
            return new ResponseEntity<>(Message,HttpStatus.OK);
        }catch (ResponseStatusException e){
            return new ResponseEntity<>(e.getReason(),HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/api/public/categories")
    public ResponseEntity<String>AmountTransfer(@RequestBody List<Category>categories){
        try{
            if (categories == null || categories.size() < 2) {
                return new ResponseEntity<>("Invalid input: Two categories must be provided.",HttpStatus.BAD_REQUEST);
            }
            String Message=categoryImplementation.amountTransfer(categories);
            return new ResponseEntity<>(Message,HttpStatus.OK);
        }catch (ResponseStatusException e){
            return new ResponseEntity<>(e.getReason(),HttpStatus.BAD_REQUEST);
        }
    }
}
