package com.arjunsatyapal.practice.gwt1.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class SimpleCallback<T>
    implements AsyncCallback<T> {

  @Override
  public final void onFailure(Throwable caught) {
    // Should never be used...
  }

  @Override
  public final void onSuccess(T result) {
    goBack(result);
  }

  public abstract void goBack(T result);
}
