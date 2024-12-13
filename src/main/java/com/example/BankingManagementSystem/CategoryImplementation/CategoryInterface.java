package com.example.BankingManagementSystem.CategoryImplementation;

import com.example.BankingManagementSystem.Category.Category;

import java.util.List;

public interface CategoryInterface {
    public List<Category> getCategories();

    public String postCategories(Category categoryClass);
    public String deleteCategory(Long id);

    String AddAmount( Category category,Long accountNumber);

    String withDrawAmount(Category category, Long accountNumber);

    String amountTransfer(List<Category>categories);
}
