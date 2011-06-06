package com.arjunsatyapal.practice.gwtxml.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.RootLayoutPanel;

import com.arjunsatyapal.practice.gwtxml.client.gwtui.xmlinput.XmlInputPresenter;
import com.arjunsatyapal.practice.gwtxml.client.gwtui.xmlinput.XmlInputViewUi;

public class Gwtxml implements EntryPoint {
  @Override
  public void onModuleLoad() {
    XmlInputPresenter xmlInputPresenter = new XmlInputPresenter(new XmlInputViewUi(), History.getToken());
    xmlInputPresenter.go(RootLayoutPanel.get());
  }
}
