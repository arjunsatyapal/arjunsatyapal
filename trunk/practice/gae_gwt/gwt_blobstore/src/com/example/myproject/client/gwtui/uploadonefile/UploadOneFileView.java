// Copyright 2011 Google Inc. All Rights Reserved.

package com.example.myproject.client.gwtui.uploadonefile;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

import com.example.myproject.client.BlobService;
import com.example.myproject.client.BlobServiceAsync;

/**
 * @author arjuns@google.com (Arjun Satyapal)
 * 
 */
public class UploadOneFileView extends Composite {
  @UiField
  HorizontalPanel horizontalPanel;
  @UiField
  Button buttonUpload;
  @UiField
  FileUpload fileUpload;
  @UiField
  FormPanel formPanel;
  
  String fileUploadUrl;

  private static UploadOneFileViewUiBinder uiBinder = GWT.create(UploadOneFileViewUiBinder.class);

  interface UploadOneFileViewUiBinder extends UiBinder<Widget, UploadOneFileView> {
  }

  private BlobServiceAsync service = GWT.create(BlobService.class);

  public UploadOneFileView() {
    initWidget(uiBinder.createAndBindUi(this));
    fileUpload.setName("fileUpload");

    buttonUpload.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        service.getBlobStoreUploadUrl(createCallbackForGetUploadUrl());
      }

      private AsyncCallback<String> createCallbackForGetUploadUrl() {
        AsyncCallback<String> callback = new AsyncCallback<String>() {

          @Override
          public void onFailure(Throwable caught) {
            Window.alert("Failed to get the upload url.");

          }

          @Override
          public void onSuccess(String result) {
            fileUploadUrl = result;
            uploadFile();
          }
       
        };
        return callback;
      }
    });
  }
  
  private void uploadFile() {
    if(fileUpload.getFilename().isEmpty()) {
      Window.alert("Please select a file.");
    } else {
      Window.alert("Received Url = " + fileUploadUrl + ". Now trying to upload the file.");
      formPanel.setWidget(horizontalPanel);
      formPanel.setMethod(FormPanel.METHOD_POST);
      formPanel.setEncoding(FormPanel.ENCODING_MULTIPART);
      formPanel.setAction(fileUploadUrl);
      formPanel.submit();
      formPanel.reset();
    }
    
  }
}
