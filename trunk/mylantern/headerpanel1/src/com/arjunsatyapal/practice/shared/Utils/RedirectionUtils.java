package com.arjunsatyapal.practice.shared.Utils;

import com.arjunsatyapal.practice.client.event.LanternToken;
import com.arjunsatyapal.practice.shared.ValidParams;

public class RedirectionUtils {
  // Utility class.
  private RedirectionUtils() {
  }

  public static String generateValidParamsToAttach(ValidParams validParamsKey, LanternToken lanternToken) {
    return generateValidParamsToAttach(validParamsKey, lanternToken.getToken());
  }

  public static String generateValidParamsToAttach(ValidParams validParamsKey, String token) {
    return validParamsKey.getParamKey() + "=" + token;
  }
}
