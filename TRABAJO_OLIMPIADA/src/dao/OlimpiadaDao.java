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
		public int sacarId(Olimpiada nom) {
			int id=0;
			try {
				conexion = new ConexionDB();
				Connection con = conexion.getConexion();
				System.out.println(nom.getNombre());
				String sql = "SELECT * FROM Olimpiada WHERE nombre='"+nom.getNombre()+"';";
				PreparedStatement ps = con.prepareStatement(sql);
		        ResultSet rs = ps.executeQuery();
		        while(rs.next()) {
		        	 id =rs.getInt("id_olimpiada");
		        }
		       
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
	            	
	            	
	            	String nom=rs.getString("nombre");
	            	int anio=rs.getInt("anio");
	            	String temp=rs.getString("temporada");
	            	String ciudad=rs.getString("ciudad");
	            	//crear
	            	Olimpiada ol=new Olimpiada(nom, anio, temp, ciudad);
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
		public boolean modificarOlimpiada(Olimpiada o, String nomAnt) {
			try {
				conexion = new ConexionDB();
		        Connection con = conexion.getConexion();
		      
		    	PreparedStatement pst;
		    	
				pst = con.prepareStatement("update Olimpiada set nombre=?, anio=?, temporada=?, ciudad=? where nombre='"+nomAnt+"'");
		    	pst.setString(1,o.getNombre());
		    	pst.setInt(2,o.getAnio());
		    	pst.setString(3,o.getTemporada());
		    	pst.setString(4,o.getCiudad());
		    	pst.execute();
		    	con.close();
		    	pst.close();
		    	return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}

			
			return false;
		}
		public boolean eliminarOlimpiada(int idOlimpiada) {
			try {
				conexion = new ConexionDB();
		        Connection con = conexion.getConexion();
		        EventoDao evD=new EventoDao();
		    	PreparedStatement pst;
		    	
		    	
		    	
		    	
		    	
		    	String sql = "SELECT * FROM Evento WHERE id_olimpiada='"+idOlimpiada+"' ;";
	            pst = con.prepareStatement(sql);
	            ResultSet rs = pst.executeQuery();
	            while(rs.next()) {
	            	String nomEv=rs.getString("nombre");
	            	int idOl=rs.getInt("id_Olimpiada");
	            	int idEventoActual=evD.sacarId(nomEv, idOl);
	            	
	            	pst = con.prepareStatement("DELETE FROM Participacion WHERE (id_evento = '"+idEventoActual+"');");
	    			//pst.setInt(1, idEvento);
	    	    	pst.execute();
	            	
	            	

		    	}
	            //*****************************************************************
	    		pst = con.prepareStatement("DELETE FROM Evento WHERE (id_olimpiada = '"+idOlimpiada+"');");
				//pst.setInt(1, idEvento);
		    	pst.execute();
		    	//*********************************************
		    	sql = "DELETE FROM Olimpiada WHERE (id_olimpiada = '"+idOlimpiada+"');";
	            pst = con.prepareStatement(sql);
	            pst.execute();
	            
		    	con.close();
		    	pst.close();
		    	return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}

			
			return false;
		}
}
