package com.arjunsatyapal.practice.shared.dtos;

import com.google.gwt.user.client.rpc.IsSerializable;

public interface AbstractDto extends IsSerializable{
  abstract StringBuilder getStringBuilder();

  abstract String validate();
}
