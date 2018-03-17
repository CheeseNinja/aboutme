package Splash;

public class food_info {
	
	private String name, water, calories, protein, sodium, fat;
	
	public food_info(String name_, String water_, String calories_, String protein_, String sodium_, String fat_) {
		name = name_;
		water = water_;
		calories = calories_;
		sodium = sodium_;
		fat = fat_;
	}
	public String getName() {
		return name;
	}
	public String getWater() {
		return water;
	}
	public String getCalories() {
		return calories;
	}
	public String getProtein() {
		return protein;
	}
	public String getSodium() {
		return sodium;
	}
	public String getFat() {
		return fat;
	}
}
