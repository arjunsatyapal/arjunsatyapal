package com.arjunsatyapal.practice.gwtxml.client.gwtui.xmlinput;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.TextArea;

import com.arjunsatyapal.practice.gwtxml.client.gwtui.mvpinterfaces.Display;

public interface XmlInputDisplay extends Display {
  TextArea getTextBoxXml();

  Button getButtonShowUi();
}
