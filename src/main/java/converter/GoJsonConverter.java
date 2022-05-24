package converter;


import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import models.response.CreateUserResponse;

public class GoJsonConverter <T>{

    private ObjectMapper objectMapper =new ObjectMapper();

    public GoJsonConverter(){

    }

    public String convertToJsonString(T obj) {
        String x = "";
        try{
            //Convert object to JSON string
            x =  objectMapper.writeValueAsString(obj);
        }catch (Exception e){
            e.printStackTrace();
        }
        return x;
    }

    public CreateUserResponse deserializeCreateUserResponse(String json){
        CreateUserResponse response= null;
        try {
            response = new ObjectMapper().readerFor(CreateUserResponse.class).readValue(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}
