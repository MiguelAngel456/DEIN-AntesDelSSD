package controller;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import dao.EventoDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Evento;

public class Principal_controles implements Initializable{

    @FXML
    private Button btnAnadir;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnModificar;

    @FXML
    private ComboBox<?> cbTabla;

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
    private TableColumn<?, ?> col7Posicion;

    @FXML
    private TableColumn<?, ?> col8Posicion;

    @FXML
    private TableView<Evento> tabla;
    @FXML
    private ToggleGroup groupTemporada;

    @FXML
    private RadioButton rbInvierno;

    @FXML
    private RadioButton rbVerano;

    @FXML
    private TextField txtFIltro;
    
    private ObservableList<Evento> listEventos;
  //  private ObservableList<Evento> listOlimpiadas;
    
    private EventoDao ed;
    
    @FXML
    void anadir(ActionEvent event) {

    }

    @FXML
    void eliminar(ActionEvent event) {

    }

    @FXML
    void modificar(ActionEvent event) {

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<String> arrTablas= FXCollections.observableArrayList();
		arrTablas.add("Evento");
		arrTablas.add("Participacion");
		cbTabla=new ComboBox<>(arrTablas);
		

		
		//ASIGNAR CAMPOS A LAS TABLAS y  los tamaños
		col1Posicion.setCellValueFactory(new PropertyValueFactory<>("nom_Evento"));
		col1Posicion.prefWidthProperty().bind(tabla.widthProperty().multiply(0.15));
		
		col2Posicion.setCellValueFactory(new PropertyValueFactory<>("nom_Olimpiada"));
		col2Posicion.prefWidthProperty().bind(tabla.widthProperty().multiply(0.20));
		
		col3Posicion.setCellValueFactory(new PropertyValueFactory<>("anio_Olimpiada"));
		col3Posicion.prefWidthProperty().bind(tabla.widthProperty().multiply(0.08));
		
		col4Posicion.setCellValueFactory(new PropertyValueFactory<>("temporada_Olimpiada"));
		col4Posicion.prefWidthProperty().bind(tabla.widthProperty().multiply(0.14));
		
		col5Posicion.setCellValueFactory(new PropertyValueFactory<>("ciudad_Olimpiada"));
		col5Posicion.prefWidthProperty().bind(tabla.widthProperty().multiply(0.09));
		
		col6Posicion.setCellValueFactory(new PropertyValueFactory<>("nom_Deporte"));
		col6Posicion.prefWidthProperty().bind(tabla.widthProperty().multiply(0.17));
		
		//ASIGNAR A LA TABLA UNA LISTA
		ed=new EventoDao();
		try {
			listEventos=ed.cargarEvento("evento");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tabla.setItems(listEventos);
		
		
	}

}

