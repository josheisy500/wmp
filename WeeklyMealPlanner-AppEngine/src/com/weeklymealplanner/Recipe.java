package com.weeklymealplanner;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Recipe {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	Long k;

	@Persistent
	private String name;
	@Persistent
	private String ingredients;
	@Persistent
	private String method;
	@Persistent
	private float calories;
	@Persistent
	private float protein;
	@Persistent
	private float carbohydrates;
	@Persistent
	private float fats;
	@Persistent
	private float cost;

	Recipe() {

	}

	Recipe(String name, String method, String ingredients) {
		this.name = name;
		this.method = method;
		this.ingredients = ingredients;
		this.calories = 0;
		this.protein = 0;
		this.carbohydrates = 0;
		this.fats = 0;
		this.cost = 0;
	}

	Recipe(String name, String method, String ingredients, float calories,
			float protein, float carbohydrates, float fats, float cost) {
		this.name = name;
		this.method = method;
		this.ingredients = ingredients;
		this.calories = calories;
		this.protein = protein;
		this.carbohydrates = carbohydrates;
		this.fats = fats;
		this.cost = cost;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public float getCalories() {
		return calories;
	}

	public void setCalories(float calories) {
		this.calories = calories;
	}

	public float getProtein() {
		return protein;
	}

	public void setProtein(float protein) {
		this.protein = protein;
	}

	public float getCarbohydrates() {
		return carbohydrates;
	}

	public void setCarbohydrates(float carbohydrates) {
		this.carbohydrates = carbohydrates;
	}

	public float getFats() {
		return fats;
	}

	public void setFats(float fats) {
		this.fats = fats;
	}

	public Long getId() {// this returned a key
		return k;
	}

}
