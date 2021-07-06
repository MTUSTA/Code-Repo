import java.util.Calendar;

public class Security extends Personnel {
	private double hourOfWork;
	private double severancePay = (Calendar.getInstance().get(Calendar.YEAR) - getYearOfStart()) * 20 * 8 / 10;
	private double transMoney = 5;
	private double foodMoney = 10;

	public Security(String name, String surname, String registerNumber, String position, int yearOfStart) {
		super(name, surname, registerNumber, position, yearOfStart);
	}

	// They do not work one day of the week ----- For each working hour, they are
	// paid 10 TL.

	public void calculate_hourOfWork(int hours) {
		// Security can work a maximum of 9 hours and a minimum of 5 hours a day. ---->
		// 5*6 =30 and 9*6 = 54
		if (hours > 29 && hours < 55) {
			this.hourOfWork += hours * 10;
		} else if (hours > 54) {
			this.hourOfWork += 54 * 10;
		}
	}

	public double getHourOfWork() {
		return hourOfWork;
	}

	public void setHourOfWork(double hourOfWork) {
		this.hourOfWork = hourOfWork;
	}

	public double getSeverancePay() {
		return severancePay;
	}

	public void setSeverancePay(double severancePay) {
		this.severancePay = severancePay;
	}

	public double getTransMoney() {
		return transMoney;
	}

	public void setTransMoney(double transMoney) {
		this.transMoney = transMoney;
	}

	public double getFoodMoney() {
		return foodMoney;
	}

	public void setFoodMoney(double foodMoney) {
		this.foodMoney = foodMoney;
	}

}
