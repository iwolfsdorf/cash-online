package ar.com.cash.online.backend.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import ar.com.cash.online.backend.model.Loan;
import ar.com.cash.online.backend.model.Page;
import ar.com.cash.online.backend.model.Paging;
import ar.com.cash.online.backend.repository.LoanRepository;

@Repository
public class LoanRepositoryImpl implements LoanRepository {

  @Autowired
  EntityManager entityManager;

  @Override
  public Page<Loan> findAllByUserId(final Integer limit, final Integer offset, final Long user_id) {
    final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    final CriteriaQuery<Loan> query = builder.createQuery(Loan.class);
    final Root<Loan> root = query.from(Loan.class);
    query.select(root);
    Predicate predicate = null;
    if (user_id != null) {
      predicate = builder.conjunction();
      predicate = builder.and(predicate, builder.equal(root.get("user_id"), user_id));
      query.where(predicate);
    }

    final TypedQuery<Loan> typedQuery = entityManager.createQuery(query);
    typedQuery.setFirstResult(offset);
    typedQuery.setMaxResults(limit);
    final List<Loan> loans = typedQuery.getResultList();
    return new Page<Loan>(loans, new Paging(offset, limit, count(predicate)));
  }

  @Override
  public Long count(final Predicate predicate) {
    final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    final CriteriaQuery<Long> query = builder.createQuery(Long.class);
    query.select(builder.count(query.from(Loan.class)));
    if (predicate != null) {
      query.where(predicate);
    }
    return entityManager.createQuery(query).getSingleResult();
  }

}
