package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexionBD.ConexionDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
				con.close();
				pst.close();
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
			String sql = "SELECT * FROM Olimpiada WHERE nombre = ?;";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ol.getId());
			
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
		public 	ObservableList<Olimpiada> sacarOlimpiada() {
			ObservableList<Olimpiada> arr=FXCollections.observableArrayList();
	        String sql;
			
	        try {
	            conexion = new ConexionDB();
	            Connection con = conexion.getConexion();
	            sql="SELECT * FROM Olimpiada;";
	            PreparedStatement ps = con.prepareStatement(sql);
	            ResultSet rs = ps.executeQuery();
	            while (rs.next()) {
	            	//sacar datos 
	            	
	            	int id=rs.getInt("id_olimpiada");
	            	String nom=rs.getString("nombre");
	            	int anio=rs.getInt("anio");
	            	String temp=rs.getString("temporada");
	            	String ciudad=rs.getString("ciudad");
	            	//crear
	            	Olimpiada ol=new Olimpiada(id,nom, anio, temp, ciudad);
	            	arr.add(ol);
	            	
	            }
	            ps.close();
	            rs.close();
	            return arr;
	            
	        }catch (SQLException e) {
				// TODO: handle exception
			}
	        return arr;
		}
		public boolean modificarOlimpiada(Olimpiada o) {
			try {
				conexion = new ConexionDB();
		        Connection con = conexion.getConexion();
		      
		    	PreparedStatement pst;
		    	//UPDATE `olimpiadas`.`Olimpiada` SET `nombre` = '2022 2', `temporada` = 'Summer' WHERE (`id_olimpiada` = '10');

				pst = con.prepareStatement("update Olimpiada SET nombre = ?, anio = ?, temporada = ?, ciudad = ? WHERE (id_olimpiada = ?);");
				System.out.println(o.getNombre());
				pst.setString(1,o.getNombre());
		    	pst.setInt(2,o.getAnio());
		    	pst.setString(3,o.getTemporada());
		    	pst.setString(4,o.getCiudad());
		    	pst.setInt(5,o.getId());
		    	pst.execute();
		    	con.close();
		    	pst.close();
		    	return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}

			
			return false;
		}
		public boolean eliminarOlimpiada(Olimpiada ol) throws SQLException {
			boolean bien=false; 
			
				conexion = new ConexionDB();
		        Connection con = conexion.getConexion();
		        EventoDao evD=new EventoDao();
		    	PreparedStatement pst;

		    	pst = con.prepareStatement("SELECT * FROM Evento WHERE id_olimpiada= ? ;");
		    	pst.setInt(1, ol.getId());
	            ResultSet rs = pst.executeQuery();
	            while(rs.next()) {
	            	
	            	int id_evento=rs.getInt("id_evento");
	            	
	            	pst = con.prepareStatement("DELETE FROM Participacion WHERE (id_evento = ?);");
	    			pst.setInt(1, id_evento);
	    	    	pst.execute();
	            	
	            	

		    	}
	            //*****************************************************************
	    		pst = con.prepareStatement("DELETE FROM Evento WHERE (id_olimpiada = ?);");
	    		pst.setInt(1, ol.getId());
		    	pst.execute();
		    	//*********************************************
		    	pst = con.prepareStatement("DELETE FROM Olimpiada WHERE (id_olimpiada = ?);");
		    	pst.setInt(1, ol.getId());
	            pst.execute();
	            
		    	con.close();
		    	pst.close();
		    	bien=true;

			return bien ;
		}
		public int ultimoId() {
			int id=0;
			try {
				conexion = new ConexionDB();
				Connection con = conexion.getConexion();
				
				String sql = "SELECT max(id_olimpiada) FROM olimpiadas.Olimpiada;";
		        PreparedStatement ps = con.prepareStatement(sql);
		        ResultSet rs = ps.executeQuery();
		        while(rs.next()) {
		        	 id=rs.getInt("max(id_olimpiada)");
		        }
		       
		        rs.close();
		        ps.close();
		        con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return id;
		}
}
