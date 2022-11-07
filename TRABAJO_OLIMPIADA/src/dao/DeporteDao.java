package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexionBD.ConexionDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Deporte;
import model.Evento;

public class DeporteDao {
	private ConexionDB conexion;
	
	//PARA AÑDIR FILAS A LA TABLA DEPORTE
	public boolean anadirDeporte(Deporte d) {
		try {
			conexion = new ConexionDB();
	        Connection con = conexion.getConexion();
			PreparedStatement pst = con.prepareStatement("insert into Deporte (nombre) values(?)");
			
			pst.setString(1, d.getDeporte());

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
			String sql = "SELECT * FROM Deporte WHERE nombre='"+d.getDeporte()+"';";
			 
			PreparedStatement ps = con.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();
	        System.out.println(rs.next());
	        int id=rs.getInt("id_deporte");
	        //CERRAR IMPORTANTE
	        rs.close();
	        ps.close();
	        con.close();
	        System.out.println(id);
	        return id;
	       
	        
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	public 	ObservableList<Deporte> sacarDeportes() {
		ObservableList<Deporte> arr=FXCollections.observableArrayList();
        String sql;
		
        try {
            conexion = new ConexionDB();
            Connection con = conexion.getConexion();
            sql="SELECT * FROM Deporte;";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	//sacar datos del evento para la tabla
            	String nom=rs.getString("nombre");
            	
            	//crear el evento
            	Deporte dep=new Deporte(nom);
            	arr.add(dep);
            	
            }
            return arr;
            
        }catch (SQLException e) {
			// TODO: handle exception
		}
        return arr;
	}
}
