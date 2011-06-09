package com.arjunsatyapal.practice.gwtyoutube.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.youtube.client.YouTubeEmbeddedPlayer;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Gwtyoutube implements EntryPoint {
  public void onModuleLoad() {
    YouTubeEmbeddedPlayer youTubeEmbeddedPlayer = new YouTubeEmbeddedPlayer("JW5meKfy3fY");
    youTubeEmbeddedPlayer.setEnhancedGenieMenu(true);
    youTubeEmbeddedPlayer.setAllowFullScreen(true);
    youTubeEmbeddedPlayer.setFullScreen(true);
    youTubeEmbeddedPlayer.setHeight("320px");
    RootPanel.get().add(youTubeEmbeddedPlayer);
    
  }
}
