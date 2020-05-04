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

package io.cdap.plugin.google.drive.source;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.InputSplit;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * A split used for mapreduce.
 */
public class GoogleDriveSplit extends InputSplit implements Writable {
  private String fileId;
  private long bytesFrom;
  private long bytesTo;
  private boolean isPartitioned = false;

  @SuppressWarnings("unused")
  public GoogleDriveSplit() {
    // For serialization
  }

  /**
   *  Constructor for GoogleDriveSplit object.
   * @param fileId     the field id is provided with
   * @param bytesFrom  the bytes from is provided with
   * @param bytesTo    the bytes to is provided
   */
  public GoogleDriveSplit(String fileId, Long bytesFrom, Long bytesTo) {
    this.fileId = fileId;
    this.bytesFrom = bytesFrom;
    this.bytesTo = bytesTo;
    this.isPartitioned = true;
  }

  public GoogleDriveSplit(String fileId) {
    this.fileId = fileId;
  }

  @Override
  public void readFields(DataInput dataInput) throws IOException {
    fileId = dataInput.readUTF();
    bytesFrom = dataInput.readLong();
    bytesTo = dataInput.readLong();
    isPartitioned = dataInput.readBoolean();
  }

  @Override
  public void write(DataOutput dataOutput) throws IOException {
    dataOutput.writeUTF(fileId);
    dataOutput.writeLong(bytesFrom);
    dataOutput.writeLong(bytesTo);
    dataOutput.writeBoolean(isPartitioned);
  }

  @Override
  public long getLength() {
    return 0;
  }

  @Override
  public String[] getLocations() {
    return new String[0];
  }

  public String getFileId() {
    return fileId;
  }

  public long getBytesFrom() {
    return bytesFrom;
  }

  public long getBytesTo() {
    return bytesTo;
  }

  public boolean isPartitioned() {
    return isPartitioned;
  }
}
