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
        return id;
    }

    //create
    public String getDescription(){
        return description;
    }

    //create
    public String getStatus(){

        return status;
    }

    //create
    public String getCreatedAt(){

        return createdAt;
    }

    //create
    public String getUpdatedAt(){

        return updatedAt;
    }

    public void updateStatus(String status){
        this.status = status;
    }

    public void updateDescription(String description){
        this.description = description;
    }


}
