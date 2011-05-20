package com.arjunsatyapal.practice.client.event;

import com.google.gwt.event.shared.HandlerManager;

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
  }
}
