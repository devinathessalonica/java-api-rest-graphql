package javaapi.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "barang")
public class Barang implements Serializable{

	@Id
	@Column(name = "id")
	private String id;

	@Column(name = "nama")
	private String nama;

	@Column(name = "status")
	private int status;

	@ManyToOne
	@JoinColumn(name = "id_m_merk", nullable = false, updatable = false)
	private Merk merk;

	public Barang() {

	}

	public Barang(String id, String nama, int status) {
		this.id = id;
		this.nama = nama;
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Merk getMerk() {
		return merk;
	}

	public void setMerk(Merk merk) {
		this.merk = merk;
	}

	@Override
	public String toString() {
		return "Barang [id=" + id + ", nama=" + nama + ", status=" + status + "]";
	}
}