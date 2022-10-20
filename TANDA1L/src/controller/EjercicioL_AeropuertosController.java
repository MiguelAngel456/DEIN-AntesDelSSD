package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class EjercicioL_AeropuertosController implements Initializable{
	 @FXML
	    private TableColumn<?, ?> Nsocios;

	    @FXML
	    private TableColumn<?, ?> calleAeropuerto;

	    @FXML
	    private TableColumn<?, ?> capaciodad;

	    @FXML
	    private TableColumn<?, ?> ciudadAeropuerto;

	    @FXML
	    private TableColumn<?, ?> colFinanciacion;

	    @FXML
	    private ToggleGroup grupoTipo;

	    @FXML
	    private TableColumn<?, ?> idAeropuerto;

	    @FXML
	    private TableColumn<?, ?> nomAeropuerto;

	    @FXML
	    private TableColumn<?, ?> numTrabajadores;

	    @FXML
	    private TableColumn<?, ?> numeroDIreccion;

	    @FXML
	    private TableColumn<?, ?> paisAeropuerto;

	    @FXML
	    private RadioButton rbPrivados;

	    @FXML
	    private RadioButton rbPublicos;

	    @FXML
	    private TableView<?> tablaAeropuertos;

	    @FXML
	    private TextField txtNombre;

    @FXML
    void cambiarApublico(ActionEvent event) {	
    	if(!rbPrivados.isSelected()) {
    		Nsocios.setVisible(false);
    		colFinanciacion.setVisible(true);
    		numTrabajadores.setVisible(true);
    	}
    	
    	else{
    		Nsocios.setVisible(true);
    		colFinanciacion.setVisible(false);
    		numTrabajadores.setVisible(false);
    	}
    	
    	
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		rbPrivados.setSelected(true);
		colFinanciacion.setVisible(false);
		numTrabajadores.setVisible(false);
		
	}

}
