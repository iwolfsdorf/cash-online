package ar.com.cash.online.backend.model;

import java.util.List;

public class Page<T> {

  private List<T> items;
  private Paging paging;

  public Page(final List<T> items, final Paging paging) {
    this.items = items;
    this.paging = paging;
  }

  public List<T> getItems() {
    return items;
  }

  public void setItems(final List<T> items) {
    this.items = items;
  }

  public Paging getPaging() {
    return paging;
  }

  public void setPaging(final Paging paging) {
    this.paging = paging;
  }

}
