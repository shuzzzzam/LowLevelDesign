import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Geektrust {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws FileNotFoundException {
		String filePath = args[0];
		HashMap<String, Branch> branchList = new HashMap<String, Branch>();
		FileInputStream fis=new FileInputStream(filePath);
		Scanner sc=new Scanner(fis);
		
		while(sc.hasNextLine()) {
			String line = sc.nextLine();
			StringTokenizer st = new StringTokenizer(line," ");
			
			while(st.hasMoreTokens()) {
				String firstToken = st.nextToken();
				if(firstToken.equals("ADD_BRANCH")) {
					String name = st.nextToken();
					String types = st.nextToken();
					if(branchList.containsKey(name)) {
						System.out.println(false);
						break;
					}
					StringTokenizer typeToken = new StringTokenizer(types,",");
					List<String> list = new ArrayList<String>();
					while(typeToken.hasMoreTokens()) {
						list.add(typeToken.nextToken());
					}
					Branch newBranch = new Branch(name, list);
					branchList.put(name, newBranch);
					System.out.println(true);
				}
				else if(firstToken.equals("ADD_VEHICLE")) {
					String name = st.nextToken();
					String type = st.nextToken();
					String vehicleId = st.nextToken();
					int price = Integer.parseInt(st.nextToken());
					
					Branch branch = branchList.get(name);
					
					if(branch.addVehicle(type, vehicleId, price)) {
						System.out.println(true);
						branchList.put(name, branch);
						break;
					}
					
					System.out.println(false);
					
				}
				else if(firstToken.equals("BOOK")) {
					String name = st.nextToken();
					String type = st.nextToken();
					
					int startTime = Integer.parseInt(st.nextToken());
					int endTime = Integer.parseInt(st.nextToken());
					
					Branch branch = branchList.get(name);
					int cost = branch.bookVehicle(type, startTime, endTime);
					System.out.println(cost);
					branchList.put(name, branch);
				}
				
				else if(firstToken.equals("DISPLAY_VEHICLES")) {
					String name = st.nextToken();
					int startTime = Integer.parseInt(st.nextToken());
					int endTime = Integer.parseInt(st.nextToken());
					Branch branch = branchList.get(name);
					branch.displayVehicle(startTime, endTime);
					
				}
			}
		}
	}

}
