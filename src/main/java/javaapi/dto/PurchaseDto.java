package javaapi.dto;

import java.util.Date;
import java.util.List;

import javaapi.model.PurchaseDetail;

public class PurchaseDto {
    private String supplier;
    private Date dates;
    private double total;
    private Integer id;  
    private List<PurchaseDetail> purchaseDetail;

    public List<PurchaseDetail> getPurchaseDetail() {
        return purchaseDetail;
    }

    public void setPurchaseDetail(List<PurchaseDetail> purchaseDetail) {
        this.purchaseDetail = purchaseDetail;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
} 
