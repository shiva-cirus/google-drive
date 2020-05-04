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

package io.cdap.plugin.google.drive.sink;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.cdap.cdap.api.data.batch.OutputFormatProvider;

import java.util.Map;

/**
 * Provides {@link GoogleDriveOutputFormat}'s class name and configuration.
 */
public class GoogleDriveOutputFormatProvider implements OutputFormatProvider {
  public static final String PROPERTY_CONFIG_JSON = "cdap.google.config";
  public static final Gson GSON = new GsonBuilder().create();

  private final Map<String, String> configMap;

  /**
   *   Constructor for GoogleDriveOutputFormatProvider object.
   * @param config  the GoogleDriveSinkConfig is provided
   */
  public GoogleDriveOutputFormatProvider(GoogleDriveSinkConfig config) {
    this.configMap = new ImmutableMap.Builder<String, String>()
      .put(PROPERTY_CONFIG_JSON, GSON.toJson(config))
      .build();
  }

  @Override
  public String getOutputFormatClassName() {
    return GoogleDriveOutputFormat.class.getName();
  }

  @Override
  public Map<String, String> getOutputFormatConfiguration() {
    return configMap;
  }
}
