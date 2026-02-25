import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class Main {
    
    public static void main(String[] args) {

        List<Task> allTasks = new ArrayList<Task>();

        /*
        need to create a list of all tasks, will be filled by json 
        will have to split lines 
        lot of manual file reading 
         */
        if (args.length > 0) {
            System.out.println(args[0]);
        }
        else {
            System.out.println("You didn't enter any arguments");
        }

        try {
            //create file object 
            File myTasks = new File("tasks.json");

            //try to create the file 
            if (myTasks.createNewFile()) {
                System.out.println("File created " + myTasks.getName());
            }
            else {
                System.out.println("File already exists");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace(); // Print error details
        }

        /* 
        StringBuilder sb = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader("data.json"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        */

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("tasks.json"))) {
            writer.write("{\n");
            writer.write("\t\"" + args[0] + "\"");
            writer.write(": 19");
            writer.write("\n}");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}
