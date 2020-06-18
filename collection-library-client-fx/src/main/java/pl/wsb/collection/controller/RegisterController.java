package pl.wsb.collection.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import pl.wsb.collection.exceptions.ValidationException;
import pl.wsb.collection.model.RegisterUserRequest;

import java.io.IOException;

public class RegisterController {

    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfSurname;
    @FXML
    private TextField tfAge;
    @FXML
    private PasswordField pfPassword;
    @FXML
    private PasswordField pfPasswordCont;
    @FXML
    private Button btnRegister;

    @FXML
    private void initialize() {
        if (this.btnRegister != null) {
            this.btnRegister.setOnAction(this::handleRegisterClick);
        }
    }

    private void handleRegisterClick(ActionEvent actionEvent) {
        try {
            if (this.pfPassword == null) {
                return;
            } //if
            if (this.tfEmail == null) {
                return;
            } //if
            if (this.pfPasswordCont == null) {
                return;
            } //if
            if (this.tfName == null) {
                return;
            } //if
            if (this.tfSurname == null) {
                return;
            } //if
            if (this.tfAge == null) {
                return;
            } //if
            if (this.handleAuthCall(this.tfEmail.getText(),
                    this.pfPassword.getText())) {
                createAlert(
                        "Logowanie - sukces",
                        "Sukces",
                        "Logowanie powiodło się.",
                        Alert.AlertType.INFORMATION
                ).showAndWait();
            } else { //if
                createAlert(
                        "Logowanie - błąd",
                        "Ostrzeżenie",
                        "Dane logowania nie wyglądają na poprawne.",
                        Alert.AlertType.WARNING
                ).showAndWait();
            } //else
        } catch (ValidationException ex) {
            createAlert(
                    "Logowanie - ostrzeżenie",
                    "Ostrzeżenie",
                    "Proszę uzupełnić adres email i hasło.",
                    Alert.AlertType.WARNING
            ).showAndWait();
        } catch (Exception ex) {
            createAlert(
                    "Logowanie - błąd",
                    "Błąd",
                    "Przepraszamy, wystąpił błąd aplikacji.",
                    Alert.AlertType.ERROR
            ).showAndWait();
        }
    }

    private Alert createAlert(String title, String header, String content, Alert.AlertType alertType) {
        final Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.getButtonTypes().clear();
        alert.getButtonTypes().addAll(ButtonType.OK);
        return alert;

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
                        new RegisterUserRequest().email(email).password(password)
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
