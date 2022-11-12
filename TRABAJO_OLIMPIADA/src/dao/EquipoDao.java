package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexionBD.ConexionDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Deporte;
import model.Deportista;
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

	
	public 	ObservableList<Equipos> sacarEquipos() {
		ObservableList<Equipos> arr=FXCollections.observableArrayList();
        String sql;
		
        try {
            conexion = new ConexionDB();
            Connection con = conexion.getConexion();
            sql="SELECT * FROM Equipo;";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	//sacar datos 
            	String nom=rs.getString("nombre");
            	String ini=rs.getString("iniciales");

            	
            	//crear el evento
            	Equipos equip=new Equipos(nom, ini);
            	arr.add(equip);
            	
            }
            return arr;
            
        }catch (SQLException e) {
			// TODO: handle exception
		}
        return arr;
	}
	public int bucarId(Equipos d) {
		try {
			conexion = new ConexionDB();
			Connection con = conexion.getConexion();
			//System.out.println(d.getDeporte());
			String sql = "SELECT * FROM Equipo WHERE nombre='"+d.getNombre()+"';";
			 
			PreparedStatement ps = con.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();
	        int id=0;
	        while(rs.next()) {
	        	id=rs.getInt("id_equipo");;
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
	public boolean modificarEquipo(Equipos d, String nomAnt) {
		try {
			conexion = new ConexionDB();
	        Connection con = conexion.getConexion();
	      
	    	PreparedStatement pst;
	    	
			pst = con.prepareStatement("update Equipo set nombre=?, iniciales=? where nombre='"+nomAnt+"'");
	    	pst.setString(1, d.getNombre());
	    	pst.setString(2, d.getIniciales());
	    	pst.execute();
	    	con.close();
	    	pst.close();
	    	return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
		return false;
	}
	public boolean eliminarEquipo(int idEquipo) {
		try {
			conexion = new ConexionDB();
	        Connection con = conexion.getConexion();
	    	PreparedStatement pst;

	    	String sql = "SELECT * FROM Participacion WHERE id_equipo='"+idEquipo+"' ;";
            pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
            	pst = con.prepareStatement("DELETE FROM Participacion WHERE (id_equipo = '"+idEquipo+"');");
    	    	pst.execute();
	    	}
            //*****************************************************************
	    	sql = "DELETE FROM Equipo WHERE (id_equipo = '"+idEquipo+"');";
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
