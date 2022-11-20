package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.FileChooser.ExtensionFilter;
import model.Deportista;
import model.Olimpiada;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ResourceBundle;

import dao.DeportistaDao;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

public class AñadirDeportistaController implements Initializable{

    @FXML
    private Button btnAceptar;

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnFoto;

    @FXML
    private ImageView fotoDeportista;

    @FXML
    private Label lblNumero2;

    @FXML
    private Label lblNumero6;

    @FXML
    private Label lblTitulo;

    @FXML
    private RadioButton rbFemenino;

    @FXML
    private RadioButton rbMasculino;

    @FXML
    private ToggleGroup rgSexo;

    @FXML
    private TextField txtAltura;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtPeso;


    private int idAnt;
    private DeportistaDao dd;
    private File archivo;
    private InputStream img;
	// Event Listener on Button[#btnAceptar].onAction
	@FXML
	public void aceptar(ActionEvent event) {
		// TODO Autogenerated
		dd=new DeportistaDao();
		String nombre=txtNombre.getText();
		String sexo;
		if(rbMasculino.isSelected()) {
			sexo="M";
		}else {
			sexo="F";
		}

		
		if(lblTitulo.getText().equals("Modificar Deportista")) {
			if(comprobar().length()==0) {
				int peso=Integer.parseInt(txtPeso.getText());
				int altu=Integer.parseInt(txtAltura.getText());
				System.out.println(idAnt);
				Deportista dep=new Deportista(idAnt,nombre, sexo, peso, altu,img);
				try {
					dd.modificarDeportista(dep);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					error("Error en el sql");
				}
				info();
				Stage stage = (Stage) btnAceptar.getScene().getWindow();
				stage.close();
			}else {
				error();
			}
			
		}else {
			if(comprobar().length()==0) {
				int peso=Integer.parseInt(txtPeso.getText());
				int altu=Integer.parseInt(txtAltura.getText());
				try {
					int id=dd.ultimoId()+1;
					Deportista dep=new Deportista(id,nombre, sexo, peso, altu,img);
					dd.anadirDeporte(dep);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					error("Error en el sql");
				}
				info();
				Stage stage = (Stage) btnAceptar.getScene().getWindow();
				stage.close();
			}else {
				error();
			}

		}
		
		
		
		
		
	}
	// Event Listener on Button[#btnCancelar].onAction
	@FXML
	public void cerrar(ActionEvent event) {
		// TODO Autogenerated
		Stage stage = (Stage) btnCancelar.getScene().getWindow();
		stage.close();
	}
	public String comprobar() {
		String fallo="";
		if(txtNombre.getText().length()==0) {
			fallo+="\n El campo del nombre  tiene que tener contenido";
		}
		if(txtNombre.getText().length()>150) {
			fallo+="\n El campo del nombre  no puede tener mas de 150 caracteres";
		}
		
		if(txtAltura.getText().length()==0) {
			fallo+="\n El campo altura tiene que tener contenido";
		}
		if(txtPeso.getText().length()==0) {
			fallo+="\n El campo peso tiene que tener contenido";
		}
		//NO PUEDE MEDIR MAS DE 3 DIGITOS PORQUE NO ES POSIBLE QUE MIDA MILES DE CM
		if(txtAltura.getText().length()>3) {
			fallo+="\n El campo altura no puede tener mas de 3 digitos";
		}
		//NO PUEDE PESAR MAS DE 3 DIGITOS PORQUE NO ES POSIBLE QUE PESE MILES DE KILOS
		if(txtPeso.getText().length()>3) {
			fallo+="\n El campo peso no puede tener mas de 3 digitos";
		}
		//El campo peso y altura son obligatorios porque en algunos deportes e tiene en cuenta para hacer secciones por ejemplo el boxeo se divide por pesos
		int altu=0,peso=0;
		
		try {
			int i=Integer.parseInt(txtPeso.getText().toString());
			peso=Integer.parseInt(txtPeso.getText().toString());
		} catch (NumberFormatException e) {
			// TODO: handle exception
			fallo+="\n El campo peso tiene que ser numeros";
		}
		
		try {
			int i=Integer.parseInt(txtAltura.getText().toString());
			
			altu=Integer.parseInt(txtAltura.getText().toString());
		} catch (NumberFormatException e) {
			// TODO: handle exception
			fallo+="\n El campo altura tiene que ser numeros";
		}
		
		String nombre=txtNombre.getText();
		String sexo;
		if(rbMasculino.isSelected()) {
			sexo="M";
		}else {
			sexo="F";
		}
		
		int id;
		try {
			id = dd.ultimoId();
			Deportista dep=new Deportista(id,nombre, sexo, peso, altu);
			if(dd.sacarDeportistas().contains(dep)) {
				fallo+="\n Esa Olimpiada ya existe";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			error("Error en el sql");
		}
		
		
		
		return fallo;
	}
    @FXML
    void eligirFoto(ActionEvent event) {
    	FileChooser FC=new FileChooser();
		FC.setTitle("Elige la imagen");
		String currentPath = Paths.get(".").toAbsolutePath().normalize().toString();
		FC.setInitialDirectory(new File(currentPath));
		FC.getExtensionFilters().add(new ExtensionFilter("Archivo imagen", "*.png", "*.jpg"));
		FC.setSelectedExtensionFilter(FC.getExtensionFilters().get(0));
		archivo=FC.showOpenDialog((Stage)btnFoto.getScene().getWindow());

		//ENSEÑAR LA IMAGEN
		if(archivo!=null) {
			try {
				FileInputStream img1=new FileInputStream(archivo);
				this.img=img1;
				this.fotoDeportista.setImage(new Image(img1));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				
			}
		}
		
    }
	public void error (String text) {
		Alert alert;
		String texto=text;
		alert = new Alert(Alert.AlertType.ERROR);
		alert.setContentText(texto);
		alert.setHeaderText(null);
		alert.setTitle("ERROR");
		alert.showAndWait();
	}
	public void error () {
		Alert alert;
		String texto=comprobar();
		alert = new Alert(Alert.AlertType.ERROR);
		alert.setContentText(texto);
		alert.setHeaderText(null);
		alert.setTitle("ERROR");
		alert.showAndWait();
	}
	public void info() {
		Alert alert;
		alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setContentText("ACCION HECHA CORRECTAMENTE");
		alert.setHeaderText(null);
		alert.setTitle("INFO");
		alert.showAndWait();
	}
	public void rellenar(Deportista dep) {
		this.lblTitulo.setText("Modificar Deportista");
		
		this.txtNombre.setText(dep.getNombre());
		this.txtAltura.setText(String.valueOf(dep.getAltura()));
		this.txtPeso.setText(String.valueOf(dep.getPeso()));
		if(dep.getSexo().equals("M")) {
			this.rbMasculino.setSelected(true);
		}else {
			this.rbFemenino.setSelected(true);
		}
		if(dep.getFoto()!=null) {
			System.out.println("aaaaaaaa");
			try {
				this.img=dd.sacarFoto(dep);
				fotoDeportista.setImage(new Image(img));
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				error("Error en el sql");
			}
		}
		
		idAnt=dep.getId_deportista();
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		dd=new DeportistaDao();
		rbMasculino.setSelected(true);
	}
}
