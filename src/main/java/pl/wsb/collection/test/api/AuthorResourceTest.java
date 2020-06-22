package pl.wsb.collection.test.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.Test;
import pl.wsb.collection.exceptions.ValidationException;
import pl.wsb.collection.model.AuthorRequest;
import pl.wsb.collection.model.RegisterUserRequest;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class AuthorResourceTest {

    @Test
    void postAuthor() {

        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost("http://127.0.0.1:8080/webapi/author");
            System.out.println("Test");
            ObjectMapper objectMapper = new ObjectMapper();
            StringEntity requestEntity = new StringEntity(
                    objectMapper.writeValueAsString(
                            new AuthorRequest().firstName("Adam").lastName("Konieczny")
                    ),
                    ContentType.APPLICATION_JSON
            );
            httpPost.setEntity(requestEntity);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");
            CloseableHttpResponse response = client.execute(httpPost);
        }
        catch (IOException ioExp){}

    }
}