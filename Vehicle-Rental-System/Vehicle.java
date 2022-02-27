import java.util.Arrays;

/**
 * 
 */

/**
 * @author fSociety
 *
 */
public class Vehicle {
	private int[] timeSlot = new int[24];
	private int price;
	private String id;
	
	public Vehicle(int price, String id) {
		this.price = price;
		Arrays.fill(timeSlot, 0);
		this.id = id;
	}
	
	public boolean isAvailable(int startTime, int endTime) {
		for(int i = startTime + 1; i < endTime; i++) {
			if(timeSlot[i] != 0)
				return false;
		}
		return true;
	}
	
	public void bookSlot(int startTime, int endTime) {
		for(int i = startTime;  i <= endTime; i++) {
			timeSlot[i] = 1;
		}
	}
	
	public int getPrice() {
		return price;
	}

	public String getId() {
		return id;
	}
}
