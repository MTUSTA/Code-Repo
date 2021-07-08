public class food {
	private int foodID,calorieCount;
	private String nameOfFood; 
	

	public food(int foodID,String nameOfFood,int calorieCount) {
		this.setFoodID(foodID);
		this.setNameOfFood(nameOfFood);
		this.setCalorieCount(calorieCount);
	}


	public int getCalorieCount() {
		return calorieCount;
	}


	public void setCalorieCount(int calorieCount) {
		this.calorieCount = calorieCount;
	}


	public int getFoodID() {
		return foodID;
	}


	public void setFoodID(int foodID) {
		this.foodID = foodID;
	}


	public String getNameOfFood() {
		return nameOfFood;
	}


	public void setNameOfFood(String nameOfFood) {
		this.nameOfFood = nameOfFood;
	}
}