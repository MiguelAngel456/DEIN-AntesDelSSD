package application;
	
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;


public class Main extends Application {
	static ObservableList<String> listEdades=FXCollections.observableArrayList("Menores de 18","Entre 18 y 30","Entre 31 y 50","Entre 51 y 70","Mayores de 70");
	@Override
	
	public void start(Stage primaryStage) {
		try {
			//CREAR EL GRIDPANEL
			GridPane root = new GridPane();
			//Crear las labels
			Label ltitulo=new Label("ENCUESTA");
			Label lProfesion=new Label("Profesion");
			Label lHermanos=new Label("Nº Hermanos");
			Label ledad=new Label("Edad");
			Label lSexo=new Label("Sexo:");
			//Crear los TextArea
			TextField txtProfesion=new TextField();
			TextField txtHermanos=new TextField();
			//crear el comboBox
			ComboBox<String> CBedad=new ComboBox<>(listEdades);
			
			
			//metidendo los apartados al grid
				//primera fila
			root.add(ltitulo, 1, 0,GridPane.REMAINING, 1);
				//Segunda fila
			root.add(lProfesion, 0, 1,1,1);
			root.add(txtProfesion,	1, 1,3,1);
				//Tercera fila
			root.add(lHermanos, 0, 2,1,1);
			root.add(txtHermanos, 1, 2,1,1);
			root.add(ledad, 2, 2,1,1);
			root.add(CBedad, 3, 2, 1, 1);
			
			//pARA QUE SE PUEDA VER
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("ENCUESTA");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
