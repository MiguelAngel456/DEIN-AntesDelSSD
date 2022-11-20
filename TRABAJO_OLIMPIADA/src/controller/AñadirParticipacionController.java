package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.Deporte;
import model.Deportista;
import model.Equipos;
import model.Evento;
import model.Participacion;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import dao.DeportistaDao;
import dao.EquipoDao;
import dao.EventoDao;
import dao.ParticipacionDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.control.ComboBox;

import javafx.scene.control.RadioButton;

public class AñadirParticipacionController implements Initializable{
	@FXML
	private Label lblNumero2;
	@FXML
	private Label lblNumero6;
	@FXML
	private Label lblTitulo;
	@FXML
	private Button btnAceptar;
	@FXML
	private Button btnCancelar;
	@FXML
	private ComboBox comboDeportista;
	@FXML
	private ComboBox comboEvento;
	@FXML
	private RadioButton rbOro;
	@FXML
	private ToggleGroup medalla;
	@FXML
	private RadioButton rbPlata;
	@FXML
	private RadioButton rbBronze;
	@FXML
	private RadioButton rbNada;
	@FXML
	private ComboBox comboEquipo;
	@FXML
	private TextField txtEdad;
	private ParticipacionDao pd;
	private EventoDao evd;
	private EquipoDao eqd;
	private DeportistaDao dd;
	
	private int antiguoIdEvento,antiguoIdDeportista,antiguoIdEquipo;

	

	// Event Listener on Button[#btnAceptar].onAction
	@FXML
	public void aceptar(ActionEvent event) {

		String med;
		//Saber que medalla a conseguido
		if(rbOro.isSelected()) {
			med=rbOro.getText();
		}else {
			if(rbPlata.isSelected()) {
				med=rbPlata.getText();
			}else {
				if(rbBronze.isSelected()) {
					med=rbBronze.getText();
				}else {
					med="";
				}
			}
		}
		//Buscar el id deportista
		Deportista dep=(Deportista) comboDeportista.getSelectionModel().getSelectedItem();
		int id_deportista=dep.getId_deportista();
		//Buscar el id Equipo
		Equipos equip=(Equipos) comboEquipo.getSelectionModel().getSelectedItem();
		int id_equipo=equip.getId_equipo();
		//Buscar el id Evento
		Evento ev=(Evento) comboEvento.getSelectionModel().getSelectedItem();
		int id_evento=ev.getId_evento();
		if (lblTitulo.getText().equals("Añadir Participacion")) {
			if(comprobar().length()==0){
				int edad=Integer.parseInt(txtEdad.getText());
				Participacion p=new Participacion(dep, edad, med, equip, ev);
				info();
				try {
					pd.anadirParticipacion(p);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					error("Error en el sql");
				}
				
				Stage stage = (Stage) btnAceptar.getScene().getWindow();
				stage.close();
			}else {
				error();
			}
			
		}else {
			if(comprobar().length()==0){
				int edad=Integer.parseInt(txtEdad.getText());
				Participacion p=new Participacion(dep, edad, med, equip, ev);
				try {
					pd.modificarParticipacion(p, this.antiguoIdEvento, this.antiguoIdDeportista);
					info();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					error("Error en el sql");
				}
				
				Stage stage = (Stage) btnAceptar.getScene().getWindow();
				stage.close();
			}else{
				error();
			}
				
			
		}
		

		
	}
	// Event Listener on Button[#btnCancelar].onAction
	@FXML
	public void cerrar(ActionEvent event) {

		// TODO Autogenerated
		Stage stage = (Stage) btnCancelar.getScene().getWindow();
		stage.close();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		ObservableList<Deportista> listDepo=FXCollections.observableArrayList();
		pd=new ParticipacionDao();
		evd=new EventoDao();
		eqd=new EquipoDao();
		dd=new DeportistaDao();
		try {
			listDepo=dd.sacarDeportistas();
			comboEvento.setItems(evd.cargarEvento());
			comboEquipo.setItems(eqd.sacarEquipos());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			error("Error en el sql");
		}

		comboDeportista.setItems(listDepo);

		lblTitulo.setText("Añadir Participacion");

		comboDeportista.getSelectionModel().select(0);
		comboEvento.getSelectionModel().select(0);
		comboEquipo.getSelectionModel().select(0);
		rbNada.setSelected(true);

	}
	

	public String comprobar() {

		String fallo="";
		if(txtEdad.getText().length()==0) {
			fallo+="\n El campo de la edad tiene que tener contenido";
		}
		//EL LIMITE ES 3 PORQUE NO HAY DEPORTISTAS DE MILES DE AÑOS
		if(txtEdad.getText().length()>=4) {
			fallo+="\n El campo de la edad no puede contener numeros de mas de 3 digitos";
		}
		int edad=0;
		try {
			edad=Integer.parseInt(txtEdad.getText());
		} catch (NumberFormatException e) {
			// TODO: handle exception
			fallo+="\n El campo de la edad tiene que tener numeros";
		} 
		Deportista d=(Deportista) this.comboDeportista.getSelectionModel().getSelectedItem();
		Evento ev=(Evento) this.comboEvento.getSelectionModel().getSelectedItem();
		Equipos eq=(Equipos) this.comboEquipo.getSelectionModel().getSelectedItem();
		String medalla;
		if(rbOro.isSelected()) {
			medalla="oro";
		}else {
			if(rbPlata.isSelected()) {
				medalla="Plata";
			}else {
				if(rbBronze.isSelected()) {
					medalla="Bronze";
				}else {
					medalla="nada";
				}
			}
		}
		if(lblTitulo.getText().equals("Añadir Participacion")) {
			Participacion p=new Participacion(d, edad, medalla, eq, ev);
			try {
				if(pd.cargarParticipacion().contains(p)) {
					fallo+="\n Ya existe esta Participacion";
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
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
	public void info() {

		Alert alert;
		alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setContentText("ACCION HECHA CORRECTAMENTE");
		alert.setHeaderText(null);
		alert.setTitle("INFO");
		alert.showAndWait();
	}
	public void rellenar(Participacion p) {
		//TITULO
		lblTitulo.setText("Modificar Participacion");
		
		//COMBO-BOX
		comboDeportista.getSelectionModel().select(p.getDep());

		comboEquipo.getSelectionModel().select(p.getEq());

		comboEvento.getSelectionModel().select(p.getEv());
		
		comboDeportista.setDisable(true);
		comboEvento.setDisable(true);
		
		//textField
		txtEdad.setText(String.valueOf(p.getEdad()));
		
		//radio Button
		if(p.getMedalla().toLowerCase().equals("oro")) {
			rbOro.setSelected(true);
		}else {
			if(p.getMedalla().toLowerCase().equals("plata")) {
				rbPlata.setSelected(true);
			}else {
				if(p.getMedalla().toLowerCase().equals("bronze")) {
					rbBronze.setSelected(true);
				}else {
					rbNada.setSelected(true);
				}
			}
		}
		//IDS ANTUGUOS,
		this.antiguoIdDeportista=p.getDep().getId_deportista();
		
		this.antiguoIdEquipo=p.getEq().getId_equipo();
		
		this.antiguoIdEvento=p.getEv().getId_evento();
	}
}
