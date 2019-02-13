
public class Points {
	
	private int points;
	private int multiplier;
	static private int DEFAULT_MULTIPLIER = 1;

	public Points(int num) {
		points = num;
		multiplier = DEFAULT_MULTIPLIER;
	}
	
	public void add(int num) {
		points += num * multiplier;
	}
	
	public void subtract(int num) {
		points -= num * multiplier;
	}
	
	public int getPoints() {
		return points;
	}
	
	public void setMultiplier(int num) {
		multiplier = num;
	}
	
	public void resetPoints() {
		points = 0;
	}
	
	public void resetMultiplier() {
		multiplier = DEFAULT_MULTIPLIER;
	}
	
}
