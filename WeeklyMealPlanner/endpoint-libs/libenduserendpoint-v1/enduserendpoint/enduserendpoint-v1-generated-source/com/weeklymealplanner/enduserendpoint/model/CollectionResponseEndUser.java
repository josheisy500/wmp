/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
/*
 * This code was generated by https://code.google.com/p/google-apis-client-generator/
 * (build: 2014-04-15 19:10:39 UTC)
 * on 2014-05-15 at 15:16:50 UTC 
 * Modify at your own risk.
 */

package com.weeklymealplanner.enduserendpoint.model;

/**
 * Model definition for CollectionResponseEndUser.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the enduserendpoint. For a detailed explanation see:
 * <a href="http://code.google.com/p/google-http-java-client/wiki/JSON">http://code.google.com/p/google-http-java-client/wiki/JSON</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class CollectionResponseEndUser extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.util.List<EndUser> items;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String nextPageToken;

  /**
   * @return value or {@code null} for none
   */
  public java.util.List<EndUser> getItems() {
    return items;
  }

  /**
   * @param items items or {@code null} for none
   */
  public CollectionResponseEndUser setItems(java.util.List<EndUser> items) {
    this.items = items;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getNextPageToken() {
    return nextPageToken;
  }

  /**
   * @param nextPageToken nextPageToken or {@code null} for none
   */
  public CollectionResponseEndUser setNextPageToken(java.lang.String nextPageToken) {
    this.nextPageToken = nextPageToken;
    return this;
  }

  @Override
  public CollectionResponseEndUser set(String fieldName, Object value) {
    return (CollectionResponseEndUser) super.set(fieldName, value);
  }

  @Override
  public CollectionResponseEndUser clone() {
    return (CollectionResponseEndUser) super.clone();
  }

}