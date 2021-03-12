package javaapi.model;

// import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

// import com.fasterxml.jackson.annotation.JsonBackReference;
// import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "purchase")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "supplier")
    private String supplier;
    @Column(name = "date")
    private Date dates;
    @Column(name = "total")
    private Double total;

    public Purchase() {

    }

    public Purchase(Integer id, String supplier, Date dates) {
        this.id = id;
        this.supplier = supplier;
        this.dates = dates;
    }

    public Integer getId() {
        return id;
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

    // @JsonBackReference
    // @JsonIgnore
    // @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    // @JoinColumn(name = "id_purchase", referencedColumnName = "id")
    // private List<PurchaseDetail> purchaseDetail;

    // public List<PurchaseDetail> getPurchaseDetail() {
    //     return purchaseDetail;
    // }

    // public void setPurchaseDetail(List<PurchaseDetail> purchaseDetail) {
    //     this.purchaseDetail = purchaseDetail;
    // }
}
