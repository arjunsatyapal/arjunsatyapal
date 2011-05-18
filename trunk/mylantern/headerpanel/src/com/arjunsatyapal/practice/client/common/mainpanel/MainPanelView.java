// Copyright 2011 Google Inc. All Rights Reserved.

package com.arjunsatyapal.practice.client.common.mainpanel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author arjuns@google.com (Arjun Satyapal)
 *
 */
public class MainPanelView extends Composite implements MainPanelDisplay {

  @UiField
  MenuItem menuItemAdmin;

  @UiField
  LayoutPanel workspace;

  @Override
  public MenuItem getMenuItemAdmin() {
    return menuItemAdmin;
  }

  @Override
  public LayoutPanel getWorkspace() {
    return workspace;
  }


  private static MainPanelUiBinder uiBinder = GWT
      .create(MainPanelUiBinder.class);

  interface MainPanelUiBinder extends UiBinder<Widget, MainPanelView> {
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
  public MainPanelView() {
    initWidget(uiBinder.createAndBindUi(this));
  }
}
