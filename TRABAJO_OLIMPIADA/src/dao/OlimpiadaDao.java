package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexionBD.ConexionDB;
import model.Deporte;
import model.Olimpiada;

public class OlimpiadaDao {
	private ConexionDB conexion;
	
	//PARA AÑDIR FILAS A LA TABLA OLIMPIADA
		public boolean anadirOlimpiada(Olimpiada ol) {
			try {
				conexion = new ConexionDB();
		        Connection con = conexion.getConexion();
	            
				PreparedStatement pst = con.prepareStatement("insert into Olimpiada (nombre, anio, temporada, ciudad) values(?,?,?,?)");
				
				pst.setString(1, ol.getNombre());
				pst.setInt(2, ol.getAnio());
				pst.setString(3, ol.getTemporada());
				pst.setString(4, ol.getCiudad());
				

				pst.execute();
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			return false;
		}
	
	
	//COMPROBAR SI EXISTE LA OLIMPIA
	public boolean comprobarOlimpiada(Olimpiada ol) {
		try {
			conexion = new ConexionDB();
			Connection con = conexion.getConexion();
			//System.out.println(d.getDeporte());
			String sql = "SELECT * FROM Olimpiada WHERE nombre = '"+ol.getNombre()+"';";
			 
			PreparedStatement ps = con.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();
	        boolean existe=rs.next();
	        rs.close();
	        ps.close();
	        con.close();
	        if(existe) {
	        	return true;
	        }else {
	        	return false;
	        }
	        
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	//SACAR EL ID DE LA OLIMPIADA BUSCADO
		public int sacarId(Olimpiada d) {
			try {
				conexion = new ConexionDB();
				Connection con = conexion.getConexion();
				//System.out.println(d.getDeporte());
				String sql = "SELECT * FROM Olimpiada WHERE nombre = '"+d.getNombre()+"';";
				 
				PreparedStatement ps = con.prepareStatement(sql);
		        ResultSet rs = ps.executeQuery();
		        int id =rs.getInt("id_olimpiada");
		        rs.close();
		        ps.close();
		        con.close();
		        return id;
		        
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 0;
		}
}
