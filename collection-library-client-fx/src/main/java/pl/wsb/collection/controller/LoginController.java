package pl.wsb.collection.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import pl.wsb.collection.exceptions.ValidationException;
import pl.wsb.collection.model.AuthenticationRequest;

import java.io.IOException;
import java.util.Objects;

public class LoginController {

    Stage stage;
    Parent root;

    @FXML
    private TextField tfEmailAddress;
    @FXML
    private PasswordField pfPassword;
    @FXML
    private Button btnLogin;

    @FXML
    private Button btnRegister;


    @FXML
    private void initialize() {
        if (this.btnRegister != null) {
            this.btnRegister.setOnAction(this::handleRegisterClick);
        }
    }

    private void handleRegisterClick(ActionEvent actionEvent) {
        stage = (Stage) btnRegister.getScene().getWindow();
        FXMLLoader loader;
        loader = new FXMLLoader(getClass().getClassLoader().getResource("view/register.fxml"));
        AnchorPane rootLayout = null;
        try {
            rootLayout = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(Objects.requireNonNull(rootLayout));
        stage.setScene(scene);
        stage.show();

    }


    private boolean handleAuthCall(String email, String password) throws
            ValidationException, IOException {
        if (StringUtils.isBlank(email)) {
            throw new ValidationException();
        } //if
        if (StringUtils.isBlank(password)) {
            throw new ValidationException();
        } //if
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://127.0.0.1:8080/api/v1/authenticate");
        ObjectMapper objectMapper = new ObjectMapper();
        StringEntity requestEntity = new StringEntity(
                objectMapper.writeValueAsString(
                        new AuthenticationRequest().eMail(email).password(password)
                ),
                ContentType.APPLICATION_JSON
        );
        httpPost.setEntity(requestEntity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");
        CloseableHttpResponse response = client.execute(httpPost);
        if (response == null) {
            return false;
        } //if
        if (response.getStatusLine() == null) {
            return false;
        } //if
        return (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK);
    }


}
