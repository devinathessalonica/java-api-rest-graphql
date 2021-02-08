package javaapi.model;

import javax.persistence.*;

@Entity
@Table(name = "m_merk")
public class Merk {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column(name = "name", nullable = false)
  private String name;

  public Merk() {
  }

  public Merk(Integer id) {
    this.id = id;
  }

  public Merk(String name) {
    this.name = name;
  }

  public Integer getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "User [id=" + id + ", name=" + name +  "]";
  }

}