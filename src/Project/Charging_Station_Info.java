package Project;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Charging_Station_Info {
	String Name();
	String Type();
}


