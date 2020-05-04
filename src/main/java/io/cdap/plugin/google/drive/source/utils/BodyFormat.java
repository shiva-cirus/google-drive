/*
 * Copyright © 2019 Cask Data, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package io.cdap.plugin.google.drive.source.utils;

import io.cdap.plugin.google.common.exceptions.InvalidPropertyTypeException;
import io.cdap.plugin.google.drive.source.GoogleDriveSourceConfig;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * An enum which represent a type of file's content.
 */
public enum BodyFormat {
  BYTES("bytes"),
  STRING("string");

  private final String value;

  BodyFormat(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  /**
   * Returns the BodyFormat.
   *
   * @param value the value is String type.
   * @return The BodyFormat
   */
  public static BodyFormat fromValue(String value) {
    return Stream.of(BodyFormat.values())
      .filter(keyType -> keyType.getValue().equalsIgnoreCase(value))
      .findAny()
      .orElseThrow(() ->
                     new InvalidPropertyTypeException(GoogleDriveSourceConfig.BODY_FORMAT_LABEL, value,
                                                      getAllowedValues()));
  }

  public static List<String> getAllowedValues() {
    return Arrays.stream(BodyFormat.values()).map(v -> v.getValue())
      .collect(Collectors.toList());
  }
}
