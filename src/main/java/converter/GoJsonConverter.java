package converter;


import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;

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
}
