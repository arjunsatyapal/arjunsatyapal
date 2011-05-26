package com.example.myproject.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import com.example.myproject.client.entities.Picture;

@RemoteServiceRelativePath("blobservice")
public interface BlobService extends RemoteService {

  String getBlobStoreUploadUrl();

  Picture getPicture(String id);

}