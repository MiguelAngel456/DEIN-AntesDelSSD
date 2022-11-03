package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexionBD.ConexionDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Evento;
import model.Participacion;

public class ParticipacionDao {
	private ConexionDB conexion;
	//PARA RELLENAR LA TABLA CON LA BASE DE DATOS
	public ObservableList<Participacion> cargarParticipacion()   {
		ObservableList<Participacion> arrParticipacion=FXCollections.observableArrayList();
		String sql;
		 sql = "SELECT * FROM Participacion a, Evento e, Deportista o, Equipo d  WHERE a.id_evento = e.id_evento AND a.id_deportista = o.id_deportista AND a.id_equipo = d.id_equipo;";
         

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
	         	String nom_evento=rs.getString("e.nombre");
	         	
	         	//sacar datos de la participacion para la tabla
	         	String edad_Participacion=rs.getString("a.edad");
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
	         	String edad_Participacion=rs.getString("a.edad");
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
	
	
	
}
