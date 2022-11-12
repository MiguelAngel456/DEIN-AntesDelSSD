package controller;

import java.io.IOException;
import java.net.URL;

import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

import conexionBD.Propiedades;
import dao.DeporteDao;
import dao.DeportistaDao;
import dao.EquipoDao;
import dao.EventoDao;
import dao.OlimpiadaDao;
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
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Deporte;
import model.Deportista;
import model.Equipos;
import model.Evento;
import model.Olimpiada;
import model.Participacion;

public class Principal_controles implements Initializable {
	@FXML
	private Button btnAnadir;
    @FXML
    private GridPane gp;
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


	// MENUITEMS
	@FXML
	private MenuItem crearDeporte;

	@FXML
	private MenuItem crearDeportista;

	@FXML
	private MenuItem crearEquipo;

	@FXML
	private MenuItem crearOlimpiada;

	@FXML
	private MenuItem eliDeporte;

	@FXML
	private MenuItem eliDeportista;

	@FXML
	private MenuItem eliEquipo;

	@FXML
	private MenuItem eliOlimpiada;

	@FXML
	private MenuItem modDeporte;

	@FXML
	private MenuItem modDeportista;

	@FXML
	private MenuItem modEquippo;

	@FXML
	private MenuItem modOlimpiada;

	private ObservableList<Evento> listEventos;
	private ObservableList<Participacion> listParticipacion;

	private EventoDao ed;
	private ParticipacionDao pd;
	private DeportistaDao dd;
	
	private ResourceBundle bundle;

	@FXML
	void anadir(ActionEvent event) {
		if (cbTabla.getSelectionModel().getSelectedItem().toString().equals("Participacion")) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AñadirParticipacion.fxml"));
			Parent root;
			try {
				root = loader.load();
				Scene newScene = new Scene(root);

				Stage newStage = new Stage();
				newScene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
				AñadirParticipacionController control = loader.getController();

				control.getLblTitulo().setText("Añadir Participacion");
				newStage.initModality(Modality.APPLICATION_MODAL);

				newStage.setScene(newScene);
				newStage.setTitle("Participacion");
				newStage.showAndWait();
				listParticipacion = pd.cargarParticipacion();
				tablaParticipacion.setItems(listParticipacion);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AñadirEvento.fxml"));
			Parent root;
			try {
				root = loader.load();
				Scene newScene = new Scene(root);
				Stage newStage = new Stage();
				newScene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
				AñadirController control = loader.getController();

				control.setLblTitulo("Añadir Evento Olimpico");
				newStage.initModality(Modality.APPLICATION_MODAL);

				newStage.setScene(newScene);
				newStage.setTitle("Evento");
				newStage.showAndWait();
				listEventos = ed.cargarEvento();
				tablaEvento.setItems(listEventos);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
			newScene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
			AñadirDeporteController control = loader.getController();

			newStage.initModality(Modality.APPLICATION_MODAL);
			newStage.setScene(newScene);
			newStage.setTitle("Añadir Deporte");
			newStage.showAndWait();
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
			newScene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
			AñadirDeportistaController control = loader.getController();

			newStage.initModality(Modality.APPLICATION_MODAL);
			newStage.setScene(newScene);
			newStage.setTitle("Añadir Deportista");
			newStage.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void anadirEquipo(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AñadirEquipo.fxml"));
		Parent root;
		try {
			root = loader.load();
			Scene newScene = new Scene(root);
			Stage newStage = new Stage();
			newScene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
			AñadirEquipoController control = loader.getController();

			newStage.initModality(Modality.APPLICATION_MODAL);
			newStage.setScene(newScene);
			newStage.setTitle("Añadir Equipo");
			newStage.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void anadirOlimpiada(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AñadirOlimpiada.fxml"));
		Parent root;
		try {
			root = loader.load();
			Scene newScene = new Scene(root);
			Stage newStage = new Stage();
			newScene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
			AñadirOlimpiadaController control = loader.getController();

			newStage.initModality(Modality.APPLICATION_MODAL);
			newStage.setScene(newScene);
			newStage.setTitle("Añadir Olimpiada");
			newStage.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void eliminar(ActionEvent event) {
		if(cbTabla.getSelectionModel().getSelectedItem().toString().equals("Participacion")) {
			Deportista dep=new Deportista(tablaParticipacion.getSelectionModel().getSelectedItem().getNomDeportista(),tablaParticipacion.getSelectionModel().getSelectedItem().getSexo(),
					Integer.parseInt(tablaParticipacion.getSelectionModel().getSelectedItem().getPeso()),Integer.parseInt(tablaParticipacion.getSelectionModel().getSelectedItem().getAltura()));
			String nom=tablaParticipacion.getSelectionModel().getSelectedItem().getNomEvento().substring(0,tablaParticipacion.getSelectionModel().getSelectedItem().getNomEvento().indexOf(","));
			String anio=tablaParticipacion.getSelectionModel().getSelectedItem().getNomEvento().substring(tablaParticipacion.getSelectionModel().getSelectedItem().getNomEvento().indexOf(",")+1, tablaParticipacion.getSelectionModel().getSelectedItem().getNomEvento().length());
			new Evento(nom, anio);
			
			pd.eliminarParticipacion(ed.sacarId(nom, anio),dd.bucarId(dep) );
		}else {
			Evento ev=new Evento(tablaEvento.getSelectionModel().getSelectedItem().getNom_Evento(), tablaEvento.getSelectionModel().getSelectedItem().getNom_Olimpiada());
			ed.eliminarEvento(ed.sacarId(ev.getNom_Evento(), ev.getNom_Olimpiada()));
			
		}
		listParticipacion = pd.cargarParticipacion();
		tablaParticipacion.setItems(listParticipacion);
		listEventos = ed.cargarEvento();
		tablaEvento.setItems(listEventos);

	}

	@FXML
	void eliminarDeporte(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/lista.fxml"));
		Parent root;
		DeporteDao ded=new DeporteDao();
		try {
			root = loader.load();
			Scene newScene = new Scene(root);
			Stage newStage = new Stage();
			newScene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
			listaController control = loader.getController();
			control.getLblTitulo().setText("Deportes");
			control.getListObjetos().setItems(ded.sacarDeportes());
			control.setTipo(2);
			newStage.initModality(Modality.APPLICATION_MODAL);
			newStage.setScene(newScene);
			newStage.setTitle("Deportes");
			newStage.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		listParticipacion = pd.cargarParticipacion();
		tablaParticipacion.setItems(listParticipacion);
		listEventos = ed.cargarEvento();
		tablaEvento.setItems(listEventos);
	}

	@FXML
	void eliminarDeportista(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/lista.fxml"));
		Parent root;
		DeportistaDao ded=new DeportistaDao();
		try {
			root = loader.load();
			Scene newScene = new Scene(root);
			Stage newStage = new Stage();
			newScene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
			listaController control = loader.getController();
			control.getLblTitulo().setText("Deportistas");
			control.getListObjetos().setItems(ded.sacarDeportistas());
			control.setTipo(2);
			newStage.initModality(Modality.APPLICATION_MODAL);
			newStage.setScene(newScene);
			newStage.setTitle("Deportistas");
			newStage.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		listParticipacion = pd.cargarParticipacion();
		tablaParticipacion.setItems(listParticipacion);
		listEventos = ed.cargarEvento();
		tablaEvento.setItems(listEventos);
	}

	@FXML
	void eliminarEquipo(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/lista.fxml"));
		Parent root;
		EquipoDao eqd=new EquipoDao();
		try {
			root = loader.load();
			Scene newScene = new Scene(root);
			Stage newStage = new Stage();
			newScene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
			listaController control = loader.getController();
			control.getLblTitulo().setText("Equipos");
			control.getListObjetos().setItems(eqd.sacarEquipos());
			control.setTipo(2);
			newStage.initModality(Modality.APPLICATION_MODAL);
			newStage.setScene(newScene);
			newStage.setTitle("Equipos");
			newStage.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		listParticipacion = pd.cargarParticipacion();
		tablaParticipacion.setItems(listParticipacion);
		listEventos = ed.cargarEvento();
		tablaEvento.setItems(listEventos);
	}

	@FXML
	void eliminarOlimpiada(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/lista.fxml"));
		Parent root;
		OlimpiadaDao olD=new OlimpiadaDao();
		try {
			root = loader.load();
			Scene newScene = new Scene(root);
			Stage newStage = new Stage();
			newScene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
			listaController control = loader.getController();
			control.getLblTitulo().setText("Olimpiadas");
			control.getListObjetos().setItems(olD.sacarOlimpiada());
			control.setTipo(2);
			newStage.initModality(Modality.APPLICATION_MODAL);
			newStage.setScene(newScene);
			newStage.setTitle("Olimpiadas");
			newStage.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		listParticipacion = pd.cargarParticipacion();
		tablaParticipacion.setItems(listParticipacion);
		listEventos = ed.cargarEvento();
		tablaEvento.setItems(listEventos);
	}
	

	@FXML
	void modificar(ActionEvent event) {
		if(tablaEvento.getSelectionModel().getSelectedItem()!=null || tablaParticipacion.getSelectionModel().getSelectedItem()!=null) {
			if (cbTabla.getSelectionModel().getSelectedItem().toString().equals("Participacion")) {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AñadirParticipacion.fxml"));
				Parent root;
			
				try {
					root = loader.load();
					Scene newScene = new Scene(root);

					Stage newStage = new Stage();
					newScene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
					AñadirParticipacionController control = loader.getController();
					Deportista dep=new Deportista(tablaParticipacion.getSelectionModel().getSelectedItem().getNomDeportista(),tablaParticipacion.getSelectionModel().getSelectedItem().getSexo(),
							Integer.parseInt(tablaParticipacion.getSelectionModel().getSelectedItem().getPeso()),Integer.parseInt(tablaParticipacion.getSelectionModel().getSelectedItem().getAltura()));
					//
					control.getLblTitulo().setText("Modificar Participacion Olimpica");
					String nom=tablaParticipacion.getSelectionModel().getSelectedItem().getNomEvento().substring(0,tablaParticipacion.getSelectionModel().getSelectedItem().getNomEvento().indexOf(","));
					String anio=tablaParticipacion.getSelectionModel().getSelectedItem().getNomEvento().substring(tablaParticipacion.getSelectionModel().getSelectedItem().getNomEvento().indexOf(",")+1, tablaParticipacion.getSelectionModel().getSelectedItem().getNomEvento().length());
					control.getComboDeportista().getSelectionModel().select(dep);
					control.getTxtEdad().setText(tablaParticipacion.getSelectionModel().getSelectedItem().getEdad()+"");
					control.getComboEquipo().getSelectionModel().select(new Equipos(tablaParticipacion.getSelectionModel().getSelectedItem().getEquipo()));
					control.getComboEvento().getSelectionModel().select(new Evento(nom,anio));
					if(tablaParticipacion.getSelectionModel().getSelectedItem().getMedalla().toLowerCase().equals("oro")) {
						control.getRbOro().setSelected(true);
					}else {
						if(tablaParticipacion.getSelectionModel().getSelectedItem().getMedalla().toLowerCase().equals("plata")) {
							control.getRbPlata().setSelected(true);
						}else {
							if(tablaParticipacion.getSelectionModel().getSelectedItem().getMedalla().toLowerCase().equals("bronze")) {
								control.getRbBronze().setSelected(true);
							}else {
								control.getRbNada().setSelected(true);
							}
						}
					}
					control.getComboDeportista().setDisable(true);
					control.getComboEvento().setDisable(true);
					control.setIdAntiguoDeportista(dep);
					
					control.setIdAntiguoEvento(new Evento(nom, anio));
					//
					newStage.initModality(Modality.APPLICATION_MODAL);

					newStage.setScene(newScene);
					newStage.setTitle("Participacion");
					newStage.showAndWait();
					listParticipacion = pd.cargarParticipacion();
					tablaParticipacion.setItems(listParticipacion);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AñadirEvento.fxml"));
				Parent root;
				try {
					root = loader.load();
					Scene newScene = new Scene(root);
					Stage newStage = new Stage();
					newScene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
					AñadirController control = loader.getController();
					//
					control.setLblTitulo("Modificar Evento Olimpico");
					control.getTxtNumero1().setText(tablaEvento.getSelectionModel().getSelectedItem().getNom_Evento());
					control.getComboDeporte().getSelectionModel().select(new Deporte(tablaEvento.getSelectionModel().getSelectedItem().getNom_Deporte()));
					control.setId_Olimpiada_Antiguo(new Olimpiada(tablaEvento.getSelectionModel().getSelectedItem().getNom_Olimpiada(),
																	tablaEvento.getSelectionModel().getSelectedItem().getAnio_Olimpiada(),
																	tablaEvento.getSelectionModel().getSelectedItem().getTemporada_Olimpiada(),
																	tablaEvento.getSelectionModel().getSelectedItem().getCiudad_Olimpiada()));
					control.setNomAntiguo(tablaEvento.getSelectionModel().getSelectedItem().getNom_Evento());
					
					control.getComboOlimpiada().getSelectionModel().select(new Olimpiada(tablaEvento.getSelectionModel().getSelectedItem().getNom_Olimpiada(),
																						tablaEvento.getSelectionModel().getSelectedItem().getAnio_Olimpiada(),
																						tablaEvento.getSelectionModel().getSelectedItem().getTemporada_Olimpiada(),
																						tablaEvento.getSelectionModel().getSelectedItem().getCiudad_Olimpiada()));
					//
					newStage.initModality(Modality.APPLICATION_MODAL);

					newStage.setScene(newScene);
					newStage.setTitle("Evento");
					newStage.showAndWait();
					listEventos = ed.cargarEvento();
					tablaEvento.setItems(listEventos);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			listParticipacion = pd.cargarParticipacion();
			tablaParticipacion.setItems(listParticipacion);
			listEventos = ed.cargarEvento();
			tablaEvento.setItems(listEventos);
		}
		

	}

	@FXML
	void modificarDeporte(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/lista.fxml"));
		Parent root;
		DeporteDao ded=new DeporteDao();
		try {
			root = loader.load();
			Scene newScene = new Scene(root);
			Stage newStage = new Stage();
			newScene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
			listaController control = loader.getController();
			control.getLblTitulo().setText("Deportes");
			control.getListObjetos().setItems(ded.sacarDeportes());
			control.setTipo(1);
			newStage.initModality(Modality.APPLICATION_MODAL);
			newStage.setScene(newScene);
			newStage.setTitle("Deportes");
			newStage.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		listParticipacion = pd.cargarParticipacion();
		tablaParticipacion.setItems(listParticipacion);
		listEventos = ed.cargarEvento();
		tablaEvento.setItems(listEventos);
	}

	@FXML
	void modificarDeportista(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/lista.fxml"));
		Parent root;
		DeporteDao ded=new DeporteDao();
		try {
			root = loader.load();
			Scene newScene = new Scene(root);
			Stage newStage = new Stage();
			newScene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
			listaController control = loader.getController();
			control.getLblTitulo().setText("Deportistas");
			control.getListObjetos().setItems(dd.sacarDeportistas());
			control.setTipo(1);
			newStage.initModality(Modality.APPLICATION_MODAL);
			newStage.setScene(newScene);
			newStage.setTitle("Deportistas");
			newStage.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		listParticipacion = pd.cargarParticipacion();
		tablaParticipacion.setItems(listParticipacion);
		listEventos = ed.cargarEvento();
		tablaEvento.setItems(listEventos);
	}

	@FXML
	void modificarEquipo(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/lista.fxml"));
		Parent root;
		EquipoDao ded=new EquipoDao();
		try {
			root = loader.load();
			Scene newScene = new Scene(root);
			Stage newStage = new Stage();
			newScene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
			listaController control = loader.getController();
			control.getLblTitulo().setText("Equipos");
			control.getListObjetos().setItems(ded.sacarEquipos());
			control.setTipo(1);
			newStage.initModality(Modality.APPLICATION_MODAL);
			newStage.setScene(newScene);
			newStage.setTitle("Equipos");
			newStage.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		listParticipacion = pd.cargarParticipacion();
		tablaParticipacion.setItems(listParticipacion);
		listEventos = ed.cargarEvento();
		tablaEvento.setItems(listEventos);
	}

	@FXML
	void modificarOlimpiada(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/lista.fxml"));
		Parent root;
		OlimpiadaDao od=new OlimpiadaDao();
		try {
			root = loader.load();
			Scene newScene = new Scene(root);
			Stage newStage = new Stage();
			newScene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
			listaController control = loader.getController();
			control.getLblTitulo().setText("Olimpiadas");
			control.getListObjetos().setItems(od.sacarOlimpiada());
			control.setTipo(1);
			newStage.initModality(Modality.APPLICATION_MODAL);
			newStage.setScene(newScene);
			newStage.setTitle("Olimpiadas");
			newStage.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		listParticipacion = pd.cargarParticipacion();
		tablaParticipacion.setItems(listParticipacion);
		listEventos = ed.cargarEvento();
		tablaEvento.setItems(listEventos);
	}

	@FXML
	void seleccionar(ActionEvent event) {
		if (cbTabla.getSelectionModel().getSelectedItem().toString().equals("Participacion")) {
			ParticipacionDao pd = new ParticipacionDao();
			listParticipacion = pd.cargarParticipacion();
			tablaParticipacion.setItems(listParticipacion);
			tablaParticipacion.setVisible(true);
			tablaEvento.setVisible(false);
		} else {
			ed = new EventoDao();
			listEventos = ed.cargarEvento();
			tablaEvento.setItems(listEventos);
			tablaParticipacion.setVisible(false);
			tablaEvento.setVisible(true);
		}
	}

	// METODOS DE FILTRAR
	@FXML
	void filtrarTemporada(ActionEvent event) {
		listEventos = ed.cargarEvento();

		if (cbTabla.getSelectionModel().getSelectedItem().toString().equals("Participacion")) {

			listParticipacion = pd.FiltrarParticipacion(checkBoxInvierno.isSelected(), checkBoxVerano.isSelected());
			tablaParticipacion.setItems(listParticipacion);

		} else {
			ObservableList<Evento> listTemporada = FXCollections.observableArrayList();
			for (int i = 0; i < listEventos.size(); i++) {
				if (checkBoxInvierno.isSelected()) {
					if (listEventos.get(i).getTemporada_Olimpiada().equals("Winter")) {
						listTemporada.add(listEventos.get(i));
					}
				}
				if (checkBoxVerano.isSelected()) {
					if (listEventos.get(i).getTemporada_Olimpiada().equals("Summer")) {
						listTemporada.add(listEventos.get(i));
					}
				}
			}
			listEventos = listTemporada;
			tablaEvento.setItems(listEventos);
		}

	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<String> arrTablas = FXCollections.observableArrayList();
		arrTablas.add("Evento");
		arrTablas.add("Participacion");
		cbTabla.setItems(arrTablas);
		cbTabla.getSelectionModel().select(0);
		tablaEvento.setVisible(true);
		// tablaParticipacion.setVisible(false);

		// ASIGNAR CAMPOS A LA TABLA EVENTO y los tamaños
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

		// ASIGNAR CAMPOS A LA TABLA Participacion y los tamaños
		colNomDeportista.setCellValueFactory(new PropertyValueFactory<>("nomDeportista"));

		colSexo.setCellValueFactory(new PropertyValueFactory<>("sexo"));

		colPeso.setCellValueFactory(new PropertyValueFactory<>("peso"));

		colAltura.setCellValueFactory(new PropertyValueFactory<>("altura"));

		colEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));

		colEquipo.setCellValueFactory(new PropertyValueFactory<>("equipo"));

		colMedalla.setCellValueFactory(new PropertyValueFactory<>("medalla"));

		colEvento.setCellValueFactory(new PropertyValueFactory<>("nomEvento"));

		pd = new ParticipacionDao();
		// ASIGNAR A LA TABLA UNA LISTA
		ed = new EventoDao();
		listEventos = ed.cargarEvento();
		tablaEvento.setItems(listEventos);
		
		dd=new DeportistaDao();
		
		//IDIOMAS
		String idioma = Propiedades.getValor("idioma");
		String region = Propiedades.getValor("region");
		Locale.setDefault(new Locale(idioma, region));
		bundle = ResourceBundle.getBundle("idiomas/messages");

	}

}
