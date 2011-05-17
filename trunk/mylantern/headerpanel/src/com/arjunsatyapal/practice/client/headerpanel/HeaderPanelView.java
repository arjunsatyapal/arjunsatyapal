// Copyright 2011 Google Inc. All Rights Reserved.

package com.arjunsatyapal.practice.client.headerpanel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author arjuns@google.com (Arjun Satyapal)
 *
 */
public class HeaderPanelView extends Composite implements HeaderPanelDisplay {

  @UiField
  MenuItem menuItemAdmin;

  @Override
  public MenuItem getMenuItemAdmin() {
    return menuItemAdmin;
  }


  private static HeaderPanelUiBinder uiBinder = GWT
      .create(HeaderPanelUiBinder.class);

  interface HeaderPanelUiBinder extends UiBinder<Widget, HeaderPanelView> {
  }

  /**
   * Because this class has a default constructor, it can be used as a binder
   * template. In other words, it can be used in other *.ui.xml files as
   * follows: <ui:UiBinder xmlns:ui="urn:ui:com.google.gwt.uibinder"
   * xmlns:g="urn:import:**user's package**">
   * <g:**UserClassName**>Hello!</g:**UserClassName> </ui:UiBinder> Note that
   * depending on the widget that is used, it may be necessary to implement
   * HasHTML instead of HasText.
   */
  public HeaderPanelView() {
    initWidget(uiBinder.createAndBindUi(this));
  }
}
