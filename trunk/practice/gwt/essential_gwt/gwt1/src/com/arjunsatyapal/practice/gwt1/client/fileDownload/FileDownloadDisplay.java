package com.arjunsatyapal.practice.gwt1.client.fileDownload;

import com.arjunsatyapal.practice.gwt1.client.Display;
import com.arjunsatyapal.practice.gwt1.client.SimpleCallback;
import com.google.gwt.user.client.ui.FormPanel;

public interface FileDownloadDisplay
    extends Display {

  String getParameter1();

  String getParameter2();

  String getParameter3();

  void setDownloadClickCallback(SimpleCallback<Object> scb);

  void setDownloadLinkClickCallback(SimpleCallback<Object> scb);

  void setLinkHref(String href);

  void setSubmitCallback(SimpleCallback<FormPanel.SubmitEvent> scb);

  void submitForm();
}