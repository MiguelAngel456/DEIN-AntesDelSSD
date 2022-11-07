package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexionBD.ConexionDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Deporte;
import model.Deportista;

public class DeportistaDao {
	
	
	
	
private ConexionDB conexion;
	
	//PARA AÑDIR FILAS A LA TABLA DEPORTE
	public boolean anadirDeporte(Deportista d) {
		try {
			conexion = new ConexionDB();
	        Connection con = conexion.getConexion();
			PreparedStatement pst = con.prepareStatement("insert into Deportista (nombre,sexo,peso,altura,foto) values(?,?,?,?,?)");
			
			pst.setString(1, d.getNombre());
			pst.setString(2, d.getSexo());
			pst.setInt(3, d.getPeso());
			pst.setInt(4, d.getAltura());
			pst.setString(5, null);
			
			pst.execute();
			con.close();
			pst.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return false;
	}
	public 	ObservableList<Deportista> sacarDeportistas() {
		ObservableList<Deportista> arr=FXCollections.observableArrayList();
        String sql;
		
        try {
            conexion = new ConexionDB();
            Connection con = conexion.getConexion();
            sql="SELECT * FROM Deportista;";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	//sacar datos 
            	String nom=rs.getString("nombre");
            	String sexo=rs.getString("sexo");
            	int peso=rs.getInt("peso");
            	int altura=rs.getInt("altura");
            	
            	//crear el evento
            	Deportista dep=new Deportista(nom, sexo, peso, altura);
            	arr.add(dep);
            	
            }
            return arr;
            
        }catch (SQLException e) {
			// TODO: handle exception
		}
        return arr;
	}
}
