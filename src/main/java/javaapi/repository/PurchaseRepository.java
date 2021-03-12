package javaapi.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import javaapi.model.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {


}