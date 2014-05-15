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
 * Model definition for EndUser.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the enduserendpoint. For a detailed explanation see:
 * <a href="http://code.google.com/p/google-http-java-client/wiki/JSON">http://code.google.com/p/google-http-java-client/wiki/JSON</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class EndUser extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Float currentWeight;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String emailAddress;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String endUserID;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String gender;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Float goalWeight;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String mainGoal;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String password;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String userName;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String vegetarian;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Float weeklyBudget;

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Float getCurrentWeight() {
    return currentWeight;
  }

  /**
   * @param currentWeight currentWeight or {@code null} for none
   */
  public EndUser setCurrentWeight(java.lang.Float currentWeight) {
    this.currentWeight = currentWeight;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getEmailAddress() {
    return emailAddress;
  }

  /**
   * @param emailAddress emailAddress or {@code null} for none
   */
  public EndUser setEmailAddress(java.lang.String emailAddress) {
    this.emailAddress = emailAddress;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getEndUserID() {
    return endUserID;
  }

  /**
   * @param endUserID endUserID or {@code null} for none
   */
  public EndUser setEndUserID(java.lang.String endUserID) {
    this.endUserID = endUserID;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getGender() {
    return gender;
  }

  /**
   * @param gender gender or {@code null} for none
   */
  public EndUser setGender(java.lang.String gender) {
    this.gender = gender;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Float getGoalWeight() {
    return goalWeight;
  }

  /**
   * @param goalWeight goalWeight or {@code null} for none
   */
  public EndUser setGoalWeight(java.lang.Float goalWeight) {
    this.goalWeight = goalWeight;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getMainGoal() {
    return mainGoal;
  }

  /**
   * @param mainGoal mainGoal or {@code null} for none
   */
  public EndUser setMainGoal(java.lang.String mainGoal) {
    this.mainGoal = mainGoal;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getPassword() {
    return password;
  }

  /**
   * @param password password or {@code null} for none
   */
  public EndUser setPassword(java.lang.String password) {
    this.password = password;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getUserName() {
    return userName;
  }

  /**
   * @param userName userName or {@code null} for none
   */
  public EndUser setUserName(java.lang.String userName) {
    this.userName = userName;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getVegetarian() {
    return vegetarian;
  }

  /**
   * @param vegetarian vegetarian or {@code null} for none
   */
  public EndUser setVegetarian(java.lang.String vegetarian) {
    this.vegetarian = vegetarian;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Float getWeeklyBudget() {
    return weeklyBudget;
  }

  /**
   * @param weeklyBudget weeklyBudget or {@code null} for none
   */
  public EndUser setWeeklyBudget(java.lang.Float weeklyBudget) {
    this.weeklyBudget = weeklyBudget;
    return this;
  }

  @Override
  public EndUser set(String fieldName, Object value) {
    return (EndUser) super.set(fieldName, value);
  }

  @Override
  public EndUser clone() {
    return (EndUser) super.clone();
  }

}
