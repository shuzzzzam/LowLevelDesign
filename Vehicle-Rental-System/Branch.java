import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Branch {
	private HashMap<String, List<Vehicle>> vehicleMatrix = new HashMap<String, List<Vehicle>>();
	private String branchName;
	private int vehicleCount;
	private HashSet<String> bookedVehicle = new HashSet<String>();
	public Branch(String name, List<String> vehicleList) {
		this.branchName = name;
		for(int i = 0; i < vehicleList.size(); i++) {
			List<Vehicle> list = new ArrayList<Vehicle>();
			vehicleMatrix.put(vehicleList.get(i), list);
		}
		this.vehicleCount = 0;
	}
	
	public boolean addVehicle(String vehicleType, String vehicleId, int price) {
		if(!vehicleMatrix.containsKey(vehicleType))
			return false;
		List<Vehicle> list = vehicleMatrix.get(vehicleType);
		Vehicle vehicle = new Vehicle(price, vehicleId);
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getPrice() >= price) {
				list.add(i, vehicle);
				vehicleMatrix.put(vehicleType, list);
				return true;
			}
		}
		
		list.add(vehicle);
		vehicleMatrix.put(vehicleType, list);
		this.vehicleCount = getVehicleCount() + 1;
		return true;
	}
	
	public int bookVehicle(String vehicleType, int startTime, int endTime) {
		if(!vehicleMatrix.containsKey(vehicleType))
			return -1;
		List<Vehicle> list = vehicleMatrix.get(vehicleType);
		for(int  i = 0; i < list.size(); i++) {
			Vehicle vehicle = list.get(i);
			if(vehicle.isAvailable(startTime, endTime)) {
				vehicle.bookSlot(startTime, endTime);
				this.bookedVehicle.add(vehicle.getId());
				if((float)(this.bookedVehicle.size() / getVehicleCount()) < 0.8) {
					return vehicle.getPrice() * (endTime - startTime);
				}
				return  ((vehicle.getPrice() + (vehicle.getPrice()/10))) * (endTime - startTime);
				
			}
		}
		return -1;
	}
	
	public void displayVehicle(int startTime, int endTime) {
		Set<String> keySet = vehicleMatrix.keySet();
		String[] array = keySet.toArray(new String[0]);
		String ans = "";
		for(int i = 0; i < array.length; i++) {
			String Type = array[i];
			List<Vehicle> list = vehicleMatrix.get(Type);
			for(int j = 0; j < list.size(); j++) {
				Vehicle vehicle = list.get(j);
				if(vehicle.isAvailable(startTime, endTime)) {
					ans += vehicle.getId();
					ans += ",";
				}
			}
		}
		
		if(ans.length() > 0)
			ans = ans.substring(0, ans.length() - 1);
		System.out.println(ans);
	}
	public String getBranchName() {
		return branchName;
	}

	public int getVehicleCount() {
		return vehicleCount;
	}
}
