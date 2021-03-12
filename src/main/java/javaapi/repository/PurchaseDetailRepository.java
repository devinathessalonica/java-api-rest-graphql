package javaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import javaapi.model.PurchaseDetail;

public interface PurchaseDetailRepository extends JpaRepository<PurchaseDetail, Integer>{
    Integer deleteByPurchase(Integer id);
}
