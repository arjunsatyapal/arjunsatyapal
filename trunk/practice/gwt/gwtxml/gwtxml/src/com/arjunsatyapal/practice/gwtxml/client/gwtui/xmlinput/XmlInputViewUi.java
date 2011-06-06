package com.arjunsatyapal.practice.gwtxml.client.gwtui.xmlinput;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;

import com.arjunsatyapal.practice.gwtxml.client.gwtui.mvpinterfaces.View;

public class XmlInputViewUi extends View implements XmlInputDisplay {
  @UiField
  TextArea textAreaXml;
  @UiField
  Button buttonShowUi;

  private static XmlInputUiUiBinder uiBinder = GWT
      .create(XmlInputUiUiBinder.class);

  interface XmlInputUiUiBinder extends UiBinder<Widget, XmlInputViewUi> {
  }

  public XmlInputViewUi() {
    initWidget(uiBinder.createAndBindUi(this));
  }

  @Override
  public void setEnabled(boolean enabled) {
    textAreaXml.setEnabled(enabled);
    buttonShowUi.setEnabled(enabled);
  }

  @Override
  public TextArea getTextBoxXml() {
    return textAreaXml;
  }

  @Override
  public Button getButtonShowUi() {
    return buttonShowUi;
  }
}
