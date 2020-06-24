package pl.wsb.collection.test.api;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class UserResourceTest {

    @Test
    public void setUserRoleTest(){


        try {
            CloseableHttpClient client = HttpClients.createDefault();
            //HttpPut httpPut = new HttpPut("http://127.0.0.1:8080/webapi/user/1/setRole/ADMIN");
            HttpPut httpPut = new HttpPut("http://127.0.0.1:8080/webapi/user/1/setRole/USER");
            httpPut.setHeader("Accept", "application/json");
            httpPut.setHeader("Content-type", "application/json");
            CloseableHttpResponse response = client.execute(httpPut);
            System.out.println(response.getStatusLine());
            System.out.println(response.getStatusLine().getStatusCode());
        }
        catch (IOException ioExp){}

    }

}