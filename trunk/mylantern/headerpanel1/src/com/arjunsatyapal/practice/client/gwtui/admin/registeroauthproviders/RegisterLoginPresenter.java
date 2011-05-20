package com.arjunsatyapal.practice.client.gwtui.admin.registeroauthproviders;

import static com.arjunsatyapal.practice.client.rpc.ServiceProvider.getServiceProvider;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;

import com.arjunsatyapal.practice.client.gwtui.mvpinterfaces.Presenter;
import com.arjunsatyapal.practice.shared.OAuthProviderEnum;
import com.arjunsatyapal.practice.shared.dtos.OAuthProviderDto;

public class RegisterLoginPresenter extends Presenter {
  private final RegisterLoginDisplay display;

  public RegisterLoginPresenter(RegisterLoginDisplay registerLoginDisplay, String historyToken) {
    super(registerLoginDisplay.getLanternHeaderPanel(), historyToken);
    this.display = registerLoginDisplay;
  }

  @Override
  public void bind() {
    for (OAuthProviderEnum curr : OAuthProviderEnum.values()) {
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
          OAuthProviderEnum selectedProvider = OAuthProviderEnum
              .getByOrdinalId(selectedIndex);
          builder.append("OAuthProvider : ").append(selectedProvider.name());
          builder.append("\nConsumerKey : ").append(
              display.getTextBoxConsumerKey().getText());
          builder.append("\nConsumerSecret: ").append(
              display.getTextBoxConsumerSecret().getText());
          Window.alert(builder.toString());

          AsyncCallback<OAuthProviderDto> callback = new AsyncCallback<OAuthProviderDto>() {
            @Override
            public void onSuccess(OAuthProviderDto result) {
              Window.alert("Received from server : " + result.toString());
            }

            @Override
            public void onFailure(Throwable caught) {
              Window.alert("Got exception : " + caught.getLocalizedMessage());

            }
          };
          OAuthProviderDto dto = new OAuthProviderDto.Builder()
              .setOAuthProvider(selectedProvider.name())
              .setConsumerKey(display.getTextBoxConsumerKey().getText())
              .setConsumerSecret(display.getTextBoxConsumerSecret().getText())
              .build();
          getServiceProvider().getOAuthProviderService().addOAuthProvider(dto, callback);
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
