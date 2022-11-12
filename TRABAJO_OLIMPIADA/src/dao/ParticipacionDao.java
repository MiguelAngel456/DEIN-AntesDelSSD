package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexionBD.ConexionDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Evento;
import model.Olimpiada;
import model.Participacion;

public class ParticipacionDao {
	private ConexionDB conexion;
	//PARA RELLENAR LA TABLA CON LA BASE DE DATOS
	public ObservableList<Participacion> cargarParticipacion()   {
		ObservableList<Participacion> arrParticipacion=FXCollections.observableArrayList();
		String sql;
		 sql = "SELECT * FROM Participacion a, Evento e, Deportista o, Equipo d, Olimpiada ol  WHERE a.id_evento = e.id_evento AND a.id_deportista = o.id_deportista AND a.id_equipo = d.id_equipo AND ol.id_olimpiada = e.id_olimpiada;";
         

		try {
			conexion = new ConexionDB();
	        Connection con;
			con = conexion.getConexion();
			
			PreparedStatement ps = con.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery();
	         while (rs.next()) {
	         	//sacar datos del deportista para la tabla
	         	String nom_Deportista=rs.getString("o.nombre");
	         	String sexo_Deportista=rs.getString("o.sexo");
	         	String peso_Deportista=rs.getString("o.peso");
	         	String altura_Deportista=rs.getString("o.altura");
	         	
	         	//sacar datos del equipo para la tabla
	         	String nom_Equipo=rs.getString("d.nombre");

	         	
	         	//sacar datos del evento para la tabla
	         	String nom_evento=rs.getString("e.nombre")+","+rs.getString("ol.nombre");
	         	
	         	//sacar datos de la participacion para la tabla
	         	int edad_Participacion=rs.getInt("a.edad");
	         	String medalla_Participacion=rs.getString("a.medalla");
	         	
	         	//crear el Participacion
	         	Participacion par=new Participacion(nom_Deportista, sexo_Deportista, peso_Deportista, altura_Deportista, edad_Participacion, medalla_Participacion, nom_Equipo, nom_evento);
	         	arrParticipacion.add(par);
	         }
	         rs.close();
             ps.close();
         
        
         
         con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
         
         
		
		return arrParticipacion;
	}
	public ObservableList<Participacion> FiltrarParticipacion(boolean inv, boolean veran)   {
		ObservableList<Participacion> arrParticipacion=FXCollections.observableArrayList();
		String sql;
		 sql = "SELECT * FROM Participacion a, Evento e, Deportista o, Equipo d, Olimpiada f WHERE e.id_olimpiada = f.id_olimpiada AND a.id_evento = e.id_evento AND a.id_deportista = o.id_deportista AND a.id_equipo = d.id_equipo;";
         

		try {
			conexion = new ConexionDB();
	        Connection con;
			con = conexion.getConexion();
			
			PreparedStatement ps = con.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();
	        
	        while (rs.next()) {
	        	String temp_evento=rs.getString("f.temporada");
	        	System.out.println(temp_evento);
	         	//sacar datos del deportista para la tabla
	         	String nom_Deportista=rs.getString("o.nombre");
	         	String sexo_Deportista=rs.getString("o.sexo");
	         	String peso_Deportista=rs.getString("o.peso");
	         	String altura_Deportista=rs.getString("o.altura");
	         	
	         	//sacar datos del equipo para la tabla
	         	String nom_Equipo=rs.getString("d.nombre");

	         	
	         	//sacar datos del evento para la tabla
	         	String nom_evento=rs.getString("e.nombre");
	         	
	         	//sacar datos de la participacion para la tabla
	         	int edad_Participacion=rs.getInt("a.edad");
	         	String medalla_Participacion=rs.getString("a.medalla");

	         	//crear el Participacion
	         	if((inv==true && temp_evento.equals("Winter")) && veran==false) {
	         		Participacion par=new Participacion(nom_Deportista, sexo_Deportista, peso_Deportista, altura_Deportista, edad_Participacion, medalla_Participacion, nom_Equipo, nom_evento);
	         		arrParticipacion.add(par);
	         	}else {
	         		if((veran==true && temp_evento.equals("Summer")) && inv==false) {
		         		Participacion par=new Participacion(nom_Deportista, sexo_Deportista, peso_Deportista, altura_Deportista, edad_Participacion, medalla_Participacion, nom_Equipo, nom_evento);
		         		arrParticipacion.add(par);
		         	}else {
		         		if(veran==true && inv==true) {
		         			Participacion par=new Participacion(nom_Deportista, sexo_Deportista, peso_Deportista, altura_Deportista, edad_Participacion, medalla_Participacion, nom_Equipo, nom_evento);
		         			arrParticipacion.add(par);
		         		}

		         	}
	         	}
	         	

	         	
	         }
	        rs.close();
            ps.close();
        
       
        
        con.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return arrParticipacion;
		
	}
	public boolean anadirParticipacion(int edad,String medalla,int id_deportita,int id_evento, int id_equipo) {
		try {
			conexion = new ConexionDB();
	        Connection con = conexion.getConexion();
			PreparedStatement pst = con.prepareStatement("insert into Participacion (id_deportista, id_evento, id_equipo, edad, medalla) values(?,?,?,?,?)");
			pst.setInt(1, id_deportita);
			pst.setInt(2, id_evento);
			pst.setInt(3, id_equipo);
			pst.setInt(4, edad);
			pst.setString(5, medalla);
			
			
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
	public boolean modificarParticipacion(int edad,String medalla,int id_deportita,int id_evento, int id_equipo, int idEvento_Antiguo, int idDeportista_Antiguo) {
		try {
			conexion = new ConexionDB();
			Connection con = conexion.getConexion();
			PreparedStatement pst = con.prepareStatement("update Participacion set id_deportista=?, id_evento=?, id_equipo=?, edad=?, medalla=? where id_evento='"+idEvento_Antiguo+"' "
														+ "AND id_deportista='"+idDeportista_Antiguo+"';");
			pst.setInt(1, id_deportita);
	    	pst.setInt(2, id_evento);
	    	pst.setInt(3, id_equipo);
	    	pst.setInt(4, edad);
	    	pst.setString(5, medalla);
	    	pst.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		return false;
		
	}
	public boolean eliminarParticipacion(int idEvento_Antiguo, int idDeportista_Antiguo) {
		try {
			conexion = new ConexionDB();
			Connection con = conexion.getConexion();
			System.out.println(idEvento_Antiguo+"---------"+idDeportista_Antiguo);
			PreparedStatement pst = con.prepareStatement("DELETE FROM Participacion WHERE (id_evento = ?) AND (id_deportista = ?);");

			pst.setInt(1, idEvento_Antiguo);
			pst.setInt(2, idDeportista_Antiguo);
	    	pst.execute();
	    	return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		return false;
	}
}
