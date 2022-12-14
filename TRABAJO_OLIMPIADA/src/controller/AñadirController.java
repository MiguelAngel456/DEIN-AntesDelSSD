package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.Deporte;
import model.Evento;
import model.Olimpiada;
import model.Participacion;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import conexionBD.ConexionDB;
import dao.DeporteDao;
import dao.EventoDao;
import dao.OlimpiadaDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

public class AñadirController implements Initializable {

	@FXML
	private Button btnAceptar;

	@FXML
	private Button btnCancelar;

	@FXML
	private ComboBox<Deporte> comboDeporte;

	@FXML
	private ComboBox<Olimpiada> comboOlimpiada;

	@FXML
	private Label lblNumero1;

	@FXML
	private Label lblNumero2;

	@FXML
	private Label lblNumero6;

	@FXML
	private Label lblTitulo;

	@FXML
	private TextField txtNumero1; //NOMBRE EVENTO

	private DeporteDao dp;
	private EventoDao ed;
	private OlimpiadaDao od;
	
	private int antiguoIdDeporte;
	
	private int antiguoIdOlimpiada;
	
	private int antiguoIdEvento;
	
	private String nomAntiguo;
	// Event Listener on Button[#btnAceptar].onAction


	@FXML
	public void aceptar(ActionEvent event) {
		// TODO Autogenerated.
		// CREAR LOS ELEMENTOS

		
		
		
		if (lblTitulo.getText().equals("Añadir Evento Olimpico")) {
			try {
				int id=(ed.ultimoId()+1);
				Deporte dep = comboDeporte.getSelectionModel().getSelectedItem();
				Olimpiada ol = comboOlimpiada.getSelectionModel().getSelectedItem();
				
				Evento ev = new Evento(id,txtNumero1.getText(), ol,dep);
				// COMPROBAR SI YA EXISTE
				if (!ed.cargarEvento().equals(ev)) {
					
					if(comprobar().length()==0) {
						// AÑADIR EVENTO
						ed.anadirEvento(ev);
						// PARA QUE SE CIERRE AL DARLE ACEPTAR
						info();
						Stage stage = (Stage) btnAceptar.getScene().getWindow();
						stage.close();
						
					}else {
						error();
					}

				}
			}catch (SQLException e) {
				// TODO: handle exception
				error("Error en el sql");
			}

		} else {
			try {
				Deporte dep = comboDeporte.getSelectionModel().getSelectedItem();
				Olimpiada ol = comboOlimpiada.getSelectionModel().getSelectedItem();
				
				Evento ev = new Evento(antiguoIdEvento,txtNumero1.getText(), ol,dep);
				if (!ed.cargarEvento().equals(ev)) {

				
					if(comprobar().length()==0) {
						
						// MODIFICAR EVENTO
						ed.modificarEvento(ev);
						// PARA QUE SE CIERRE AL DARLE ACEPTAR
						info();
						Stage stage = (Stage) btnAceptar.getScene().getWindow();
						stage.close();
					}else {
						this.error();
					}
					
				}
			}catch (SQLException e) {
				// TODO: handle exception
				error("Error en el sql");
			}
			
			
		}

	}
	public void info() {
		Alert alert;
		alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setContentText("ACCION HECHA CORRECTAMENTE");
		alert.setHeaderText(null);
		alert.setTitle("INFO");
		alert.showAndWait();
	}
	// Event Listener on Button[#btnCancelar].onAction
	@FXML
	public void cerrar(ActionEvent event) {
		// TODO Autogenerated
		// PARA QUE SE CIERRE AL DARLE CANCELAR
		Stage stage = (Stage) btnCancelar.getScene().getWindow();
		stage.close();

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		// DECLARAR LOS DAO
		dp = new DeporteDao();
		ed = new EventoDao();
		od = new OlimpiadaDao();
		try {
			this.comboDeporte.setItems(dp.sacarDeportes());
			this.comboOlimpiada.setItems(od.sacarOlimpiada());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			this.error("Error en el sql");
		}
		
		if(lblTitulo.getText().equals("Añadir Evento Olimpico")){
			comboDeporte.getSelectionModel().select(0);
			comboOlimpiada.getSelectionModel().select(0);
		}
		

	}


	public String comprobar() {
		String fallo="";
		if(txtNumero1.getText().length()==0) {
			fallo+="\n El campo del nombre Evento tiene que tener contenido";
		}
		if(txtNumero1.getText().length()>150) {
			fallo+="\n El campo del nombre Evento no puede tener mas de 150 Caracteres";
		}
		
		Olimpiada ol=comboOlimpiada.getSelectionModel().getSelectedItem();
		Deporte dep=comboDeporte.getSelectionModel().getSelectedItem();
		if (lblTitulo.getText().equals("Añadir Evento Olimpico")) {
			try {
				int id=ed.ultimoId()+1;
				
				Evento ev = new Evento(id,txtNumero1.getText(), ol,dep);
				
				if(ed.cargarEvento().contains(ev)) {
					
					fallo+="\n Ese Evento ya existe";
				}
			} catch (SQLException e) {
				// TODO: handle exception
				error("Error en el sql");
			}
		}
		return fallo;
	}
	public void error () {
		Alert alert;
		String texto=comprobar();
		alert = new Alert(Alert.AlertType.ERROR);
		alert.setContentText(texto);
		alert.setHeaderText(null);
		alert.setTitle("ERROR");
		alert.showAndWait();
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
	public void rellenar(Evento ev) {
		//TITULO
		lblTitulo.setText("Modificar Evento Olimpico");
		
		//COMBO-BOX
		comboDeporte.getSelectionModel().select(ev.getDep());
		comboOlimpiada.getSelectionModel().select(ev.getOl());
		
		txtNumero1.setDisable(true);
		comboOlimpiada.setDisable(true);
		
		
		//textField
		txtNumero1.setText(ev.getNom_Evento());
		
		//IDS ANTIGUOS,
		this.antiguoIdDeporte=ev.getDep().getId();
		
		this.antiguoIdOlimpiada=ev.getOl().getId();
		
		this.antiguoIdEvento=ev.getId_evento();
	}
}
