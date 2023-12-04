package Project;

import java.util.Arrays;
import java.util.Random;


public class ChargingSystem {
	private static final int HOURS = 24;
	private static final int CHARGING_UNITS = 3;
	static String[][] Prioritized_Queue = new String[HOURS][CHARGING_UNITS];

	public static void main(String[] args) {
		Reservation_System(UserClass.class, AdminstratorClass.class, ChargingUnitClass.class);
		processDay(AdminstratorClass.class, ChargingUnitClass.class);
	}

	private static void Reservation_System(Class<?> userClass, Class<?> adminstratorClass, Class<?> chargingUnitClass) {
		UserDetails userDetails = userClass.getAnnotation(UserDetails.class);
		AdminstratorDetails adminstratorDetails = adminstratorClass.getAnnotation(AdminstratorDetails.class);

		for (String[] row : Prioritized_Queue) {
			Arrays.fill(row, "");
		}

		if (userDetails != null) {
			for (User user : userDetails.users()) {
				reserveSlot(user.CarPlateNumber(), user.TimeSlot(), user.CarType(),ChargingUnitClass.class);
			}

		}
		
		// Print the initial reservations
		System.out.println("Initial Reservations:");
		printReservations(adminstratorDetails);
	}

    private static void processDay(Class<?> adminstratorClass, Class<?> chargingUnitClass) {
        AdminstratorDetails adminstratorDetails = adminstratorClass.getAnnotation(AdminstratorDetails.class);
        ChargingStationDetails chargingStationDetails = chargingUnitClass.getAnnotation(ChargingStationDetails.class);

        System.out.println("\nProcessing Reservations Day:");

        // Create and start threads for each charging unit
        Thread[] threads = new Thread[CHARGING_UNITS];
        for (int unit = 0; unit < CHARGING_UNITS; unit++) {
            final int finalUnit = unit; // Create a final variable for use inside the lambda
            threads[unit] = new Thread(() -> chargeVehicles(finalUnit, chargingStationDetails, adminstratorDetails));
            threads[unit].start();
        }

        // Wait for all threads to finish
        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    


    private static void chargeVehicles(int unit, ChargingStationDetails chargingStationDetails, AdminstratorDetails adminstratorDetails) {
        Random rand = new Random();
        StringBuilder hourlyOutput = new StringBuilder();

        for (int hour = 0; hour < HOURS; hour++) {
            if (hour == 0) {
                hourlyOutput.append("Shift Start: Chief - ").append(chargingStationDetails.Stations()[unit].Name()).append("\n");
            } else if (hour == 13) {
                hourlyOutput.append("Shift Start: Chief - ").append(chargingStationDetails.Stations()[unit].Name()).append("\n");
            }

            hourlyOutput.append("\nHour ").append(String.format("%02d", hour)).append(" at unit ").append(chargingStationDetails.Stations()[unit].Name()).append(":\n");
            if (!Prioritized_Queue[hour][unit].equals("")) {
                String carType = Prioritized_Queue[hour][unit].equalsIgnoreCase("E") ? "Electric" : "Solar";
                hourlyOutput.append("Charging: Car (").append(Prioritized_Queue[hour][unit]).append(") of type ")
                        .append(carType).append("\n");
                Prioritized_Queue[hour][unit] = ""; // Clear the processed reservation
            }

            if (rand.nextBoolean()) {
                String carType = rand.nextBoolean() ? "Electric" : "Solar";
                int waitingTime = calculateWaitingTime(hour, unit);
                if (waitingTime <= 1) {
                    reserveSlot("RandomCar", hour, carType, ChargingUnitClass.class);
                } else {
                    hourlyOutput.append("Car left the queue due to waiting time exceeding 1 slot.\n");
                }
            }
        }

        // Print the status of the array for the entire day after each hour is processed
        printHourlyReservations(hourlyOutput.toString(), adminstratorDetails);
    }

    private static void printHourlyReservations(String hourlyOutput, AdminstratorDetails adminstratorDetails) {
        System.out.println(hourlyOutput);
        printReservations(adminstratorDetails); // Print the status of the array after each hour

        // Add a clear separator to distinguish between different days
        System.out.println("\n-------------------------------------------------------------\n");
    }

   private static void printReservations(AdminstratorDetails adminstratorDetails) {
        synchronized(System.out) {
            System.out.println("Reservations:");
            System.out.printf("%-6s", "Hour");
    
            for (int unit = 0; unit < CHARGING_UNITS; unit++) {
                System.out.printf("%-25s", "Unit " + unit);
            }
            System.out.println();
    
            for (int hour = 0; hour < HOURS; hour++) {
                System.out.printf("%-6d", hour);
    
                for (int unit = 0; unit < CHARGING_UNITS; unit++) {
                    System.out.printf("%-25s", formatReservation(Prioritized_Queue[hour][unit]));
                }
                System.out.println();
            }
            System.out.println();
        }
    }


    private static String formatReservation(String reservation) {
        if (reservation.isEmpty()) {
            return "Empty";
        } else if (reservation.equals("Car left the queue due to waiting time exceeding 1 slot.")) {
            return "Car left queue";
        } else {
            return "Reserved: " + reservation;
        }
    }


    private static int calculateWaitingTime(int hour, int unit) {
        int waitingTime = 0;
        for (int i = hour - 1; i >= 0; i--) {
            if (!Prioritized_Queue[i][unit].equals("")) {
                waitingTime += hour - i;
                break;
            }
        }
        return waitingTime;
    }



	public static void reserveSlot(String carPlateNumber, int hour, String chargingType, Class<?> chargingUnitClass) {
		int startUnit = 0;
		int endUnit = CHARGING_UNITS;
		ChargingStationDetails chargingStationDetails = chargingUnitClass.getAnnotation(ChargingStationDetails.class);

		

		if (chargingType.equalsIgnoreCase("Electric")) {
			endUnit = Math.min(endUnit, 2);
		} else if (chargingType.equalsIgnoreCase("Solar")) {
			startUnit = Math.max(startUnit, 2);
		}

		for (int unit = startUnit; unit < endUnit; unit++) {
			if (Prioritized_Queue[hour][unit].equals("")) {
				Prioritized_Queue[hour][unit] = chargingType.substring(0, 1).toUpperCase();
				System.out.println(
						carPlateNumber + " reserved at hour " + hour + " on unit " + unit + " for " + chargingType);
				return; 
			}
		}

		for (int nextHour = (hour + 1) % HOURS; nextHour != hour; nextHour = (nextHour + 1) % HOURS) {
			for (int unit = startUnit; unit < endUnit; unit++) {
				if (Prioritized_Queue[nextHour][unit].equals("")) {
					Prioritized_Queue[nextHour][unit] = chargingType.substring(0, 1).toUpperCase();
					System.out.println(carPlateNumber + " reserved at hour " + nextHour + " on unit " + chargingStationDetails.Stations()[unit].Name()
 + " for "
							+ chargingType);
					return; 
				}
			}
		}
		System.out.println("No available slots for " + carPlateNumber + " at hour " + hour + " for " + chargingType);
	}
}
