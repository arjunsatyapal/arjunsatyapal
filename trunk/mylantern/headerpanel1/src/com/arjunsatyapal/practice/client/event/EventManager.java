package com.arjunsatyapal.practice.client.event;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;

public class EventManager {
  private HandlerManager eventBus;
  private static EventManager instance = new EventManager();

  public static EventManager getEventManager() {
    return instance;
  }

  public HandlerManager getEventBus() {
    return eventBus;
  }

  private EventManager() {
    eventBus = new HandlerManager(null);
    bind();
  }

  private void bind() {
    eventBus.addHandler(RedirectToHomeEvent.TYPE,
        new RedirectToHomeEventHandler() {
          @Override
          public void redirectToHome(RedirectToHomeEvent event) {
            HistoryHandler.handleNewToken(LanternEvents.HOME.getToken());
          }
        });

    eventBus.addHandler(RedirectToRegisterOAuthProviderEvent.TYPE,
        new RedirectToRegisterOAuthProviderEventHandler() {
          @Override
          public void redirectToRegisterOAuthProvider(
              RedirectToRegisterOAuthProviderEvent event) {
            HistoryHandler.handleNewToken(LanternEvents.REGISTER_OAUTH_PROVIDER   .getToken());
          }
        });
  }
}
