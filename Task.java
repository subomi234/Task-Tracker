public class Task {
    
    private int id;
    private String description;
    private String status;
    private String createdAt;
    private String updatedAt;

    private static int idCount = 0;

    public Task(){
        idCount++;

        id = idCount;
        description = "";
        status = "";
        createdAt = "";
        updatedAt = "";
    }

    public Task(String description, String status, 
                String createdAt, String updatedAt){

        idCount++;

        id = idCount;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    //create
    public int getId(){
        return 0;
    }

    //create
    public String getDescription(){

        return "";
    }

    //create
    public String getStatus(){

        return "";
    }

    //create
    public String getCreatedAt(){

        return "";
    }

    //create
    public String getUpdatedAt(){

        return "";
    }

    public void updateStatus(String status){
        //will either change to in progress or done
    }

    public void updateDescription(String description){
        //change description and update time
    }


}
