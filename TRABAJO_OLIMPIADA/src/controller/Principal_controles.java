package controller;
import java.io.IOException;
import java.net.URL;

import java.sql.SQLException;
import java.util.ResourceBundle;

import dao.EventoDao;
import dao.ParticipacionDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Evento;
import model.Participacion;

public class Principal_controles implements Initializable{
	 @FXML
	    private Button btnAnadir;

	    @FXML
	    private Button btnEliminar;

	    @FXML
	    private Button btnModificar;

	    @FXML
	    private ComboBox<String> cbTabla;

	    @FXML
	    private CheckBox checkBoxInvierno;

	    @FXML
	    private CheckBox checkBoxVerano;

	    @FXML
	    private TableColumn<?, ?> col1Posicion;

	    @FXML
	    private TableColumn<?, ?> col2Posicion;

	    @FXML
	    private TableColumn<?, ?> col3Posicion;

	    @FXML
	    private TableColumn<?, ?> col4Posicion;

	    @FXML
	    private TableColumn<?, ?> col5Posicion;

	    @FXML
	    private TableColumn<?, ?> col6Posicion;

	    @FXML
	    private TableColumn<?, ?> colAltura;

	    @FXML
	    private TableColumn<?, ?> colEdad;

	    @FXML
	    private TableColumn<?, ?> colEquipo;

	    @FXML
	    private TableColumn<?, ?> colEvento;

	    @FXML
	    private TableColumn<?, ?> colMedalla;

	    @FXML
	    private TableColumn<?, ?> colNomDeportista;

	    @FXML
	    private TableColumn<?, ?> colPeso;

	    @FXML
	    private TableColumn<?, ?> colSexo;

	    @FXML
	    private TableView<Evento> tablaEvento;

	    @FXML
	    private TableView<Participacion> tablaParticipacion;

	    @FXML
	    private TextField txtFIltro;
	    @FXML
	    private MenuItem crearDeporte;

	    @FXML
	    private MenuItem crearDeportista;

	    @FXML
	    private MenuItem crearEquipo;

	    @FXML
	    private MenuItem crearOlimpiada;
    private ObservableList<Evento> listEventos;
    private ObservableList<Participacion> listParticipacion;
    
    private EventoDao ed;
    private ParticipacionDao pd;
    
    @FXML
    void anadir(ActionEvent event) {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AñadirEvento.fxml"));
        Parent root;
		try {
			root = loader.load();
			 Scene newScene = new Scene(root);
		        Stage newStage = new Stage();
		        AñadirController control= loader.getController();
		        
		        newStage.initModality(Modality.APPLICATION_MODAL);
		
		        newStage.setScene(newScene);
	        	newStage.setTitle("Añadir Evento");
		        newStage.showAndWait ();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    @FXML
    void anadirDeporte(ActionEvent event) {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AñadirDeporte.fxml"));
        Parent root;
		try {
			root = loader.load();
			Scene newScene = new Scene(root);
		    Stage newStage = new Stage();
		    AñadirDeporteController control= loader.getController();
		    
		    newStage.initModality(Modality.APPLICATION_MODAL);
			newStage.setScene(newScene);
	        newStage.setTitle("Añadir Deporte");
		    newStage.showAndWait ();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void anadirDeportista(ActionEvent event) {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AñadirDeportista.fxml"));
        Parent root;
		try {
			root = loader.load();
			Scene newScene = new Scene(root);
		    Stage newStage = new Stage();
		    AñadirDeportistaController control= loader.getController();
		    
		    newStage.initModality(Modality.APPLICATION_MODAL);
			newStage.setScene(newScene);
	        newStage.setTitle("Añadir Deportista");
		    newStage.showAndWait ();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }


    @FXML
    void anadirEquipo(ActionEvent event) {

    }

    @FXML
    void anadirOlimpiada(ActionEvent event) {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AñadirOlimpiada.fxml"));
        Parent root;
		try {
			root = loader.load();
			Scene newScene = new Scene(root);
		    Stage newStage = new Stage();
		    AñadirOlimpiadaController control= loader.getController();
		    
		    newStage.initModality(Modality.APPLICATION_MODAL);
			newStage.setScene(newScene);
	        newStage.setTitle("Añadir Olimpiada");
		    newStage.showAndWait ();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void eliminar(ActionEvent event) {

    }

    @FXML
    void modificar(ActionEvent event) {

    }

    @FXML
    void seleccionar(ActionEvent event) {
    	if(cbTabla.getSelectionModel().getSelectedItem().toString().equals("Participacion")) {
    		ParticipacionDao pd=new ParticipacionDao();
    		listParticipacion=pd.cargarParticipacion();
    		tablaParticipacion.setItems(listParticipacion);
    		tablaParticipacion.setVisible(true);
    		tablaEvento.setVisible(false);
    	}else {
    		ed=new EventoDao();
    		listEventos=ed.cargarEvento();
    		tablaEvento.setItems(listEventos);
    		tablaParticipacion.setVisible(false);
    		tablaEvento.setVisible(true);
    	}
    }
    //METODOS DE FILTRAR
    @FXML
    void filtrarTemporada(ActionEvent event) {
    	listEventos=ed.cargarEvento();
    	
    	if(cbTabla.getSelectionModel().getSelectedItem().toString().equals("Participacion")) {
    		
    		listParticipacion=pd.FiltrarParticipacion(checkBoxInvierno.isSelected(), checkBoxVerano.isSelected());
    		tablaParticipacion.setItems(listParticipacion);
    		
    	}else {
    		ObservableList<Evento> listTemporada= FXCollections.observableArrayList();
			for (int i = 0; i < listEventos.size(); i++) {
				if(checkBoxInvierno.isSelected()) {
					if(listEventos.get(i).getTemporada_Olimpiada().equals("Winter")) {
						listTemporada.add(listEventos.get(i));
					}
				}
				if(checkBoxVerano.isSelected()) {
					if(listEventos.get(i).getTemporada_Olimpiada().equals("Summer")) {
						listTemporada.add(listEventos.get(i));
					}
				}
			}
			listEventos=listTemporada;
			tablaEvento.setItems(listEventos);
    	}
    	
    	
    }
    @FXML
    void filtrarNombre(ActionEvent event) {
		String nom="";
		if(cbTabla.getSelectionModel().getSelectedItem().toString().equals("Participacion")) {
			ObservableList<Participacion> listaFiltrada= FXCollections.observableArrayList();
			nom=txtFIltro.getText();
			for (int i = 0; i < listParticipacion.size(); i++) {
				if(listParticipacion.get(i).getNomDeportista().contains(nom)) {
					listaFiltrada.add(listParticipacion.get(i));
					
				}
			}
			tablaParticipacion.refresh();
			tablaParticipacion.setItems(listaFiltrada);
		}else {
			ObservableList<Evento> listaFiltrada= FXCollections.observableArrayList();
			nom=txtFIltro.getText();
			for (int i = 0; i < listEventos.size(); i++) {
				if(listEventos.get(i).getNom_Evento().contains(nom)) {
					listaFiltrada.add(listEventos.get(i));
				}
			}
			tablaEvento.refresh();
			tablaEvento.setItems(listaFiltrada);
		}
		
		if(nom.isEmpty()){
			tablaParticipacion.setItems(listParticipacion);
		}
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<String> arrTablas= FXCollections.observableArrayList();
		arrTablas.add("Evento");
		arrTablas.add("Participacion");
		cbTabla.setItems(arrTablas);
		cbTabla.getSelectionModel().select(0);
		tablaEvento.setVisible(true);
		//tablaParticipacion.setVisible(false);
		

		
		//ASIGNAR CAMPOS A LA TABLA EVENTO y  los tamaños
		col1Posicion.setCellValueFactory(new PropertyValueFactory<>("nom_Evento"));
		col1Posicion.prefWidthProperty().bind(tablaEvento.widthProperty().multiply(0.30));
		
		col2Posicion.setCellValueFactory(new PropertyValueFactory<>("nom_Olimpiada"));
		col2Posicion.prefWidthProperty().bind(tablaEvento.widthProperty().multiply(0.20));
		
		col3Posicion.setCellValueFactory(new PropertyValueFactory<>("anio_Olimpiada"));
		col3Posicion.prefWidthProperty().bind(tablaEvento.widthProperty().multiply(0.08));
		
		col4Posicion.setCellValueFactory(new PropertyValueFactory<>("temporada_Olimpiada"));
		col4Posicion.prefWidthProperty().bind(tablaEvento.widthProperty().multiply(0.14));
		
		col5Posicion.setCellValueFactory(new PropertyValueFactory<>("ciudad_Olimpiada"));
		col5Posicion.prefWidthProperty().bind(tablaEvento.widthProperty().multiply(0.09));
		
		col6Posicion.setCellValueFactory(new PropertyValueFactory<>("nom_Deporte"));
		col6Posicion.prefWidthProperty().bind(tablaEvento.widthProperty().multiply(0.17));
		
		//ASIGNAR CAMPOS A LA TABLA Participacion y  los tamaños
		colNomDeportista.setCellValueFactory(new PropertyValueFactory<>("nomDeportista"));
		
		colSexo.setCellValueFactory(new PropertyValueFactory<>("sexo"));
		
		colPeso.setCellValueFactory(new PropertyValueFactory<>("peso"));
		
		colAltura.setCellValueFactory(new PropertyValueFactory<>("altura"));
		
		colEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));
		
		colEquipo.setCellValueFactory(new PropertyValueFactory<>("equipo"));
		
		colMedalla.setCellValueFactory(new PropertyValueFactory<>("medalla"));
		
		colEvento.setCellValueFactory(new PropertyValueFactory<>("nomEvento"));
		
		pd=new ParticipacionDao();
		//ASIGNAR A LA TABLA UNA LISTA
		ed=new EventoDao();
		listEventos=ed.cargarEvento();
		tablaEvento.setItems(listEventos);
		
		
	}

}

