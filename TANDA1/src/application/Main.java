package application;
	
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class Main extends Application {
	static ObservableList<String> listEdades=FXCollections.observableArrayList("Menores de 18","Entre 18 y 30","Entre 31 y 50","Entre 51 y 70","Mayores de 70");
	static ObservableList<String> listDeportes=FXCollections.observableArrayList("Tenis","Futbol","Baloncesto","Natacion","Ciclismo","otro");
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
			Label lSatisfaccion=new Label("Marque del 1 al 10 su grado de satisfaccion");
			//Crear los TextArea
			TextField txtProfesion=new TextField();
			TextField txtHermanos=new TextField();
			//crear el comboBox
			ComboBox<String> CBedad=new ComboBox<>(listEdades);
			//crear el chechBox
			CheckBox CBDeporte=new CheckBox("Practicas algun deporte");
			//crear lista 
			ListView<String> listaDep=new ListView<>(listDeportes);
			listaDep.setDisable(true);
			listaDep.setPrefSize(200, 70);
			
			//crear el slider(barra de nivel
			Slider s1= new Slider(0,10,5);
			s1.setShowTickMarks(true);
			s1.setShowTickLabels(true);
			s1.setMajorTickUnit(1);
			//********************
			Slider s2= new Slider(0,10,5);
			s2.setShowTickMarks(true);
			s2.setShowTickLabels(true);
			s2.setMajorTickUnit(1);
			//********************
			Slider s3= new Slider(0,10,5);
			s3.setShowTickMarks(true);
			s3.setShowTickLabels(true);
			s3.setMajorTickUnit(1);
			
			
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
				//cuarta fila//l
			RadioButton RBHombre=new RadioButton("Hombre");
			RadioButton RBMujer=new RadioButton("Mujer");
			RadioButton RBOtro=new RadioButton("Otro");
			ToggleGroup group = new ToggleGroup();
			group.getToggles().addAll(RBHombre,RBMujer,RBOtro);
			HBox HSexo=new HBox(10,RBHombre,RBMujer,RBOtro);
			HSexo.setSpacing(50);
			root.add(lSexo, 0, 3,1,1);
			root.add(HSexo, 1, 3,1,1);
				//quinta fila
		//	CBDeporte.selectedProperty().addListener(eventoCB(CBDeporte,listaDep));
			root.add(CBDeporte, 0, 4,2,1);
			VBox VDeportes=new VBox(new Label("¿Cual?"),listaDep);
			root.add(VDeportes, 2, 4,2,1);
				//Sexta Fila
			root.add(lSatisfaccion, 1, 5);
				//Septima Fila
			root.add(new Label("Compras"), 0, 8,1,1);
			root.add(s1, 1, 8,3,1);
			
				//Octava fila
			root.add(new Label("Ver TeleVision"), 0, 9,1,1);
			root.add(s2, 1, 9,3,1);
			
				//Novena fila
			root.add(new Label("ir al Cine"), 0, 10,1,1);
			root.add(s3, 1, 10,3,1);
			//PARA QUE SE PUEDA VER
			
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("ENCUESTA");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	void eventoCB(CheckBox CBDeporte,ListView<String> listaDep) {
		if (CBDeporte.isSelected()==true) {
			listaDep.setDisable(true);;
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}