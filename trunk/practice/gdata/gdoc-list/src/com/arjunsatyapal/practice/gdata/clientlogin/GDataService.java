package com.arjunsatyapal.practice.gdata.clientlogin;

public enum GDataService {
  CALENDAR("cl", "http://picasaweb.google.com/data/feed/api/user/default"),
  CONTACTS("cp", "https://www.google.com/m8/feeds/contacts/default/full");

  private String serviceId;
  private String feed;

  public String getServiceId() {
    return serviceId;
  }

  public String getFeed() {
    return feed;
  }

  private GDataService(String serviceId, String feed) {
    this.serviceId = serviceId;
    this.feed = feed;
  }

  public static GDataService getGDataServiceByOrdinal(int ordinal) {
    for (GDataService currService : GDataService.values()) {
      if (currService.ordinal() == ordinal) {
        return currService;
      }
    }

    throw new RuntimeException("GDataService not found for ordinal : " + ordinal);
  }
}
