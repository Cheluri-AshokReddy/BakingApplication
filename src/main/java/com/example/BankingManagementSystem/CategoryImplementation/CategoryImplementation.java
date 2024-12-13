package com.example.BankingManagementSystem.CategoryImplementation;

import com.example.BankingManagementSystem.Category.Category;
import com.example.BankingManagementSystem.ExceptionHandler.ResourceNotFoundException;
import com.example.BankingManagementSystem.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryImplementation implements CategoryInterface{
    private Long nextId = 100000000001L;
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }
    @Override
    public String postCategories(Category categoryClass) {
        categoryClass.setAccountNumber(nextId);
        categoryRepository.save(categoryClass);
        nextId++;

        return "Category with name " + categoryClass.getName() + " Created successfully..!!";
    }

    @Override
    public String deleteCategory(Long accountNumber) {
        Category category = categoryRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "AccountNumber", accountNumber));
        categoryRepository.delete(category);
        return "Category With Account number " + accountNumber + " Deleted..!!";
    }


    @Override
    public String AddAmount(Category category, Long accountNumber) {
        Category accountcategory=categoryRepository.findByAccountNumber(accountNumber).orElseThrow(
                ()->new ResourceNotFoundException("Category", "AccountNumber", accountNumber));
        if(category==null){
            return "Category with Account number "+accountNumber+" does not exist..!!";
        }else
        {
            Double TotalAmount=category.getAmount()+accountcategory.getAmount();
            accountcategory.setAmount(TotalAmount);
            categoryRepository.save(accountcategory);
            return category.getAmount()+" Amount updated successfully";

        }
    }

    @Override
    public String withDrawAmount(Category category, Long accountNumber) {
        Category accountcategory=categoryRepository.findByAccountNumber(accountNumber).orElseThrow(
                ()->new ResourceNotFoundException("Category", "AccountNumber", accountNumber));

        if(category==null){
            return "Category with Account number "+accountNumber+" does not exist..!!";
        }else{
            if(accountcategory.getAmount()<category.getAmount()){
                return "Sufficent Amount not there in "+accountNumber +"Account";
            }else{
                accountcategory.setAmount(accountcategory.getAmount()-category.getAmount());
                categoryRepository.save(accountcategory);
                return category.getAmount()+" Amount withdraw Successfully";
            }
        }
    }

    @Override
    public String amountTransfer(List<Category>categories) {
        Category category1=categories.get(0); // We are sending amount from category 1 to category 2
        Category category2=categories.get(1);
        Long accountnumber1=category1.getAccountNumber();
        Long accountnumber2=category2.getAccountNumber();
        if(accountnumber1.equals(accountnumber2)){
            return "Both categories are same..!!";
        }
        Category accountcategory1=categoryRepository.findByAccountNumber(accountnumber1).orElseThrow(
                ()->new ResourceNotFoundException("Category", "AccountNumber", accountnumber1));
        Category accountcategory2=categoryRepository.findByAccountNumber(accountnumber2).orElseThrow(
                ()->new ResourceNotFoundException("Category", "AccountNumber", accountnumber2));
        if(accountcategory1==null){
            return "Category with Account number "+accountnumber1+" does not exist..!!";
        }else if(accountcategory2==null){
            return "Category with Account number "+accountnumber2+" does not exist..!!";
        }
        if(accountcategory1.getAmount()<category1.getAmount()){
            return "Sufficent Amount not there in "+category1.getAccountNumber() +"Account";
        }else{
            Double amounttosendaccountnumber2=category1.getAmount();
            accountcategory1.setAmount(accountcategory1.getAmount()-amounttosendaccountnumber2);
            Double amountinsecondaccount=accountcategory2.getAmount();
            accountcategory2.setAmount(amounttosendaccountnumber2+amountinsecondaccount);
            categoryRepository.save(accountcategory1);
            categoryRepository.save(accountcategory2);
            return category1.getAmount()+" Amount Successfully send to "+category2.getAccountNumber();
        }
    }
}
