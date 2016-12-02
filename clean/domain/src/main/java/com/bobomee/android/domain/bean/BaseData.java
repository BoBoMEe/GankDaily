package com.bobomee.android.domain.bean;

/**
 * Created on 2016/11/20.下午11:59.
 *
 * @author bobomee.
 * @description
 */

public class BaseData<T> {

  private boolean error;
  private T results;

  public T getResults() {
    return results;
  }

  public void setResults(T results) {
    this.results = results;
  }

  public boolean getError() {
    return error;
  }

  public void setError(boolean error) {
    this.error = error;
  }
}