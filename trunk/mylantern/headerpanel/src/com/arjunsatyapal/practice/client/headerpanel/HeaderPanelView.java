package com.arjunsatyapal.practice.client.headerpanel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class HeaderPanelView extends Composite implements HeaderPanelDisplay {
  private static UserBadgeUiBinder uiBinder = GWT.create(UserBadgeUiBinder.class);

  interface UserBadgeUiBinder extends UiBinder<Widget, HeaderPanelView> {
  }

  public HeaderPanelView() {
    initWidget(uiBinder.createAndBindUi(this));
  }
}
