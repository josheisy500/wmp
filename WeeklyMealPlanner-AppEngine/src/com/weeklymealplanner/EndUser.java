package com.weeklymealplanner;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EndUser {

	@Id
	private String endUserID;

	private String userName;

	private String password;

	private String emailAddress;

	private String gender;

	private String mainGoal;

	private float goalWeight;

	private float currentWeight;

	private float weeklyBudget;

	private String vegetarian;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public float getGoalWeight() {
		return goalWeight;
	}

	public void setGoalWeight(float goalWeight) {
		this.goalWeight = goalWeight;
	}

	public float getCurrentWeight() {
		return currentWeight;
	}

	public void setCurrentWeight(float currentWeight) {
		this.currentWeight = currentWeight;
	}

	public float getWeeklyBudget() {
		return weeklyBudget;
	}

	public void setWeeklyBudget(float weeklyBudget) {
		this.weeklyBudget = weeklyBudget;
	}

	public String getVegetarian() {
		return vegetarian;
	}

	public void setVegetarian(String vegetarian) {
		this.vegetarian = vegetarian;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMainGoal() {
		return mainGoal;
	}

	public void setMainGoal(String mainGoal) {
		this.mainGoal = mainGoal;
	}

	public String getEndUserID() {
		return endUserID;
	}

	public void setEndUserID(String endUserID) {
		this.endUserID = endUserID;
	}

}
