package models.response;

public class CreateUserResponse {
    private int id;
    private String name;
    private String email;
    private String gender;
    private String status;

    public CreateUserResponse(){}

    public CreateUserResponse(int id, String name, String email, String gender, String status) {
        this.id=id;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.status = status;
    }
    public int getId() {return id;}
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getStatus() {
        return status;
    }

    public void setId(int id) {this.id = id;}

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
