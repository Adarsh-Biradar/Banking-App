package com.bank.bank.controller;

import com.bank.bank.entity.Account;
import com.bank.bank.service.bankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("bank")
public class bankController {

    @Autowired
    bankService service;

    @PostMapping("addaccount")
    public ResponseEntity<String> addAccount(@RequestBody Account account) {
        return service.addAccount(account);
    }

    @GetMapping("getAllac")
    public ResponseEntity<List<Account>> getAccount() {
        return service.getAccount();
    }

    @GetMapping("getAllac/{id}")
    public ResponseEntity<List<Account>> getAccountById(@PathVariable int id) {
        return service.getAccountById(id);

    }

    @PostMapping("update/{id}")
    public String updateAccount(@PathVariable int id, @RequestBody Account account) {
        return service.updateAccount(id, account);
    }


    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable int id) {
        return service.deleteAccount(id);
    }

    @PutMapping("deposite/{id}")
    public Account Deposit(@PathVariable int id, @RequestBody Map<String, Double> request) {

        Account account = service.deposit(id, request.get("amount")).getBody();

        return account;
    }

    @PutMapping("withdrwal/{id}")
    public ResponseEntity<Account> withdrawl(@PathVariable int id, @RequestBody Map<String, Double> wamount) {

        return service.withdraw(id, wamount.get("wamount"));
    }


}
