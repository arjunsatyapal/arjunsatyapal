package com.arjunsatyapal.practice.client.gwtui.mvpinterfaces;

import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.ui.HasWidgets;

import com.arjunsatyapal.practice.client.gwtui.lanternheaderpanel.LanternHeaderPanelDisplay;
import com.arjunsatyapal.practice.shared.ValidParams;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Presenter {
  protected final String historyToken;
  protected final Map<String, List<String>> parametersMap;
  protected LanternHeaderPanelDisplay lanternHeaderPanelDisplay;

  protected Presenter(LanternHeaderPanelDisplay lanternHeaderPanelDisplay, String historyToken) {
    this.lanternHeaderPanelDisplay = lanternHeaderPanelDisplay;
    this.lanternHeaderPanelDisplay.bind();
    this.historyToken = historyToken;
    this.parametersMap = buildListParamMap(getParameters(this.historyToken));
  }

  private String getParameters(String token) {
    if (token.contains("?")) {
      String[] params = token.split("\\?");
      return params[1];
    }
    return null;
  }

  private Map<String, List<String>> getParametersMap() {
    return parametersMap;
  }

  public String getEncodedRedirectHash() {
    List<String> tempList = getParametersMap().get(ValidParams.REDIRECT_HASH.getParamKey());

    if (tempList != null && tempList.size() != 0) {
      return URL.encode(tempList.get(0));
    }

    return null;
  }

  /**
   * Copied from Window.location for GWT as this is not exposed.
   */
  private Map<String, List<String>> buildListParamMap(String queryString) {
    Map<String, List<String>> out = new HashMap<String, List<String>>();

    if (queryString != null && queryString.length() > 1) {
      String qs = queryString;

      for (String kvPair : qs.split("&")) {
        String[] kv = kvPair.split("=", 2);
        if (kv[0].length() == 0) {
          continue;
        }

        List<String> values = out.get(kv[0]);
        if (values == null) {
          values = new ArrayList<String>();
          out.put(kv[0], values);
        }
        values.add(kv.length > 1 ? URL.decodeQueryString(kv[1]) : "");
      }
    }

    for (Map.Entry<String, List<String>> entry : out.entrySet()) {
      entry.setValue(Collections.unmodifiableList(entry.getValue()));
    }

    out = Collections.unmodifiableMap(out);

    return out;
  }

  public abstract void go(final HasWidgets container);
  public abstract void bind();

}
