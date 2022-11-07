package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import conexionBD.ConexionDB;
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
}
