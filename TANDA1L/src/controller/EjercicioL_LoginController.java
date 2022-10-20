package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

public class EjercicioL_LoginController implements Initializable{
	@FXML
    private Button btnLogin;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsuario;

    @FXML
    void abrirListado(ActionEvent event) {
    	try{
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/fxml/EjercicioL_Aeropuertos.fxml"));
            Parent root = loader1.load();
            Scene newScene = new Scene(root);
            Stage newStage = new Stage();
            newStage.setScene(newScene);
            newStage.setTitle("AVIONES-AEROPUERTOS");
            newStage.show();
            Stage myStage = (Stage) this.btnLogin.getScene().getWindow();
            myStage.close();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(e.getMessage());
            e.printStackTrace();
            alert.showAndWait();
        }
	
	
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	}

}
