package Project;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.nio.file.*;

public class systemLogger extends file 
{
	public void Task_Update() 
	{
		try (FileWriter f = new FileWriter("systemlogger.text", true);
				BufferedWriter b = new BufferedWriter(f);
				PrintWriter p = new PrintWriter(b);) 
		{
		
			p.println("==========================");
			
			p.println("Admin detail : ");
			
			p.println("Admin AdminUserName: "+AdminUserNames);
			
			p.println("Admin AdminShiftTaken: "+AdminShiftTakens);
			
			p.println("Admin AdminAge: "+AdminAges);
			
			p.println("Charging unit details : ");
			
			p.println("Unit_Name: "+Unit_Names);
			
			p.println("Engery provided: "+Unit_Energys);
			
			p.println("Systemloggers data :");
			p.println("Car user name: "+username);
			p.println("Car plate :"+plate);
			p.println("Car type :"+car_Type);
			p.println("Arrived time : "+time_slot);
			p.println("Fuel received by car : "+Engery_type);
			System.out.println("Done");
			p.close();
			f.close();
			b.close();
		}
		catch (IOException i) {
			// if anything wrong happen while opening file error will be printed
			i.printStackTrace();
			System.out.println("error");
		}

	}
	
	public void archiveFile() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timestamp = dateFormat.format(new Date());
        String archiveFileName = "archive/" + "systemlogger.text".replace(".", "_" + timestamp + ".");

        try {
            Path source = Paths.get("systemlogger.text");
            Path destination = Paths.get(archiveFileName);

            Files.move(source, destination, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File archived successfully to: " + destination);
        } catch (IOException e) {
            System.err.println("Error archiving the file: " + e.getMessage());
        }
    }
	
	public void moveFile(String destinationDirectory) {
        try {
            Path source = Paths.get("systemlogger.text");
            Path destination = Paths.get(destinationDirectory, source.getFileName().toString());

            Files.move(source, destination, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File moved successfully to: " + destination);
        } catch (IOException e) {
            System.err.println("Error moving the file: " + e.getMessage());
        }
    }
	public void deleteFile() {
        try {
            Files.deleteIfExists(Paths.get("systemlogger.text"));
            System.out.println("File deleted successfully: " + "systemlogger.text");
        } catch (IOException e) {
            System.err.println("Error deleting the file: " + e.getMessage());
        }
    }
}
