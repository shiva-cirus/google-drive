/*
 * Copyright © 2020 Cask Data, Inc.
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

package io.cdap.plugin.google.sheets.sink;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.cdap.cdap.api.data.batch.OutputFormatProvider;

import java.util.Map;

/**
 * Provides {@link GoogleSheetsOutputFormat}'s class name and configuration.
 */
public class GoogleSheetsOutputFormatProvider implements OutputFormatProvider {
  public static final String PROPERTY_CONFIG_JSON = "cdap.google.config";
  public static final Gson GSON = new GsonBuilder().create();

  private final Map<String, String> configMap;

  /**
   * Constructor for GoogleSheetsOutputFormatProvider object.
   * @param config the google sheets sink config is provided
   */
  public GoogleSheetsOutputFormatProvider(GoogleSheetsSinkConfig config) {
    this.configMap = new ImmutableMap.Builder<String, String>()
      .put(PROPERTY_CONFIG_JSON, GSON.toJson(config))
      .build();
  }

  @Override
  public String getOutputFormatClassName() {
    return GoogleSheetsOutputFormat.class.getName();
  }

  @Override
  public Map<String, String> getOutputFormatConfiguration() {
    return configMap;
  }
}
