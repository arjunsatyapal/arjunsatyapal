package com.example.myproject.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Gwt_blobstore implements EntryPoint {

  // You must use a FormPanel to create a blobstore upload form
  final FormPanel uploadForm = new FormPanel();

  // Use an RPC call to the Blob Service to get the blobstore upload url
  BlobServiceAsync blobService = GWT.create(BlobService.class);

  VerticalPanel mainVerticalPanel = new VerticalPanel();
  FileUpload upload = new FileUpload();
  Button submitButton = new Button("Submit");

  @Override
  public void onModuleLoad() {


    mainVerticalPanel.add(upload);

    mainVerticalPanel.add(submitButton);


    uploadForm.setWidget(mainVerticalPanel);

    // The upload form, when submitted, will trigger an HTTP call to the
    // servlet.  The following parameters must be set
    uploadForm.setEncoding(FormPanel.ENCODING_MULTIPART);
    uploadForm.setMethod(FormPanel.METHOD_POST);

    // Set Names for the text boxes so that they can be retrieved from the
    // HTTP call as parameters
    upload.setName("upload");
    
    RootPanel.get().add(uploadForm);

    submitButton.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {

        blobService
            .getBlobStoreUploadUrl(new AsyncCallback<String>() {

              @Override
              public void onSuccess(String result) {
                // Set the form action to the newly created
                // blobstore upload URL
                uploadForm.setAction(result.toString());

                // Submit the form to complete the upload
                uploadForm.submit();
                uploadForm.reset();
              }

              @Override
              public void onFailure(Throwable caught) {
                caught.printStackTrace();
              }
            });

      }
    });
  }
}