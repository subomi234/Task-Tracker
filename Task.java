public class Task {
    
    private int id;
    private String description;
    private String status;
    private String createdAt;
    private String updatedAt;

    public Task(){
        id = 0;
        description = "";
        status = "";
        createdAt = "";
        updatedAt = "";
    }

    public Task(int id, String description, String status, 
                String createdAt, String updatedAt){

        this.id = id;
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
