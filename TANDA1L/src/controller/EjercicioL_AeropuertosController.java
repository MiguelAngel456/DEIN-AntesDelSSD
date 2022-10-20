package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import dao.AeropuertoDao;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Aeropuerto;

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
	    private TableColumn<?, ?> colAnio;

	    @FXML
	    private RadioButton rbPrivados;

	    @FXML
	    private RadioButton rbPublicos;

	    @FXML
	    private TableView<?> tablaAeropuertos;

	    @FXML
	    private TextField txtNombre;
	    
	    private ObservableList<Aeropuerto> listaAeropuerto;
	    private AeropuertoDao ad;

	    
	    @FXML
	    void AniadirAeropurto(ActionEvent event) {

	    }
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
		
		
		try {
			ad=new AeropuertoDao();
			this.listaAeropuerto=ad.cargarAeroPuerto("privado");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		rbPrivados.setSelected(true);
		colFinanciacion.setVisible(false);
		numTrabajadores.setVisible(false);
		//ASIGNAR LAS COLUMNAS A LOS CAMPOS DE OBJETO CORRESPONDIENTE(privado)
		idAeropuerto.setCellValueFactory(new PropertyValueFactory<>("id"));
		nomAeropuerto.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		paisAeropuerto.setCellValueFactory(new PropertyValueFactory<>("pais"));
		ciudadAeropuerto.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
		calleAeropuerto.setCellValueFactory(new PropertyValueFactory<>("calle"));
		numeroDIreccion.setCellValueFactory(new PropertyValueFactory<>("numero"));
		colAnio.setCellValueFactory(new PropertyValueFactory<>("año"));
		capaciodad.setCellValueFactory(new PropertyValueFactory<>("capacidad"));
		//Privado
		Nsocios.setCellValueFactory(new PropertyValueFactory<>("Nsocios"));
		//Publico
		colFinanciacion.setCellValueFactory(new PropertyValueFactory<>("financiacion"));
		numTrabajadores.setCellValueFactory(new PropertyValueFactory<>("trabajadores"));
		
		
		
		
		
		
	}

}
