package com.bank.bank.service;

import com.bank.bank.dao.bankRepo;
import com.bank.bank.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class bankService {

    @Autowired
    bankRepo repo;

    public ResponseEntity<String> addAccount(Account account) {
        try {
            repo.save(account);
            return new ResponseEntity<>("Account added successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error occurred while adding account", HttpStatus.INTERNAL_SERVER_ERROR); // Return 500 Internal Server Error
        }
    }


    public ResponseEntity<List<Account>> getAccount() {
    try {
        return  new ResponseEntity<>(repo.findAll(),HttpStatus.FOUND);
    }catch (Exception e){
        e.printStackTrace();
    }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<List<Account>> getAccountById(int id) {
        Optional<Account> accountOpt = repo.findById(id);

        if (accountOpt.isPresent()) {
            Account account = accountOpt.get();
            List<Account> accounts = new ArrayList<>();
            accounts.add(account);
            return new ResponseEntity<>(accounts, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return 404 if account not found
        }
    }

    public ResponseEntity<String> deleteAccount(int id) {
        try {
            if (repo.existsById(id)) {
                repo.deleteById(id);
                return new ResponseEntity<>("Account deleted successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Account not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error occurred while deleting account", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public String updateAccount(int id, Account updateaccount) {
        Optional<Account> account1 = repo.findAllById(id);
        Account account2 = account1.get();
        account2.setAccountholderName(account2.getAccountholderName());
        return "Updated";
    }


    public ResponseEntity<Account> deposit(int id, double amount) {
        try {
            Optional<Account> accountOpt = repo.findById(id);
            if (!accountOpt.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            Account account = accountOpt.get();
            double newBalance = account.getBalance() + amount;
            account.setBalance(newBalance);
            Account updatedAccount = repo.save(account);
            return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<Account> withdraw(int id, double wamount) {
        try {
            Optional<Account> accountOpt = repo.findById(id);
            if (!accountOpt.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            Account account = accountOpt.get();

            if (account.getBalance() < wamount) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            double newBalance = account.getBalance() - wamount;
            account.setBalance(newBalance);
            repo.save(account);
            return new ResponseEntity<>(account, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}