package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.Deporte;
import model.Evento;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import dao.DeporteDao;
import javafx.event.ActionEvent;

public class AñadirDeporteController implements Initializable{
	@FXML
	private Button btnAnadir;
	@FXML
	private Button btnCancelar;
	@FXML
	private TextField txtDeporte;

    @FXML
    private Label lblTitulo;
    
    private int idAnt;

	private DeporteDao dd;
	// Event Listener on Button[#btnAnadir].onAction
	@FXML
	public void aceptar(ActionEvent event) {

		
		if(lblTitulo.getText().equals("Añadir Deporte")) {
			if(comprobar().length()==0) {
				int id;
				try {
					id = dd.ultimoId();
					Deporte dep=new Deporte(id,txtDeporte.getText());
					dd.anadirDeporte(dep);
					info();
					Stage stage = (Stage) btnAnadir.getScene().getWindow();
					stage.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					error("Error en el sql");
				}
				
			}else {
				error("ERROR");
			}
			
			
		}else {
			if(comprobar().length()==0) {
				Deporte dep=new Deporte(idAnt,txtDeporte.getText());
				try {
					
					dd.modificarDeporte(dep);
					info();
					Stage stage = (Stage) btnAnadir.getScene().getWindow();
					stage.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					error("Error en el sql");
				}
			
			}else {
				error("ERROR");
			}
			
		}
	
	}
	// Event Listener on Button[#btnCancelar].onAction
	@FXML
	public void cancelar(ActionEvent event) {
		// TODO Autogenerated
		Stage stage = (Stage) btnCancelar.getScene().getWindow();
		stage.close();
	}

	public String comprobar() {
		String fallo="";
		if(txtDeporte.getText().length()==0) {
			fallo+="\n El campo del nombre Deporte tiene que tener contenido";
		}
		try {
			int id=dd.ultimoId();
			Deporte dep=new Deporte(id,txtDeporte.getText());
			if(dd.sacarDeportes().contains(dep)) {
				fallo+="\n Ese deporte ya existe";
			}
		}catch (SQLException e) {
			
		}
		
		
		
		return fallo;
	}
	public void error (String t) {
		Alert alert;
		String texto=t;
		alert = new Alert(Alert.AlertType.ERROR);
		alert.setContentText(texto);
		alert.setHeaderText(null);
		alert.setTitle("ERROR");
		alert.showAndWait();
	}
	public void info() {
		Alert alert;
		alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setContentText("ACCION HECHA CORRECTAMENTE");
		alert.setHeaderText(null);
		alert.setTitle("INFO");
		alert.showAndWait();
	}
	public void rellenar(Deporte d,String t) {
		lblTitulo.setText("Modificar Deporte");
		
		txtDeporte.setText(d.getDeporte());
		
		idAnt=d.getId();
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		dd=new DeporteDao();
	}
}
