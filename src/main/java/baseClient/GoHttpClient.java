package baseClient;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.Builder;
import java.net.http.HttpResponse;
import java.util.Map;

public class GoHttpClient {
    private Builder builder;
    private HttpRequest request;
    private final HttpClient httpClient = HttpClient.newBuilder().build();
    private final String BASE_URL = "https://gorest.co.in/public/v2/";

    public HttpRequest getRequest() {
        return request;
    }

    public void setRequest(HttpRequest request) {
        this.request = request;
    }

    public void setupHTTPBuilder(){
        builder = HttpRequest.newBuilder();
    }
    public void createEndpoint(String endpoint){
        builder.uri(URI.create((BASE_URL + endpoint)));
    }

    public void addHeaders(Map<String, String> header){
        for (Map.Entry<String, String> entry: header.entrySet()){
            builder.header(entry.getKey(), entry.getValue());
        }
    }
    public void addPostMethod(String body){
        builder.POST(HttpRequest.BodyPublishers.ofString(body));
    }

    public void createRequest(){
        request = builder.build();
    }

    public HttpResponse getResponse(HttpRequest request){
        HttpResponse res = null;
        try{
                res = httpClient.send(request,HttpResponse.BodyHandlers.ofString());
        }catch (IOException | InterruptedException e){
            e.printStackTrace();
        }
        return  res;
    }
}
