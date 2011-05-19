package com.arjunsatyapal.practice.client.admin.registerloginproviders;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;

import com.arjunsatyapal.practice.client.ServiceProvider;
import com.arjunsatyapal.practice.client.common.mvpinterfaces.Presenter;
import com.arjunsatyapal.practice.shared.OAuthProvider;
import com.arjunsatyapal.practice.shared.dtos.LoginProviderDto;

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

          AsyncCallback<LoginProviderDto> callback = new AsyncCallback<LoginProviderDto>() {
            @Override
            public void onSuccess(LoginProviderDto result) {
              Window.alert("Received from server : " + result.toString());
            }

            @Override
            public void onFailure(Throwable caught) {
              Window.alert("Got exception : " + caught.getLocalizedMessage());

            }
          };
          LoginProviderDto dto = new LoginProviderDto.Builder()
              .setLoginProvider(selectedProvider.name())
              .setConsumerKey(display.getTextBoxConsumerKey().getText())
              .setConsumerSecret(display.getTextBoxConsumerSecret().getText())
              .build();
          ServiceProvider.getLoginProviderService().addLoginProvider(dto, callback);
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
