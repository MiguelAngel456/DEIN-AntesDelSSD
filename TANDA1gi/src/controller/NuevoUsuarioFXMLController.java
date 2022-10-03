package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.Persona;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class NuevoUsuarioFXMLController {
	@FXML
	private TextField txtNombre;
	@FXML
	private TextField txtApellido;
	@FXML
	private TextField txtEdad;
	@FXML
	private Button btnGuardar;
	@FXML
	private Button btnCancelar;
	
	private Persona p;
	
	private ObservableList<Persona> lista;

	// Event Listener on Button[#btnGuardar].onAction
	@FXML
	public void anadirPersona(ActionEvent event) {
		p= new Persona(txtNombre.getText(), txtApellido.getText(), txtEdad.getText());
		Stage stage = (Stage) btnGuardar.getScene().getWindow();
		if(this.comprobar().length()==0) {
			this.AniadirAlert(stage);
			stage.close();
			
		}else {
			this.AniadirAlert(stage);
		}

	}
	private void AniadirAlert(Window win) {
		Alert alert;
		String texto=comprobar();
		if(texto.length()==0) {
			alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setContentText("Modificado correctamente");
		}else {
			
			alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText(texto);
		}
		alert.setHeaderText(null);
		alert.initOwner(win);
		alert.setTitle("TUS DATOS");
		alert.showAndWait();
	}
	public String comprobar() {
	   
		String mal="";
		boolean fallos=false;
		//NOMBRE
		if (txtNombre.getText().length()==0) {
			mal+="El campo nombre es obligatorio";
		
		}
		//APELLIDO
		if (txtApellido.getText().length()==0) {
			mal+="\n El campo apellido es obligatorio";
		//	fallos++;
		}
		if (txtEdad.getText().length()==0) {
			mal+="\n El campo edad es obligatorio rellenado";
		//	fallos++;
		}
		try {
			int num=Integer.parseInt(txtEdad.getText());
		} catch (NumberFormatException e) {
			if (txtEdad.getText().length()!=0) {
				mal+="\n El campo edad tiene que ser numeros";
			}
		}
		
		if(lista.contains(p)) {
			mal+="\n ya existe esta persona en la tabla";
		}
		

		return mal;

	}
	public TextField getTxtNombre() {
		return txtNombre;
	}

	public TextField getTxtApellido() {
		return txtApellido;
	}

	public TextField getTxtEdad() {
		return txtEdad;
	}

	public Persona getP() {
		return p;
	}
	
	public void cargarLista(ObservableList lista) {
		this.lista=lista;
	}
}
