/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.nutch.metadata;

import com.newtech.Configuration;

/**
 * This is a simple decorator that adds metadata to any Writable-s that can be
 * serialized by <tt>NutchWritable</tt>. This is useful when data needs to be
 * temporarily enriched during processing, but this temporary metadata doesn't
 * need to be permanently stored after the job is done.
 * 
 * @author Andrzej Bialecki
 */
public class MetaWrapper {
  private Metadata metadata;

  public MetaWrapper() {
    metadata = new Metadata();
  }

  public MetaWrapper(Configuration conf) {
    metadata = new Metadata();
  }

  public MetaWrapper(Metadata metadata, Configuration conf) {
    if (metadata == null)
      metadata = new Metadata();
    this.metadata = metadata;
  }

  /**
   * Get all metadata.
   */
  public Metadata getMetadata() {
    return metadata;
  }

  /**
   * Add metadata. See {@link Metadata#add(String, String)} for more
   * information.
   * 
   * @param name
   *          metadata name
   * @param value
   *          metadata value
   */
  public void addMeta(String name, String value) {
    metadata.add(name, value);
  }

  /**
   * Set metadata. See {@link Metadata#set(String, String)} for more
   * information.
   * 
   * @param name
   * @param value
   */
  public void setMeta(String name, String value) {
    metadata.set(name, value);
  }

  /**
   * Get metadata. See {@link Metadata#get(String)} for more information.
   * 
   * @param name
   * @return metadata value
   */
  public String getMeta(String name) {
    return metadata.get(name);
  }

  /**
   * Get multiple metadata. See {@link Metadata#getValues(String)} for more
   * information.
   * 
   * @param name
   * @return multiple values
   */
  public String[] getMetaValues(String name) {
    return metadata.getValues(name);
  }

//  public void readFields(DataInput in) throws IOException {
//    super.readFields(in);
//    metadata = new Metadata();
//    metadata.readFields(in);
//  }
//
//  public void write(DataOutput out) throws IOException {
//    super.write(out);
//    metadata.write(out);
//  }
}