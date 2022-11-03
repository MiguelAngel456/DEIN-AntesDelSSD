package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexionBD.ConexionDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Evento;

public class EventoDao {
	private ConexionDB conexion;
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
                	Evento event=new Evento(nom_Evento, nom_Olimpiada, anio, temporada, ciudad, nom_deporte);
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
}
