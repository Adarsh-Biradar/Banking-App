package com.bank.bank.dao;

import com.bank.bank.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface bankRepo extends JpaRepository<Account, Integer> {
    String deleteById(int id);

   // List<Account> findAllById(int id);

    Optional<Account> findAllById(int id);
}
