package Project;

import java.util.*;

public class Main {

	public static void main(String[] args) {
		
		UserDetails userDetails = UserClass.class.getAnnotation(UserDetails.class);
		AdminstratorDetails adminstratorDetails = AdminstratorClass.class.getAnnotation(AdminstratorDetails.class);
		ChargingStationDetails chargingStationDetails = ChargingUnitClass.class.getAnnotation(ChargingStationDetails.class);

		
		ArrayList<String> Usernames= new ArrayList<>();
		ArrayList<String> CarTypes= new ArrayList<>();
		ArrayList<String> EnergyTypes= new ArrayList<>();
		ArrayList<String> CarPlateNumbers= new ArrayList<>();
		ArrayList<Integer> TimeSlots= new ArrayList<>();
		ArrayList<String> AdminUserNames = new ArrayList<>();
		ArrayList<String> AdminShiftTakens = new ArrayList<>();
		ArrayList<Integer> AdminAges = new ArrayList<>();
		ArrayList<String> Unit_Names = new ArrayList<>();
		ArrayList<String> Unit_Energys = new ArrayList<>();
		

		systemLogger log1=new systemLogger();
		stationLogger log2=new stationLogger();
		energyLogger log3=new energyLogger();
		int i=0;
		
		
		if (userDetails != null) {
			for (User user : userDetails.users()) {
				Usernames.add(user.Name());
				CarTypes.add(user.CarType());
				EnergyTypes.add(user.EnergyType());
				CarPlateNumbers.add(user.CarPlateNumber());
				TimeSlots.add(user.TimeSlot());

			}
		}
		
		if (adminstratorDetails != null) {
			for (Adminstrator adminstrator : adminstratorDetails.adminstrators()) {
				AdminUserNames.add(adminstrator.Name());
				AdminShiftTakens.add(adminstrator.shift_taken());
				AdminAges.add(adminstrator.age());

			}
		}
		
		if (chargingStationDetails != null) {
			for (Charging_Station_Info unit : chargingStationDetails.Stations()) {
				Unit_Names.add(unit.Name());
				Unit_Energys.add(unit.Type());
			}
		}
		
		
		
		for (i=0;i<4;i++) 
		{
			if(i<2) 
			{
			log1.setAdminUserNames(AdminUserNames.get(i));
			log1.setAdminShiftTakens(AdminShiftTakens.get(i));
			log1.setAdminAges(AdminAges.get(i));
			}
		if(i<3) {	
			log1.setUnit_Names(Unit_Names.get(i));
			log1.setUnit_Energys(Unit_Names.get(i));
		}
			log1.setUsername(Usernames.get(i));
			log1.setCar_Type(CarTypes.get(i));
			log1.setPlate(CarPlateNumbers.get(i));
			log1.setEngery_type(EnergyTypes.get(i));
			log1.Task_Update();
			
			
			log2.setUsername(Usernames.get(i));;
			log2.setPlate(CarPlateNumbers.get(i));
			log2.setCar_Type(CarTypes.get(i));
			log2.Task_Update();
			
			log3.setTime_slot(TimeSlots.get(i));
			log3.setEngery_type(EnergyTypes.get(i));
			log3.setCar_Type(CarTypes.get(i));
			log3.Task_Update();
			
		}
		
		
		log1.moveFile("/Users/rizwan/Desktop");	//path	
		
		log2.deleteFile();		
				
		log3.archiveFile();
				
		
			
		}

}
