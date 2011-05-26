package com.example.myproject.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import com.example.myproject.client.gwtui.uploadonefile.UploadOneFileView;

public class Gwt_blobstore implements EntryPoint {
  BlobServiceAsync blobService = GWT.create(BlobService.class);
  FormPanel formPanel = new FormPanel();
  VerticalPanel verticalPanel = new VerticalPanel();
  FileUpload fileUpload = new FileUpload();
  Button buttonUpload = new Button("Submit");

  //
  @Override
  public void onModuleLoad() {
    RootLayoutPanel root = RootLayoutPanel.get();
    root.clear();
    UploadOneFileView view = new UploadOneFileView();
    root.add(view);
  }
}
