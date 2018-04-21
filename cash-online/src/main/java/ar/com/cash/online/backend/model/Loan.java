package ar.com.cash.online.backend.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LOAN")
public class Loan {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "LOAN_ID")
  private Integer id;
  @Column(name = "TOTAL")
  private BigDecimal total;
  private Long user_id;

  public Loan() {

  }

  public Loan(final Integer id, final BigDecimal total, final Long user_id) {
    this.id = id;
    this.total = total;
    this.user_id = user_id;
  }

  public Integer getId() {
    return id;
  }

  public void setId(final Integer id) {
    this.id = id;
  }

  public BigDecimal getTotal() {
    return total;
  }

  public void setTotal(final BigDecimal total) {
    this.total = total;
  }

  public Long getUser_id() {
    return user_id;
  }

  public void setUser_id(Long user_id) {
    this.user_id = user_id;
  }

}
