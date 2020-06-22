package pl.wsb.collection.test.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import pl.wsb.collection.model.AuthenticationRequest;
import pl.wsb.collection.model.AuthenticationResponse;
import pl.wsb.collection.model.AuthorRequest;
import pl.wsb.collection.model.ItemRequest;

import javax.ws.rs.core.HttpHeaders;
import java.io.IOException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class CollectionEntryResourceTest {

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
            this.authResponse = new ObjectMapper().readValue(response.getEntity().getContent(), AuthenticationResponse.class);
            System.out.println(this.authResponse.getAccessToken());
            System.out.println(this.authResponse.getEmailAddress());
            System.out.println(this.authResponse.getExpiresIn());
            System.out.println(this.authResponse.getUserId());

        }
        catch (IOException ioExp){}
    }

    @Test
    void postCollectionEntry() {
        try {
            this.logOn();
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost("http://127.0.0.1:8080/webapi/collectionEntry");
            System.out.println("Test");
            ObjectMapper objectMapper = new ObjectMapper();
            StringEntity requestEntity = new StringEntity(
                    objectMapper.writeValueAsString(
                            new ItemRequest()
                            .author(new AuthorRequest().firstName("Adam").lastName("Konieczny"))
                            .genre("Adventure")
                            .dateOfRelease(new Date())
                            .title("Testowa ksiazka 2")
                            .publisher("Wydawca")
                            .type("BOOK")
                    ),
                    ContentType.APPLICATION_JSON
            );
            httpPost.setHeader(HttpHeaders.AUTHORIZATION, "Bearer: "+ this.authResponse.getAccessToken());
            httpPost.setEntity(requestEntity);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");
            CloseableHttpResponse response = client.execute(httpPost);

            System.out.println(EntityUtils.toString(response.getEntity()));

        }
        catch (IOException ioExp){}
    }

    /*@Test
    void putCollectionEntryIdAccept() {
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPut httpPost = new HttpPut("http://127.0.0.1:8080/webapi/collectionEntry/5/accept");
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");
            CloseableHttpResponse response = client.execute(httpPost);
            System.out.println(response.getStatusLine());
            System.out.println(response.getStatusLine().getStatusCode());
        }
        catch (IOException ioExp){}
    }

    @Test
    void putCollectionEntryIdReject() {
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPut httpPost = new HttpPut("http://127.0.0.1:8080/webapi/collectionEntry/4/reject");
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");
            CloseableHttpResponse response = client.execute(httpPost);
            System.out.println(response.getStatusLine());
            System.out.println(response.getStatusLine().getStatusCode());
        }
        catch (IOException ioExp){}
    }

    @Test
    void putAssignGenreToCollectionEntry() {
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            //HttpPut httpPost = new HttpPut("http://127.0.0.1:8080/webapi/collectionEntry/4/assignGenre/Black%20and%20white");
            HttpPut httpPost = new HttpPut("http://127.0.0.1:8080/webapi/collectionEntry/4/assignGenre/Comedy");
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");
            CloseableHttpResponse response = client.execute(httpPost);
            System.out.println(response.getStatusLine());
            System.out.println(response.getStatusLine().getStatusCode());
            System.out.println(response.getStatusLine().getReasonPhrase());
        }
        catch (IOException ioExp){}
    }

    @Test
    void putUnAssignGenreToCollectionEntry() {
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            //HttpPut httpPost = new HttpPut("http://127.0.0.1:8080/webapi/collectionEntry/4/assignGenre/Black%20and%20white");
            HttpPut httpPost = new HttpPut("http://127.0.0.1:8080/webapi/collectionEntry/4/unassignGenre/Comedy");
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");
            CloseableHttpResponse response = client.execute(httpPost);
            System.out.println(response.getStatusLine());
            System.out.println(response.getStatusLine().getStatusCode());
            System.out.println(response.getStatusLine().getReasonPhrase());
        }
        catch (IOException ioExp){}
    }*/
}