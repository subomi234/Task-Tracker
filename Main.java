import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.lang.Integer;

public class Main {

    //this method parses through the string 
    private static String parse(String str) {

        //gets all the string after colon excluding comma
        str = str.substring(str.indexOf(":") + 2, str.length());

        if (str.contains(",")) {
            str = str.substring(0, str.length() - 1);
        }
        str = str.replace("\"", "");
 
        return str;
    }

    private static Task createTask(String id, String description, String status, 
                String createdAt, String updatedAt){
            
            id = parse(id);
            description = parse(description);
            status = parse(status);
            createdAt = parse(createdAt);
            updatedAt = parse(updatedAt);            

            return new Task(Integer.valueOf(id), description, status, createdAt, updatedAt);
    }


    
    public static void main(String[] args) {

        List<Task> allTasks = new ArrayList<Task>();


        /*
        need to create a list of all tasks, will be filled by json 
        will have to split lines 
        lot of manual file reading 
         */

        /* 
;        if (args.length > 0) {
            System.out.println(args[0]);
        }
        else {
            System.out.println("You didn't enter any arguments");
        }
        */

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



        try (BufferedReader reader = new BufferedReader(new FileReader("tasks.json"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("id")) {
                    allTasks.add(createTask(line, reader.readLine(), reader.readLine(), reader.readLine(), reader.readLine()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        
        
        allTasks.add(new Task(3, "take out trash", "in-progress", "03/24/2025", "03/25/2025"));
        allTasks.add(new Task(4, "cook dinner", "done", "05/24/2026", "05/25/2026"));
        

        for (int i = 0; i < allTasks.size(); i++) {
            System.out.println(allTasks.get(i).getId());
            System.out.println(allTasks.get(i).getDescription());
            System.out.println(allTasks.get(i).getStatus());
            System.out.println(allTasks.get(i).getCreatedAt());
            System.out.println(allTasks.get(i).getUpdatedAt());
        }
            
         
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("tasks.json"))) {
            writer.write("{\n");
            writer.write("\t\"tasks\":[");

            for (int i = 0; i < allTasks.size() - 1; i++) {
                writer.write("\n");
                writer.write("\t\t{\n");

                writer.write("\t\t\t\"id\": " + allTasks.get(i).getId() + ",\n");
                writer.write("\t\t\t\"description\": \"" + allTasks.get(i).getDescription() + "\",\n");
                writer.write("\t\t\t\"status\": \"" + allTasks.get(i).getStatus() + "\",\n");
                writer.write("\t\t\t\"createdAt\": \"" + allTasks.get(i).getCreatedAt() + "\",\n");
                writer.write("\t\t\t\"updatedAt\": \"" + allTasks.get(i).getUpdatedAt() + "\"\n");

                writer.write("\t\t},");
            }
            
            int i = allTasks.size() - 1;
            writer.write("\n");
            writer.write("\t\t{");

            writer.write("\n\t\t\t\"id\": " + allTasks.get(i).getId() + ",\n");
            writer.write("\t\t\t\"description\": \"" + allTasks.get(i).getDescription() + "\",\n");
            writer.write("\t\t\t\"status\": \"" + allTasks.get(i).getStatus() + "\",\n");
            writer.write("\t\t\t\"createdAt\": \"" + allTasks.get(i).getCreatedAt() + "\",\n");
            writer.write("\t\t\t\"updatedAt\": \"" + allTasks.get(i).getUpdatedAt() + "\"\n");
                
            writer.write("\t\t}");

            writer.write("\n\t]");
            writer.write("\n}");
        } catch (IOException e) {
            e.printStackTrace();
        }
            

            
        
    }
}
