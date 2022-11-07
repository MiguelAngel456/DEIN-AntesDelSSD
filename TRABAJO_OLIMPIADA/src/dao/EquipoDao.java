package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import conexionBD.ConexionDB;
import model.Equipos;

public class EquipoDao {
private ConexionDB conexion;
	
	//PARA AÑDIR FILAS A LA TABLA DEPORTE
	public boolean anadirEquipo(Equipos e) {
		try {
			conexion = new ConexionDB();
	        Connection con = conexion.getConexion();
			PreparedStatement pst = con.prepareStatement("insert into Equipo (nombre,iniciales) values(?,?)");
			
			pst.setString(1, e.getNombre());
			pst.setString(2, e.getIniciales());

			pst.execute();
			con.close();
			pst.close();
			return true;
		} catch (SQLException f) {
			// TODO Auto-generated catch block
			f.printStackTrace();
		}
		
		
		
		return false;
	}
}
