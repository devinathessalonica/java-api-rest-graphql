package javaapi.payload.request;

import java.util.Date;
import java.util.List;
import javaapi.model.PurchaseDetail;

import javax.validation.constraints.*;
public class PurchaseRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String supplier;

    @NotBlank
    private Date date;

    @NotBlank
    private Double qty;
 
    @NotBlank
    private Double total;
    
    private List<PurchaseDetail> purchaseDetail;
    
  
    public String getSupplier() {
        return supplier;
    }
 
    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }
  
    public Date getDate() {
        return date;
    }
 
    public void setDate(Date date) {
        this.date = date;
    }
  
    public Double getQty() {
        return qty;
    }
 
    public void setQty(Double qty) {
        this.qty = qty;
    }
 
    public Double getTotal() {
        return total;
    }
 
    public void setTotal(Double total) {
        this.total = total;
    }
    
    public List<PurchaseDetail> getPurchaseDetail() {
      return purchaseDetail;
    }
    
    public void setPurchaseDetail(List<PurchaseDetail> purchaseDetail) {
      this.purchaseDetail = purchaseDetail;
    }
}
