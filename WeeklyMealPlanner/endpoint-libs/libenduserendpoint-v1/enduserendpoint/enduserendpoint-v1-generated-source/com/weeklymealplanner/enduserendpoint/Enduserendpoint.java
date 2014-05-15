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

package com.weeklymealplanner.enduserendpoint;

/**
 * Service definition for Enduserendpoint (v1).
 *
 * <p>
 * This is an API
 * </p>
 *
 * <p>
 * For more information about this service, see the
 * <a href="" target="_blank">API Documentation</a>
 * </p>
 *
 * <p>
 * This service uses {@link EnduserendpointRequestInitializer} to initialize global parameters via its
 * {@link Builder}.
 * </p>
 *
 * @since 1.3
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public class Enduserendpoint extends com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient {

  // Note: Leave this static initializer at the top of the file.
  static {
    com.google.api.client.util.Preconditions.checkState(
        com.google.api.client.googleapis.GoogleUtils.MAJOR_VERSION == 1 &&
        com.google.api.client.googleapis.GoogleUtils.MINOR_VERSION >= 15,
        "You are currently running with version %s of google-api-client. " +
        "You need at least version 1.15 of google-api-client to run version " +
        "1.16.0-rc of the enduserendpoint library.", com.google.api.client.googleapis.GoogleUtils.VERSION);
  }

  /**
   * The default encoded root URL of the service. This is determined when the library is generated
   * and normally should not be changed.
   *
   * @since 1.7
   */
  public static final String DEFAULT_ROOT_URL = "https://studentwmp.appspot.com/_ah/api/";

  /**
   * The default encoded service path of the service. This is determined when the library is
   * generated and normally should not be changed.
   *
   * @since 1.7
   */
  public static final String DEFAULT_SERVICE_PATH = "enduserendpoint/v1/";

  /**
   * The default encoded base URL of the service. This is determined when the library is generated
   * and normally should not be changed.
   */
  public static final String DEFAULT_BASE_URL = DEFAULT_ROOT_URL + DEFAULT_SERVICE_PATH;

  /**
   * Constructor.
   *
   * <p>
   * Use {@link Builder} if you need to specify any of the optional parameters.
   * </p>
   *
   * @param transport HTTP transport, which should normally be:
   *        <ul>
   *        <li>Google App Engine:
   *        {@code com.google.api.client.extensions.appengine.http.UrlFetchTransport}</li>
   *        <li>Android: {@code newCompatibleTransport} from
   *        {@code com.google.api.client.extensions.android.http.AndroidHttp}</li>
   *        <li>Java: {@link com.google.api.client.googleapis.javanet.GoogleNetHttpTransport#newTrustedTransport()}
   *        </li>
   *        </ul>
   * @param jsonFactory JSON factory, which may be:
   *        <ul>
   *        <li>Jackson: {@code com.google.api.client.json.jackson2.JacksonFactory}</li>
   *        <li>Google GSON: {@code com.google.api.client.json.gson.GsonFactory}</li>
   *        <li>Android Honeycomb or higher:
   *        {@code com.google.api.client.extensions.android.json.AndroidJsonFactory}</li>
   *        </ul>
   * @param httpRequestInitializer HTTP request initializer or {@code null} for none
   * @since 1.7
   */
  public Enduserendpoint(com.google.api.client.http.HttpTransport transport, com.google.api.client.json.JsonFactory jsonFactory,
      com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
    this(new Builder(transport, jsonFactory, httpRequestInitializer));
  }

  /**
   * @param builder builder
   */
  Enduserendpoint(Builder builder) {
    super(builder);
  }

  @Override
  protected void initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest<?> httpClientRequest) throws java.io.IOException {
    super.initialize(httpClientRequest);
  }

  /**
   * Create a request for the method "getEndUser".
   *
   * This request holds the parameters needed by the the enduserendpoint server.  After setting any
   * optional parameters, call the {@link GetEndUser#execute()} method to invoke the remote operation.
   *
   * @param id
   * @return the request
   */
  public GetEndUser getEndUser(java.lang.String id) throws java.io.IOException {
    GetEndUser result = new GetEndUser(id);
    initialize(result);
    return result;
  }

  public class GetEndUser extends EnduserendpointRequest<com.weeklymealplanner.enduserendpoint.model.EndUser> {

    private static final String REST_PATH = "enduser/{id}";

    /**
     * Create a request for the method "getEndUser".
     *
     * This request holds the parameters needed by the the enduserendpoint server.  After setting any
     * optional parameters, call the {@link GetEndUser#execute()} method to invoke the remote
     * operation. <p> {@link
     * GetEndUser#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
     * must be called to initialize this instance immediately after invoking the constructor. </p>
     *
     * @param id
     * @since 1.13
     */
    protected GetEndUser(java.lang.String id) {
      super(Enduserendpoint.this, "GET", REST_PATH, null, com.weeklymealplanner.enduserendpoint.model.EndUser.class);
      this.id = com.google.api.client.util.Preconditions.checkNotNull(id, "Required parameter id must be specified.");
    }

    @Override
    public com.google.api.client.http.HttpResponse executeUsingHead() throws java.io.IOException {
      return super.executeUsingHead();
    }

    @Override
    public com.google.api.client.http.HttpRequest buildHttpRequestUsingHead() throws java.io.IOException {
      return super.buildHttpRequestUsingHead();
    }

    @Override
    public GetEndUser setAlt(java.lang.String alt) {
      return (GetEndUser) super.setAlt(alt);
    }

    @Override
    public GetEndUser setFields(java.lang.String fields) {
      return (GetEndUser) super.setFields(fields);
    }

    @Override
    public GetEndUser setKey(java.lang.String key) {
      return (GetEndUser) super.setKey(key);
    }

    @Override
    public GetEndUser setOauthToken(java.lang.String oauthToken) {
      return (GetEndUser) super.setOauthToken(oauthToken);
    }

    @Override
    public GetEndUser setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (GetEndUser) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public GetEndUser setQuotaUser(java.lang.String quotaUser) {
      return (GetEndUser) super.setQuotaUser(quotaUser);
    }

    @Override
    public GetEndUser setUserIp(java.lang.String userIp) {
      return (GetEndUser) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private java.lang.String id;

    /**

     */
    public java.lang.String getId() {
      return id;
    }

    public GetEndUser setId(java.lang.String id) {
      this.id = id;
      return this;
    }

    @Override
    public GetEndUser set(String parameterName, Object value) {
      return (GetEndUser) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "insertEndUser".
   *
   * This request holds the parameters needed by the the enduserendpoint server.  After setting any
   * optional parameters, call the {@link InsertEndUser#execute()} method to invoke the remote
   * operation.
   *
   * @param content the {@link com.weeklymealplanner.enduserendpoint.model.EndUser}
   * @return the request
   */
  public InsertEndUser insertEndUser(com.weeklymealplanner.enduserendpoint.model.EndUser content) throws java.io.IOException {
    InsertEndUser result = new InsertEndUser(content);
    initialize(result);
    return result;
  }

  public class InsertEndUser extends EnduserendpointRequest<com.weeklymealplanner.enduserendpoint.model.EndUser> {

    private static final String REST_PATH = "enduser";

    /**
     * Create a request for the method "insertEndUser".
     *
     * This request holds the parameters needed by the the enduserendpoint server.  After setting any
     * optional parameters, call the {@link InsertEndUser#execute()} method to invoke the remote
     * operation. <p> {@link InsertEndUser#initialize(com.google.api.client.googleapis.services.Abstra
     * ctGoogleClientRequest)} must be called to initialize this instance immediately after invoking
     * the constructor. </p>
     *
     * @param content the {@link com.weeklymealplanner.enduserendpoint.model.EndUser}
     * @since 1.13
     */
    protected InsertEndUser(com.weeklymealplanner.enduserendpoint.model.EndUser content) {
      super(Enduserendpoint.this, "POST", REST_PATH, content, com.weeklymealplanner.enduserendpoint.model.EndUser.class);
    }

    @Override
    public InsertEndUser setAlt(java.lang.String alt) {
      return (InsertEndUser) super.setAlt(alt);
    }

    @Override
    public InsertEndUser setFields(java.lang.String fields) {
      return (InsertEndUser) super.setFields(fields);
    }

    @Override
    public InsertEndUser setKey(java.lang.String key) {
      return (InsertEndUser) super.setKey(key);
    }

    @Override
    public InsertEndUser setOauthToken(java.lang.String oauthToken) {
      return (InsertEndUser) super.setOauthToken(oauthToken);
    }

    @Override
    public InsertEndUser setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (InsertEndUser) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public InsertEndUser setQuotaUser(java.lang.String quotaUser) {
      return (InsertEndUser) super.setQuotaUser(quotaUser);
    }

    @Override
    public InsertEndUser setUserIp(java.lang.String userIp) {
      return (InsertEndUser) super.setUserIp(userIp);
    }

    @Override
    public InsertEndUser set(String parameterName, Object value) {
      return (InsertEndUser) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "listEndUser".
   *
   * This request holds the parameters needed by the the enduserendpoint server.  After setting any
   * optional parameters, call the {@link ListEndUser#execute()} method to invoke the remote
   * operation.
   *
   * @return the request
   */
  public ListEndUser listEndUser() throws java.io.IOException {
    ListEndUser result = new ListEndUser();
    initialize(result);
    return result;
  }

  public class ListEndUser extends EnduserendpointRequest<com.weeklymealplanner.enduserendpoint.model.CollectionResponseEndUser> {

    private static final String REST_PATH = "enduser";

    /**
     * Create a request for the method "listEndUser".
     *
     * This request holds the parameters needed by the the enduserendpoint server.  After setting any
     * optional parameters, call the {@link ListEndUser#execute()} method to invoke the remote
     * operation. <p> {@link
     * ListEndUser#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
     * must be called to initialize this instance immediately after invoking the constructor. </p>
     *
     * @since 1.13
     */
    protected ListEndUser() {
      super(Enduserendpoint.this, "GET", REST_PATH, null, com.weeklymealplanner.enduserendpoint.model.CollectionResponseEndUser.class);
    }

    @Override
    public com.google.api.client.http.HttpResponse executeUsingHead() throws java.io.IOException {
      return super.executeUsingHead();
    }

    @Override
    public com.google.api.client.http.HttpRequest buildHttpRequestUsingHead() throws java.io.IOException {
      return super.buildHttpRequestUsingHead();
    }

    @Override
    public ListEndUser setAlt(java.lang.String alt) {
      return (ListEndUser) super.setAlt(alt);
    }

    @Override
    public ListEndUser setFields(java.lang.String fields) {
      return (ListEndUser) super.setFields(fields);
    }

    @Override
    public ListEndUser setKey(java.lang.String key) {
      return (ListEndUser) super.setKey(key);
    }

    @Override
    public ListEndUser setOauthToken(java.lang.String oauthToken) {
      return (ListEndUser) super.setOauthToken(oauthToken);
    }

    @Override
    public ListEndUser setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (ListEndUser) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public ListEndUser setQuotaUser(java.lang.String quotaUser) {
      return (ListEndUser) super.setQuotaUser(quotaUser);
    }

    @Override
    public ListEndUser setUserIp(java.lang.String userIp) {
      return (ListEndUser) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private java.lang.String cursor;

    /**

     */
    public java.lang.String getCursor() {
      return cursor;
    }

    public ListEndUser setCursor(java.lang.String cursor) {
      this.cursor = cursor;
      return this;
    }

    @com.google.api.client.util.Key
    private java.lang.Integer limit;

    /**

     */
    public java.lang.Integer getLimit() {
      return limit;
    }

    public ListEndUser setLimit(java.lang.Integer limit) {
      this.limit = limit;
      return this;
    }

    @Override
    public ListEndUser set(String parameterName, Object value) {
      return (ListEndUser) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "removeEndUser".
   *
   * This request holds the parameters needed by the the enduserendpoint server.  After setting any
   * optional parameters, call the {@link RemoveEndUser#execute()} method to invoke the remote
   * operation.
   *
   * @param id
   * @return the request
   */
  public RemoveEndUser removeEndUser(java.lang.String id) throws java.io.IOException {
    RemoveEndUser result = new RemoveEndUser(id);
    initialize(result);
    return result;
  }

  public class RemoveEndUser extends EnduserendpointRequest<Void> {

    private static final String REST_PATH = "enduser/{id}";

    /**
     * Create a request for the method "removeEndUser".
     *
     * This request holds the parameters needed by the the enduserendpoint server.  After setting any
     * optional parameters, call the {@link RemoveEndUser#execute()} method to invoke the remote
     * operation. <p> {@link RemoveEndUser#initialize(com.google.api.client.googleapis.services.Abstra
     * ctGoogleClientRequest)} must be called to initialize this instance immediately after invoking
     * the constructor. </p>
     *
     * @param id
     * @since 1.13
     */
    protected RemoveEndUser(java.lang.String id) {
      super(Enduserendpoint.this, "DELETE", REST_PATH, null, Void.class);
      this.id = com.google.api.client.util.Preconditions.checkNotNull(id, "Required parameter id must be specified.");
    }

    @Override
    public RemoveEndUser setAlt(java.lang.String alt) {
      return (RemoveEndUser) super.setAlt(alt);
    }

    @Override
    public RemoveEndUser setFields(java.lang.String fields) {
      return (RemoveEndUser) super.setFields(fields);
    }

    @Override
    public RemoveEndUser setKey(java.lang.String key) {
      return (RemoveEndUser) super.setKey(key);
    }

    @Override
    public RemoveEndUser setOauthToken(java.lang.String oauthToken) {
      return (RemoveEndUser) super.setOauthToken(oauthToken);
    }

    @Override
    public RemoveEndUser setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (RemoveEndUser) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public RemoveEndUser setQuotaUser(java.lang.String quotaUser) {
      return (RemoveEndUser) super.setQuotaUser(quotaUser);
    }

    @Override
    public RemoveEndUser setUserIp(java.lang.String userIp) {
      return (RemoveEndUser) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private java.lang.String id;

    /**

     */
    public java.lang.String getId() {
      return id;
    }

    public RemoveEndUser setId(java.lang.String id) {
      this.id = id;
      return this;
    }

    @Override
    public RemoveEndUser set(String parameterName, Object value) {
      return (RemoveEndUser) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "updateEndUser".
   *
   * This request holds the parameters needed by the the enduserendpoint server.  After setting any
   * optional parameters, call the {@link UpdateEndUser#execute()} method to invoke the remote
   * operation.
   *
   * @param content the {@link com.weeklymealplanner.enduserendpoint.model.EndUser}
   * @return the request
   */
  public UpdateEndUser updateEndUser(com.weeklymealplanner.enduserendpoint.model.EndUser content) throws java.io.IOException {
    UpdateEndUser result = new UpdateEndUser(content);
    initialize(result);
    return result;
  }

  public class UpdateEndUser extends EnduserendpointRequest<com.weeklymealplanner.enduserendpoint.model.EndUser> {

    private static final String REST_PATH = "enduser";

    /**
     * Create a request for the method "updateEndUser".
     *
     * This request holds the parameters needed by the the enduserendpoint server.  After setting any
     * optional parameters, call the {@link UpdateEndUser#execute()} method to invoke the remote
     * operation. <p> {@link UpdateEndUser#initialize(com.google.api.client.googleapis.services.Abstra
     * ctGoogleClientRequest)} must be called to initialize this instance immediately after invoking
     * the constructor. </p>
     *
     * @param content the {@link com.weeklymealplanner.enduserendpoint.model.EndUser}
     * @since 1.13
     */
    protected UpdateEndUser(com.weeklymealplanner.enduserendpoint.model.EndUser content) {
      super(Enduserendpoint.this, "PUT", REST_PATH, content, com.weeklymealplanner.enduserendpoint.model.EndUser.class);
    }

    @Override
    public UpdateEndUser setAlt(java.lang.String alt) {
      return (UpdateEndUser) super.setAlt(alt);
    }

    @Override
    public UpdateEndUser setFields(java.lang.String fields) {
      return (UpdateEndUser) super.setFields(fields);
    }

    @Override
    public UpdateEndUser setKey(java.lang.String key) {
      return (UpdateEndUser) super.setKey(key);
    }

    @Override
    public UpdateEndUser setOauthToken(java.lang.String oauthToken) {
      return (UpdateEndUser) super.setOauthToken(oauthToken);
    }

    @Override
    public UpdateEndUser setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (UpdateEndUser) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public UpdateEndUser setQuotaUser(java.lang.String quotaUser) {
      return (UpdateEndUser) super.setQuotaUser(quotaUser);
    }

    @Override
    public UpdateEndUser setUserIp(java.lang.String userIp) {
      return (UpdateEndUser) super.setUserIp(userIp);
    }

    @Override
    public UpdateEndUser set(String parameterName, Object value) {
      return (UpdateEndUser) super.set(parameterName, value);
    }
  }

  /**
   * Builder for {@link Enduserendpoint}.
   *
   * <p>
   * Implementation is not thread-safe.
   * </p>
   *
   * @since 1.3.0
   */
  public static final class Builder extends com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient.Builder {

    /**
     * Returns an instance of a new builder.
     *
     * @param transport HTTP transport, which should normally be:
     *        <ul>
     *        <li>Google App Engine:
     *        {@code com.google.api.client.extensions.appengine.http.UrlFetchTransport}</li>
     *        <li>Android: {@code newCompatibleTransport} from
     *        {@code com.google.api.client.extensions.android.http.AndroidHttp}</li>
     *        <li>Java: {@link com.google.api.client.googleapis.javanet.GoogleNetHttpTransport#newTrustedTransport()}
     *        </li>
     *        </ul>
     * @param jsonFactory JSON factory, which may be:
     *        <ul>
     *        <li>Jackson: {@code com.google.api.client.json.jackson2.JacksonFactory}</li>
     *        <li>Google GSON: {@code com.google.api.client.json.gson.GsonFactory}</li>
     *        <li>Android Honeycomb or higher:
     *        {@code com.google.api.client.extensions.android.json.AndroidJsonFactory}</li>
     *        </ul>
     * @param httpRequestInitializer HTTP request initializer or {@code null} for none
     * @since 1.7
     */
    public Builder(com.google.api.client.http.HttpTransport transport, com.google.api.client.json.JsonFactory jsonFactory,
        com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
      super(
          transport,
          jsonFactory,
          DEFAULT_ROOT_URL,
          DEFAULT_SERVICE_PATH,
          httpRequestInitializer,
          false);
    }

    /** Builds a new instance of {@link Enduserendpoint}. */
    @Override
    public Enduserendpoint build() {
      return new Enduserendpoint(this);
    }

    @Override
    public Builder setRootUrl(String rootUrl) {
      return (Builder) super.setRootUrl(rootUrl);
    }

    @Override
    public Builder setServicePath(String servicePath) {
      return (Builder) super.setServicePath(servicePath);
    }

    @Override
    public Builder setHttpRequestInitializer(com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
      return (Builder) super.setHttpRequestInitializer(httpRequestInitializer);
    }

    @Override
    public Builder setApplicationName(String applicationName) {
      return (Builder) super.setApplicationName(applicationName);
    }

    @Override
    public Builder setSuppressPatternChecks(boolean suppressPatternChecks) {
      return (Builder) super.setSuppressPatternChecks(suppressPatternChecks);
    }

    @Override
    public Builder setSuppressRequiredParameterChecks(boolean suppressRequiredParameterChecks) {
      return (Builder) super.setSuppressRequiredParameterChecks(suppressRequiredParameterChecks);
    }

    @Override
    public Builder setSuppressAllChecks(boolean suppressAllChecks) {
      return (Builder) super.setSuppressAllChecks(suppressAllChecks);
    }

    /**
     * Set the {@link EnduserendpointRequestInitializer}.
     *
     * @since 1.12
     */
    public Builder setEnduserendpointRequestInitializer(
        EnduserendpointRequestInitializer enduserendpointRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(enduserendpointRequestInitializer);
    }

    @Override
    public Builder setGoogleClientRequestInitializer(
        com.google.api.client.googleapis.services.GoogleClientRequestInitializer googleClientRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(googleClientRequestInitializer);
    }
  }
}