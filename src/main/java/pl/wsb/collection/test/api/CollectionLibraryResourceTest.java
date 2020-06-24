package pl.wsb.collection.test.api;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.Test;
import pl.wsb.collection.model.AuthenticationRequest;
import pl.wsb.collection.model.AuthenticationResponse;
import pl.wsb.collection.model.ItemRentalRequest;

import javax.ws.rs.core.HttpHeaders;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CollectionLibraryResourceTest {

    private AuthenticationResponse authResponse;
    void logOn(){
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost("http://127.0.0.1:8080/webapi/authenticate");
            ObjectMapper objectMapper = new ObjectMapper();
            StringEntity requestEntity = new StringEntity(
                    objectMapper.writeValueAsString(
                            new AuthenticationRequest()
                                    .eMail("test@test.pl")
                                    .password("QAZaleksy2")
                    ),
                    ContentType.APPLICATION_JSON
            );
            httpPost.setEntity(requestEntity);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");
            CloseableHttpResponse response = client.execute(httpPost);


            this.authResponse = new ObjectMapper()
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

                    .readValue(response.getEntity().getContent(), AuthenticationResponse.class);


            System.out.println(this.authResponse.getAccessToken());
            System.out.println(this.authResponse.getEmailAddress());
            System.out.println(this.authResponse.getExpiresIn());
            System.out.println(this.authResponse.getUserId());
            this.authResponse.getRole().forEach((e)->
            {
                System.out.println(e.getAbbr() + " "+ e.getName());
            });


        }
        catch (IOException ioExp){

            System.out.println(ioExp.getMessage());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    void rentCollectionEntry() {
        try {
            this.logOn();
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPut httpPut = new HttpPut("http://127.0.0.1:8080/webapi/collection_library/rent?id=1");
            httpPut.setHeader("Accept", "application/json");
            httpPut.setHeader("Content-type", "application/json");

            ObjectMapper objectMapper = new ObjectMapper();
            StringEntity requestEntity = new StringEntity(


                    objectMapper.writeValueAsString(
                            new ItemRentalRequest().duration(10).itemId(10)
                    ),
                    ContentType.APPLICATION_JSON
            );
            httpPut.setHeader(HttpHeaders.AUTHORIZATION, "Bearer: "+ this.authResponse.getAccessToken());
            httpPut.setEntity(requestEntity);
            CloseableHttpResponse response = client.execute(httpPut);
            System.out.println(response.getStatusLine());
            System.out.println(response.getStatusLine().getStatusCode());
        }
        catch (IOException ioExp){}
    }

    @Test
    void returnCollectionEntry() {
        try {
            this.logOn();
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPut httpPut = new HttpPut("http://127.0.0.1:8080/webapi/collection_library/return?id=1");
            httpPut.setHeader("Accept", "application/json");
            httpPut.setHeader("Content-type", "application/json");

            ObjectMapper objectMapper = new ObjectMapper();
            StringEntity requestEntity = new StringEntity(


                    objectMapper.writeValueAsString(
                            new ItemRentalRequest().duration(10).itemId(8)
                    ),
                    ContentType.APPLICATION_JSON
            );
            httpPut.setHeader(HttpHeaders.AUTHORIZATION, "Bearer: "+ this.authResponse.getAccessToken());
            httpPut.setEntity(requestEntity);
            CloseableHttpResponse response = client.execute(httpPut);
            System.out.println(response.getStatusLine());
            System.out.println(response.getStatusLine().getStatusCode());
        }
        catch (IOException ioExp){}
    }
}