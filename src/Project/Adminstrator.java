package Project;
import java.lang.annotation.*;



@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Adminstrator {
	String Name();
	int age();
	String shift_taken();

}
