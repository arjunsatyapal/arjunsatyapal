package com.arjunsatyapal.practice.gwt1.client.fileUpload;

import com.arjunsatyapal.practice.gwt1.client.Display;
import com.arjunsatyapal.practice.gwt1.client.SimpleCallback;
import com.google.gwt.user.client.ui.FormPanel;

public interface FileUploadDisplay
    extends Display {

  String getFileToUploadName();

  void setSubmitCallback(SimpleCallback<FormPanel.SubmitEvent> scb);

  void setSubmitCompleteCallback(
      SimpleCallback<FormPanel.SubmitCompleteEvent> scb);

  void setUploadClickCallback(SimpleCallback<Object> scb);

  void submitForm();
}
