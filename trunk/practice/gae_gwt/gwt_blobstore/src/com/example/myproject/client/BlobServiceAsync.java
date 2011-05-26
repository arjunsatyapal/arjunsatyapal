package com.example.myproject.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author arjuns@google.com (Arjun Satyapal)
 */
public interface BlobServiceAsync {
  void getBlobStoreUploadUrl(AsyncCallback<String> callback);
}
