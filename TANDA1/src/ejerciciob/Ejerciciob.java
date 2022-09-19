package ejerciciob;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Persona;

public class Ejerciciob extends Application{
	private TextField txtNombre,txtApellido,txtEdad;
	private Button btnAgregar;
	private ObservableList<Persona> listPersonas;
	private TableView<Persona> tablaPersona;
	private int fallos;
	@Override
	public void start(Stage primaryStage){
		try {
			//DECLARANDO LOS ELEMENTOS
				//textfield
			txtNombre=new TextField();
			txtApellido=new TextField();
			txtEdad=new TextField();
				//botones
			btnAgregar=new Button("Agregar Persona");
				//lista en la que se basa la tabla
			listPersonas=FXCollections.observableArrayList();
				//la tabla de personas
			tablaPersona=new TableView<>(listPersonas);
			//*******************************************
			//CREANDO LA PESTAÑA
			 GridPane root=new GridPane();
			 	//primer apartado: los datos de la persona y el boton de agregar
			 VBox datos=new VBox(10);
			 datos.getChildren().addAll(new Label("Nombre"),txtNombre);
			 datos.getChildren().addAll(new Label("Apellidos"),txtApellido);
			 datos.getChildren().addAll(new Label("Edad"),txtEdad);
			 
			 datos.getChildren().add(btnAgregar);
			 datos.setAlignment(Pos.CENTER_LEFT);
			 root.add(datos, 0,0);
			 	//Segundo apartado
			 TableColumn<Persona, String> colNombre=new TableColumn<>("NOMBRE");
			 TableColumn<Persona, String> colApellido=new TableColumn<>("APELLIDOS");
			 TableColumn<Persona, String> colEdad=new TableColumn<>("EDAD");
			 tablaPersona.getColumns().addAll(colNombre,colApellido,colEdad);
			 datos.setStyle("-fx-padding: 10;");
			 root.add(tablaPersona,1,0);
			 
			 
			 //PARA QUE SE VEA
			 Scene scene = new Scene(root);
			 primaryStage.setScene(scene);
			 primaryStage.setTitle("ENCUESTA");
			 primaryStage.show();
			 primaryStage.sizeToScene();
		
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	public void comprobar() {
		String bien="";
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
		try {
			int num=Integer.parseInt(txtEdad.getText());
		} catch (NumberFormatException e) {
			mal+="\n El campo edad tiene que ser numeros";
			if (txtEdad.getText().length()>0) {
				mal+="\n El campo edad es obligatorio rellenado";
			}
		}
	}
	public static void main(String[] args) {
		launch(args);
	}
}
