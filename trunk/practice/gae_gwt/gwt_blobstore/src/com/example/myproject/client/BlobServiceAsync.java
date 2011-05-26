// Copyright 2011 Google Inc. All Rights Reserved.

package com.example.myproject.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import com.example.myproject.client.entities.Picture;

/**
 * @author arjuns@google.com (Arjun Satyapal)
 *
 */
public interface BlobServiceAsync {

  /**
   * 
   * @see com.example.myproject.client.BlobService#getBlobStoreUploadUrl()
   */
  void getBlobStoreUploadUrl(AsyncCallback<String> callback);

  void getPicture(String id, AsyncCallback<Picture> callback);

}
