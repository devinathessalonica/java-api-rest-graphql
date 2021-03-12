package javaapi.model;

import java.io.Serializable;

import javax.persistence.Column;
// @Embeddable
public class PurchaseDetailID implements Serializable {
	/**
     *
     */
    private static final long serialVersionUID = 1L;

    @Column(name = "id_purchase")
    private Integer purchase;

	@Column(name = "id_barang")
    private String barang;

    public Integer getPurchase() {
        return purchase;
    }

    public void setPurchase(Integer purchase) {
        this.purchase = purchase;
    }

    public String getBarang() {
        return barang;
    }

    public void setIdBarang(String barang) {
        this.barang = barang;
    }

    public PurchaseDetailID() {
    }
    public PurchaseDetailID(Integer purchase, String barang) {
        this.purchase = purchase;
        this.barang = barang;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PurchaseDetailID that = (PurchaseDetailID) o;

        if (!purchase.equals(that.purchase)) return false;
        return barang.equals(that.barang);
    }

    @Override
    public int hashCode() {
        int result = purchase.hashCode();
        result = 31 * result + barang.hashCode();
        return result;
    }
}
