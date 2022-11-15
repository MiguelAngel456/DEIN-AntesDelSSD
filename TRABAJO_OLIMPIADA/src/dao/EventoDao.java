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
import model.Olimpiada;

public class EventoDao {
	private ConexionDB conexion;
	private DeporteDao dp;
	//PARA RELLENAR LA TABLA CON LA BASE DE DATOS
	public ObservableList<Evento> cargarEvento()   {
		ObservableList<Evento> arrEvento=FXCollections.observableArrayList();
        String sql;
		
        try {
            conexion = new ConexionDB();
            Connection con = conexion.getConexion();
            

            	 //para buscar en la base de datos todos los eventos
                sql = "SELECT * FROM Evento a, Olimpiada o, Deporte d WHERE a.id_olimpiada = o.id_olimpiada AND a.id_deporte = d.id_deporte;";
                            
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                	//sacar datos del evento para la tabla
                	String nom_Evento=rs.getString("a.nombre");
                	
                	//sacar datos de la Olimpiada para la tabla
                	String nom_Olimpiada=rs.getString("o.nombre");
                	int anio=rs.getInt("o.anio");
                	String temporada=rs.getString("o.temporada");
                	String ciudad=rs.getString("o.ciudad");
                	
                	//sacar datos de deporte para la tabla
                	String nom_deporte=rs.getString("d.nombre");
                	
                	//crear el evento
                	Evento event=new Evento(nom_Evento,new Olimpiada(nom_Olimpiada, anio, temporada, ciudad),new Deporte(nom_deporte));
                	arrEvento.add(event);
                	
                }
                rs.close();
                ps.close();
            
           
            
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
		return arrEvento;
	}
	public boolean anadirEvento(Evento ev,int id_olimpiada,int id_Deporte) {
		
			try {
				conexion = new ConexionDB();
		        Connection con = conexion.getConexion();
	            System.out.println(id_olimpiada);
				PreparedStatement pst = con.prepareStatement("insert into Evento (nombre, id_olimpiada ,id_deporte) values(?,?,?)");
				
				pst.setString(1, ev.getNom_Evento());
				pst.setInt(2, id_olimpiada);
				pst.setInt(3, id_Deporte);

				pst.execute();
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		return false;
		
	}
	public boolean modificarEvento(Evento ev,int id_olimpiada, int id_deporte,int id_antiguo, String nomAntiguo) {
		
		try {
			conexion = new ConexionDB();
	        Connection con = conexion.getConexion();
	        
	    	PreparedStatement pst;
	    	
	    	
			pst = con.prepareStatement("update Evento set nombre=?, id_olimpiada=?, id_deporte=? where nombre='"+nomAntiguo+"' AND id_olimpiada='"+id_antiguo+"'");
	    	pst.setString(1, ev.getNom_Evento());
	    	pst.setInt(2, id_olimpiada);
	    	pst.setInt(3, id_deporte);
	    	pst.execute();
	    	con.close();
	    	pst.close();
	    	return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
	public int sacarId(String nom, String nomol) {
		try {
			conexion = new ConexionDB();
			Connection con = conexion.getConexion();
			String sql = "SELECT * FROM Evento e, Olimpiada o WHERE o.id_olimpiada=e.id_olimpiada AND e.nombre='"+nom+"' AND o.nombre='"+nomol+"';";
			 
			PreparedStatement ps = con.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();
	        int id=0;
	        while(rs.next()) {
	        	id=rs.getInt("id_evento");
	        }
	        
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
	public int sacarId(String nom, int idOl) {
		try {
			conexion = new ConexionDB();
			Connection con = conexion.getConexion();
			String sql = "SELECT * FROM Evento  WHERE nombre='"+nom+"' AND id_olimpiada="+idOl+";";
			 
			PreparedStatement ps = con.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();
	        int id=0;
	        while(rs.next()) {
	        	id=rs.getInt("id_evento");
	        }
	        
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
	public boolean eliminarEvento(int idEvento) {
		try {
			conexion = new ConexionDB();
			Connection con = conexion.getConexion();
			PreparedStatement pst ;
	    	//******************
	    	  String sql = "SELECT * FROM Participacion WHERE id_evento='"+idEvento+"' ;";
              PreparedStatement ps = con.prepareStatement(sql);
              ResultSet rs = ps.executeQuery();
	    	while(rs.next()) {
	    		pst = con.prepareStatement("DELETE FROM Participacion WHERE (id_evento = '"+idEvento+"');");
				//pst.setInt(1, idEvento);
		    	pst.execute();
	    	}
	    	pst= con.prepareStatement("DELETE FROM Evento WHERE (id_evento = "+idEvento+");");

	    	pst.execute();
	    	return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		return false;
	}
}
