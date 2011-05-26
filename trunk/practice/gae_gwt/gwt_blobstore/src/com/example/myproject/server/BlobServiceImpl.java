package com.example.myproject.server;

import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import com.example.myproject.client.BlobService;

@SuppressWarnings("serial")
public class BlobServiceImpl extends RemoteServiceServlet implements BlobService {

  BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

  // Generate a Blobstore Upload URL from the GAE BlobstoreService
  @Override
  public String getBlobStoreUploadUrl() {
    return blobstoreService.createUploadUrl("/blobstoreexample/uploadservice");
  }
}
