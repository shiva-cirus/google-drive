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

package io.cdap.plugin.google.sheets.source.utils;

import io.cdap.plugin.google.common.exceptions.InvalidPropertyTypeException;
import io.cdap.plugin.google.sheets.source.GoogleSheetsSourceConfig;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * An enum which represent a set of titles to be processed.
 */
public enum SheetsToPull {
  ALL("all"),
  NUMBERS("numbers"),
  TITLES("titles");

  private final String value;

  SheetsToPull(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  /**
   *  Returns the SheetsToPull.
   * @param value the value is a String type.
   * @return The SheetsToPull
   */
  public static SheetsToPull fromValue(String value) {
    return Stream.of(SheetsToPull.values())
      .filter(keyType -> keyType.getValue().equalsIgnoreCase(value))
      .findAny()
      .orElseThrow(() -> new InvalidPropertyTypeException(GoogleSheetsSourceConfig.SHEETS_TO_PULL_LABEL, value,
                                                          getAllowedValues()));
  }

  public static List<String> getAllowedValues() {
    return Arrays.stream(SheetsToPull.values()).map(v -> v.getValue())
      .collect(Collectors.toList());
  }
}
