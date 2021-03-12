package javaapi.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@IdClass(PurchaseDetailID.class)
@Table(name = "purchase_detail")
public class PurchaseDetail {
    
    // @ManyToOne(fetch = FetchType.LAZY,cascade={CascadeType.REMOVE})
    // @JoinColumn(name="id_purchase",insertable = false, updatable = false)
    // private Purchase pur;

    @Id
	@Column(name = "id_purchase")
    private Integer purchase;
    @Id
	@Column(name = "id_barang")
    private String barang;

	@Column(name = "qty")
    private Double qty;
	@Column(name = "total")
    private Double total;

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
    // public void setPurchaseID(Purchase purchase) {
    //     this.purchase = purchase;
    // }
    
    // public void setBarang(Barang barang) {
    //     this.barang = barang;
    // }
    public void setPurchase(Integer purchase) {
        this.purchase = purchase;
    }
    
    public void setBarang(String barang) {
        this.barang = barang;
    }

    public String getBarang() {
        return barang;
    }

    public Integer getPurchase() {
        return purchase;
    }


	


}
