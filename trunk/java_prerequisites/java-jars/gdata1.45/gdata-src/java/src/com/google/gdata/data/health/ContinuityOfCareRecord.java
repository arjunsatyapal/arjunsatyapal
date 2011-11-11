/* Copyright (c) 2008 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.google.gdata.data.health;

import com.google.gdata.data.ExtensionDescription;
import com.google.gdata.data.ExtensionPoint;

/**
 * Describes a ccr record.
 *
 * 
 */
@ExtensionDescription.Default(
    nsAlias = HealthNamespace.CCR_ALIAS,
    nsUri = HealthNamespace.CCR,
    localName = ContinuityOfCareRecord.XML_NAME)
public class ContinuityOfCareRecord extends ExtensionPoint {

  /** XML element name */
  static final String XML_NAME = "ContinuityOfCareRecord";

  /**
   * Default mutable constructor.
   */
  public ContinuityOfCareRecord() {
    super();
  }

  /**
   * Returns the extension description, specifying whether it is required, and
   * whether it is repeatable.
   *
   * @param required   whether it is required
   * @param repeatable whether it is repeatable
   * @return extension description
   */
  public static ExtensionDescription getDefaultDescription(boolean required,
      boolean repeatable) {
    ExtensionDescription desc =
        ExtensionDescription.getDefaultDescription(
        ContinuityOfCareRecord.class);
    desc.setRequired(required);
    desc.setRepeatable(repeatable);
    return desc;
  }

  @Override
  public String toString() {
    return "{ContinuityOfCareRecord}";
  }

}
