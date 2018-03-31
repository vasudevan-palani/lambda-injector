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

package org.apache.nutch.protocol;

import java.util.Arrays;

//Nutch imports
import org.apache.nutch.metadata.Metadata;
import org.apache.nutch.util.MimeUtil;

import com.newtech.Configuration;

public final class Content {

  public static final String DIR_NAME = "content";

  private final static int VERSION = -1;

  private int version;

  private String url;

  private String base;

  private byte[] content;

  private String contentType;

  private Metadata metadata;

  private MimeUtil mimeTypes;

  public Content() {
    metadata = new Metadata();
  }

  public Content(String url, String base, byte[] content, String contentType,
      Metadata metadata, Configuration conf) {

    if (url == null)
      throw new IllegalArgumentException("null url");
    if (base == null)
      throw new IllegalArgumentException("null base");
    if (content == null)
      throw new IllegalArgumentException("null content");
    if (metadata == null)
      throw new IllegalArgumentException("null metadata");

    this.url = url;
    this.base = base;
    this.content = content;
    this.metadata = metadata;

    this.mimeTypes = new MimeUtil(conf);
    this.contentType = getContentType(contentType, url, content);
  }

  public Content(String url, String base, byte[] content, String contentType,
      Metadata metadata, MimeUtil mimeTypes) {

    if (url == null)
      throw new IllegalArgumentException("null url");
    if (base == null)
      throw new IllegalArgumentException("null base");
    if (content == null)
      throw new IllegalArgumentException("null content");
    if (metadata == null)
      throw new IllegalArgumentException("null metadata");

    this.url = url;
    this.base = base;
    this.content = content;
    this.metadata = metadata;

    this.mimeTypes = mimeTypes;
    this.contentType = getContentType(contentType, url, content);
  }

  //
  // Accessor methods
  //

  /** The url fetched. */
  public String getUrl() {
    return url;
  }

  /**
   * The base url for relative links contained in the content. Maybe be
   * different from url if the request redirected.
   */
  public String getBaseUrl() {
    return base;
  }

  /** The binary content retrieved. */
  public byte[] getContent() {
    return content;
  }

  public void setContent(byte[] content) {
    this.content = content;
  }

  /**
   * The media type of the retrieved content.
   * 
   * @see <a href="http://www.iana.org/assignments/media-types/">
   *      http://www.iana.org/assignments/media-types/</a>
   */
  public String getContentType() {
    return contentType;
  }

  public void setContentType(String contentType) {
    this.contentType = contentType;
  }

  /** Other protocol-specific data. */
  public Metadata getMetadata() {
    return metadata;
  }

  /** Other protocol-specific data. */
  public void setMetadata(Metadata metadata) {
    this.metadata = metadata;
  }

  public boolean equals(Object o) {
    if (!(o instanceof Content)) {
      return false;
    }
    Content that = (Content) o;
    return this.url.equals(that.url) && this.base.equals(that.base)
        && Arrays.equals(this.getContent(), that.getContent())
        && this.contentType.equals(that.contentType)
        && this.metadata.equals(that.metadata);
  }

  public String toString() {
    StringBuffer buffer = new StringBuffer();

    buffer.append("Version: " + version + "\n");
    buffer.append("url: " + url + "\n");
    buffer.append("base: " + base + "\n");
    buffer.append("contentType: " + contentType + "\n");
    buffer.append("metadata: " + metadata + "\n");
    buffer.append("Content:\n");
    buffer.append(new String(content)); // try default encoding

    return buffer.toString();

  }

  private String getContentType(String typeName, String url, byte[] data) {
    return this.mimeTypes.autoResolveContentType(typeName, url, data);
  }

}
