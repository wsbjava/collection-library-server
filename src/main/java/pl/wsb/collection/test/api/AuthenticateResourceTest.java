package pl.wsb.collection.test.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
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

import java.io.IOException;

class AuthenticateResourceTest {

    @Test
    void postLogOn() {
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
            AuthenticationResponse authResponse = new ObjectMapper().readValue(response.getEntity().getContent(), AuthenticationResponse.class);
            System.out.println(authResponse.getAccessToken());
            System.out.println(authResponse.getEmailAddress());
            System.out.println(authResponse.getExpiresIn());
            System.out.println(authResponse.getUserId());

        }
        catch (IOException ioExp){}
    }


}