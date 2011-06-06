package com.arjunsatyapal.practice.gwtxml.client.gwtui.xmlinput;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.RootLayoutPanel;

import com.arjunsatyapal.practice.gwtxml.client.gwtui.cnxml.presenter.CnxmlPresenter;
import com.arjunsatyapal.practice.gwtxml.client.gwtui.cnxml.presenter.CnxmlView;
import com.arjunsatyapal.practice.gwtxml.client.gwtui.mvpinterfaces.Presenter;

public class XmlInputPresenter extends Presenter {
  private final XmlInputDisplay display;

  public XmlInputPresenter(XmlInputDisplay display, String historyToken) {
    super(historyToken);
    this.display = display;
  }

  @Override
  public void go(HasWidgets container) {
    container.clear();
    container.add(display.asWidget());
    bind();
  }

  @Override
  public void bind() {
    display.getButtonShowUi().addClickHandler(new ClickHandler() {

      @Override
      public void onClick(ClickEvent event) {
        if (display.getTextBoxXml().getText().isEmpty()) {
          Window.alert("Please enter the xml.");
        } else {
          CnxmlPresenter presenter = new CnxmlPresenter(new CnxmlView(), historyToken,
              display.getTextBoxXml().getText());
          presenter.go(RootLayoutPanel.get());
        }
      }
    });
  }
}
