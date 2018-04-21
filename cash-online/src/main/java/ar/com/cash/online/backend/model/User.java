package ar.com.cash.online.backend.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "USER")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "USER_ID")
  private Long id;
  @Column(name = "EMAIL")
  private String email;
  @Column(name = "FIRST_NAME")
  private String first_name;
  @Column(name = "LAST_NAME")
  private String last_name;
  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "USER_ID")
  private List<Loan> loans;

  public Long getId() {
    return id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(final String email) {
    this.email = email;
  }

  public String getFirst_name() {
    return first_name;
  }

  public void setFirst_name(final String first_name) {
    this.first_name = first_name;
  }

  public String getLast_name() {
    return last_name;
  }

  public void setLast_name(final String last_name) {
    this.last_name = last_name;
  }

  public List<Loan> getLoans() {
    return loans;
  }

  public void setLoans(final List<Loan> loans) {
    this.loans = loans;
  }

}
