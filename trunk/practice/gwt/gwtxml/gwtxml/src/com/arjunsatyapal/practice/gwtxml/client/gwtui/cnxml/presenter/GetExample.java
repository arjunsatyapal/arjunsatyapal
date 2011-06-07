package com.arjunsatyapal.practice.gwtxml.client.gwtui.cnxml.presenter;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Panel;

public class GetExample {
  public static final int STATUS_CODE_OK = 200;

  public static void doGet(final String url, final Panel panel) {
    RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url);

    try {
      Request response = builder.sendRequest(null, new RequestCallback() {
        @Override
        public void onError(Request request, Throwable exception) {
          Window
              .alert("Failed to get url[" + url + "] due to Reason : " + exception
                  .getLocalizedMessage());
        }

        @Override
        public void onResponseReceived(Request request, Response response) {
          String html = response.getText();

          panel.add(new HTML(html));
        }
      });
    } catch (RequestException e) {
      // Code omitted for clarity
    }
  }
}
