package ejercicioD;


import java.util.Iterator;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.Persona;

public class ejercicioD extends Application{
	private TextField txtNombre,txtApellido,txtEdad;
	private Button btnAgregar,btnModificar,btnEliminar;
	private ObservableList<Persona> listPersonas;
	private TableView<Persona> tablaPersona;
	private int fallos,suma;
	@Override
	public void start(Stage primaryStage){
		try {
			//DECLARANDO LOS ELEMENTOS
				//botones
			btnAgregar=new Button("Agregar Persona");
				//lista en la que se basa la tabla
			listPersonas=FXCollections.observableArrayList();
				//la tabla de personas
			tablaPersona=new TableView<>(listPersonas);
			//*******************************************
			//CREANDO LA PESTAÑA
			 GridPane root=new GridPane();
			 	//Unico apartado
			 TableColumn<Persona, String> colNombre=new TableColumn<>("NOMBRE");
			 colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
			 colNombre.prefWidthProperty().bind(tablaPersona.widthProperty().multiply(0.33));
			 
			 TableColumn<Persona, String> colApellido=new TableColumn<>("APELLIDOS");
			 colApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
			 colApellido.prefWidthProperty().bind(tablaPersona.widthProperty().multiply(0.33));
			 
			 TableColumn<Persona, String> colEdad=new TableColumn<>("EDAD");
			 colEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));
			 colEdad.prefWidthProperty().bind(tablaPersona.widthProperty().multiply(0.33));
			 colEdad.setStyle("-fx-alignment: CENTER;");
			 
			 tablaPersona.getColumns().addAll(colNombre,colApellido,colEdad);
			 colNombre.setMinWidth(200);
			 colApellido.setMinWidth(200);
			 colEdad.setMinWidth(100);
			 //******************
			 FlowPane fp=new FlowPane(40,40);
			
			 root.add(fp, 0, 1,GridPane.REMAINING,1);
			 fp.setAlignment(Pos.CENTER);
			// fp.setPadding(new Insets(0,0,0,200));
			 //**********************************
			 root.setStyle("-fx-padding: 10;");
			 root.add(tablaPersona,1,0,2,1);
			 
			 //PARA QUE SE VEA
			 ColumnConstraints c1=new ColumnConstraints();
			
			 
			 c1.setHgrow(Priority.ALWAYS);
			 root.setVgrow(tablaPersona, Priority.ALWAYS);
			 root.getColumnConstraints().add(c1);
			 
			 String imagePath = getClass().getResource("/picture/agenda.png").toString();
			 primaryStage.getIcons().add(new Image(imagePath));
			 Scene scene = new Scene(root);
			 primaryStage.setScene(scene);
			 primaryStage.setTitle("PERSONAS");
			 primaryStage.show();
			 primaryStage.sizeToScene();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	public boolean añadir() {
		Persona p=new Persona(txtNombre.getText(), txtApellido.getText(), txtEdad.getText());
		if(listPersonas.contains(p)==true) {
			return true;
		}else {
			return false;
		}
		
	}
	
	public String comprobar() {
		String mal="";
		fallos=0;
		
		//NOMBRE
		if (txtNombre.getText().length()==0) {
			mal+="El campo nombre es obligatorio";
			fallos++;
		}
		//APELLIDO
		if (txtApellido.getText().length()==0) {
			mal+="\n El campo apellido es obligatorio";
			fallos++;
		}
		if (txtEdad.getText().length()==0) {
			mal+="\n El campo edad es obligatorio rellenado";
			fallos++;
		}
		try {
			int num=Integer.parseInt(txtEdad.getText());
		} catch (NumberFormatException e) {
			if (txtEdad.getText().length()!=0) {
				mal+="\n El campo edad tiene que ser numeros";
				fallos++;
			}
		}
		if (this.añadir()==true) {
			mal+="\n ya existe esta persona en la tabla";
			fallos++;
		}
		
		if(fallos>0) {
			return mal;
		}else {

			return "Persona añadida correctamente";
			
		}
	}
	private void mostrarAlertError(Window win) {
		Alert alert;
		String texto=this.comprobar();
		if (fallos>0) {
			alert = new Alert(Alert.AlertType.ERROR);
		}else {
			alert = new Alert(Alert.AlertType.INFORMATION);
			Persona p=new Persona(txtNombre.getText(), txtApellido.getText(), txtEdad.getText());
			listPersonas.add(p);
		}
		alert.setHeaderText(null);
		alert.initOwner(win);
		alert.setContentText(texto);
		alert.setTitle("TUS DATOS");
		alert.showAndWait();
	}
	public static void main(String[] args) {
		launch(args);
	}
}
