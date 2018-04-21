package ar.com.cash.online.backend.model;

public class Paging {

  private Integer offset;
  private Integer limit;
  private Long total;

  public Paging(final Integer offset, final Integer limit, final Long total) {
    this.offset = offset;
    this.limit = limit;
    this.total = total;
  }

  public Integer getOffset() {
    return offset;
  }

  public void setOffset(final Integer offset) {
    this.offset = offset;
  }

  public Integer getLimit() {
    return limit;
  }

  public void setLimit(final Integer limit) {
    this.limit = limit;
  }

  public Long getTotal() {
    return total;
  }

  public void setTotal(final Long total) {
    this.total = total;
  }

}
