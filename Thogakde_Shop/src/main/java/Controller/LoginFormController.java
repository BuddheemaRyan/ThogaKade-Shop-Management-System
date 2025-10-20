package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {

    @FXML
    public PasswordField txtPassword;

    @FXML
    public TextField txtUserName;

    @FXML
    void btnLogin(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        String username =txtUserName.getText();
        String password = txtPassword.getText();

        System.out.println("Username :"+username);
        System.out.println("Password :"+password);

       if("Ryan".equals(username)&& "1234".equals(password)){
           System.out.println("Valid User");
           try {
               stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/DashboardForm.fxml"))));
           } catch (IOException e) {
               throw new RuntimeException(e);
           }
           stage.setTitle("Dashboard");
           stage.show();
       }else{
           System.out.println("invalid User");

           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("Error");
           alert.setHeaderText("Invalid User");
           alert.setContentText("Please enter valid user name and password");
           alert.showAndWait();

           txtUserName.setText("");
           txtPassword.setText("");
       }

    }

}
