package com.bobomee.android.domain.exception;

/**
 * Created on 2016/11/26.下午10:31.
 *
 * @author bobomee.
 * @description
 */

public class ApiException extends
    Exception {

  public ApiException(String message) {
    super(message);
  }
}
