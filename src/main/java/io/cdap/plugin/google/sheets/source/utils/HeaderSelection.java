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
 * An enum which represent a way of header names selection.
 */
public enum HeaderSelection {
  FIRST_ROW_AS_COLUMNS("firstRowAsColumns"),
  CUSTOM_ROW_AS_COLUMNS("customRowAsColumns"),
  NO_COLUMN_NAMES("noColumnNames");

  private final String value;

  HeaderSelection(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  /**
   * Returns the HeaderSelection.
   * @param value  The value is String type
   * @return   The  HeaderSelection
   */
  public static HeaderSelection fromValue(String value) {
    return Stream.of(HeaderSelection.values())
        .filter(keyType -> keyType.getValue().equalsIgnoreCase(value))
        .findAny()
        .orElseThrow(() -> new InvalidPropertyTypeException(GoogleSheetsSourceConfig.HEADERS_SELECTION_LABEL, value,
          getAllowedValues()));
  }

  public static List<String> getAllowedValues() {
    return Arrays.stream(HeaderSelection.values()).map(v -> v.getValue())
      .collect(Collectors.toList());
  }
}
