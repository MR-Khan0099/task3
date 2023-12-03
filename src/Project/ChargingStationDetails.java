package Project;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)

public @interface ChargingStationDetails {
	Charging_Station_Info[] Stations(); 

}


