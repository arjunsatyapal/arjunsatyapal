package com.arjunsatyapal.practice.client.admin.registerloginproviders;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;

import com.arjunsatyapal.practice.client.common.mvpinterfaces.Presenter;
import com.arjunsatyapal.practice.shared.OAuthProvider;

public class RegisterLoginPresenter extends Presenter {
  private final RegisterLoginDisplay display;

  public RegisterLoginPresenter(RegisterLoginDisplay registerLoginDisplay) {
    super(registerLoginDisplay.getLanternHeaderPanel());
    this.display = registerLoginDisplay;
  }

  @Override
  public void bind() {
    for (OAuthProvider curr : OAuthProvider.values()) {
      display.getListBoxOauthProvider().addItem(curr.name());
    }
    display.getListBoxOauthProvider().addItem("---Select Provider ---");
    display.getListBoxOauthProvider().setSelectedIndex(
        display.getListBoxOauthProvider().getItemCount() - 1);

    display.getButtonSave().addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        StringBuilder builder = new StringBuilder();
        int selectedIndex = display.getListBoxOauthProvider()
            .getSelectedIndex();

        if (selectedIndex == display.getListBoxOauthProvider().getItemCount() - 1) {
          Window.alert("Please Select a OAuthProvider.");
        } else {
          OAuthProvider selectedProvider = OAuthProvider
              .getByOrdinalId(selectedIndex);
          builder.append("OAuthProvider : ").append(selectedProvider.name());
          builder.append("\nConsumerKey : ").append(
              display.getTextBoxConsumerKey().getText());
          builder.append("\nConsumerSecret: ").append(
              display.getTextBoxConsumerSecret().getText());
          Window.alert(builder.toString());
        }
      }
    });
  }

  @Override
  public void go(HasWidgets container) {
    container.clear();
    container.add(display.asWidget());
    bind();
  }
}
