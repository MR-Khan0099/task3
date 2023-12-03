package Project;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

public class stationLogger extends file 
{
	public void Task_Update() 
	{
		try (FileWriter f = new FileWriter("Stationlogger.text", true);
				BufferedWriter b = new BufferedWriter(f);
				PrintWriter p = new PrintWriter(b);) 
		{
			p.println("==========================");
			p.println("Systemlogger");
			p.println("Car user name: "+username);
			p.println("Car plate :"+plate);
			p.println("Car type :"+car_Type);
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
        String archiveFileName = "archive/" + "Stationlogger.text".replace(".", "_" + timestamp + ".");

        try {
            Path source = Paths.get("Stationlogger.text");
            Path destination = Paths.get(archiveFileName);

            Files.move(source, destination, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File archived successfully to: " + destination);
        } catch (IOException e) {
            System.err.println("Error archiving the file: " + e.getMessage());
        }
    }
	
	public void moveFile(String destinationDirectory) {
        try {
            Path source = Paths.get("Stationlogger.text");
            Path destination = Paths.get(destinationDirectory, source.getFileName().toString());

            Files.move(source, destination, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File moved successfully to: " + destination);
        } catch (IOException e) {
            System.err.println("Error moving the file: " + e.getMessage());
        }
    }
	public void deleteFile() {
        try {
            Files.deleteIfExists(Paths.get("Stationlogger.text"));
            System.out.println("File deleted successfully: " + "Stationlogger.text");
        } catch (IOException e) {
            System.err.println("Error deleting the file: " + e.getMessage());
        }
    }

}
