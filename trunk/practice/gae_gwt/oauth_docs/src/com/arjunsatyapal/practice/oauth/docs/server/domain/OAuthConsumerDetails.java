package com.arjunsatyapal.practice.oauth.docs.server.domain;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
public class OAuthConsumerDetails {
  @PrimaryKey
  @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
  private Key key;
}
