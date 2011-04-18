package com.arjunsatyapal.practice.gwt1.client.fileDownload;

import com.arjunsatyapal.practice.gwt1.client.Environment;
import com.arjunsatyapal.practice.gwt1.client.Presenter;
import com.arjunsatyapal.practice.gwt1.client.SimpleCallback;
import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.ui.FormPanel.SubmitEvent;

public class FileDownloadPresenter
    extends Presenter<FileDownloadDisplay> {
  public static String PLACE = "download";

  public FileDownloadPresenter(
      final String params,
      final FileDownloadDisplay fileDownloadDisplay,
      final Environment environment) {

    super(params, fileDownloadDisplay, environment);

    fileDownloadDisplay
        .setDownloadLinkClickCallback(new SimpleCallback<Object>() {

          @Override
          public void goBack(final Object result) {
            final String param1 = URL.encode(getDisplay()
                .getParameter1());
            final String param2 = URL.encode(getDisplay()
                .getParameter2());
            final String param3 = URL.encode(getDisplay()
                .getParameter3());

            getDisplay()
                .setLinkHref(
                    "/mvpproject/fileproduce?parameter1=" + param1
                        + "&parameter2=" + param2 + "&parameter3="
                        + param3);
          }
        });

    fileDownloadDisplay
        .setDownloadClickCallback(new SimpleCallback<Object>() {

          @Override
          public void goBack(final Object result) {
            getDisplay().submitForm();
          }
        });

    fileDownloadDisplay
        .setSubmitCallback(new SimpleCallback<SubmitEvent>() {

          @Override
          public void goBack(final SubmitEvent result) {
            // result.cancel();
          }
        });
  }
}
