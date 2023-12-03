package Project;
import java.lang.annotation.*;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface User {
	String Name();
	String CarType();
	String EnergyType();
	String CarPlateNumber();
	int TimeSlot();

}
