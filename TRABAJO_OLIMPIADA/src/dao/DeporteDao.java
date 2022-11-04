package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexionBD.ConexionDB;
import model.Deporte;

public class DeporteDao {
	private ConexionDB conexion;
	
	//PARA AÑDIR FILAS A LA TABLA DEPORTE
	public boolean anadirDeporte(Deporte d) {
		try {
			conexion = new ConexionDB();
	        Connection con = conexion.getConexion();
            
			PreparedStatement pst = con.prepareStatement("insert into Evento (nombre) values(?)");
			
			pst.setString(1, d.getDeporte());

			pst.execute();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return false;
	}
	
	
	//COMPRUEBA SI YA ESTA EL DEPORTE
	public boolean comprobarDeporte(Deporte d) {
		try {
			conexion = new ConexionDB();
			Connection con = conexion.getConexion();
			//System.out.println(d.getDeporte());
			String sql = "SELECT * FROM Deporte WHERE nombre = '"+d.getDeporte()+"';";
			 
			PreparedStatement ps = con.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();
	        boolean existe=rs.next();
	        //CERRAR IMPORTANTE 
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
	//SACAR EL ID DEL DEPORTE BUSCADO
	public int sacarId(Deporte d) {
		try {
			conexion = new ConexionDB();
			Connection con = conexion.getConexion();
			//System.out.println(d.getDeporte());
			String sql = "SELECT * FROM Deporte WHERE nombre = '"+d.getDeporte()+"';";
			 
			PreparedStatement ps = con.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();
	        int id=rs.getInt("id_deporte");
	        //CERRAR IMPORTANTE
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
