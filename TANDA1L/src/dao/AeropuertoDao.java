package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexionBD.ConexionDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Aeropuerto;
import model.AeropuertoPrivado;
import model.AeropuertoPublico;

public class AeropuertoDao {
	 private ConexionDB conexion;
	
	public ObservableList<Aeropuerto> cargarAeroPuerto(	String p) throws SQLException  {
		Aeropuerto a;
		
        ObservableList<Aeropuerto> listP= FXCollections.observableArrayList();
        conexion = new ConexionDB();
        Connection con = conexion.getConexion();
        String sql = "select * from aeropuertos";
        PreparedStatement ps = con.prepareStatement(sql);
        //ps.setInt(1, 200);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            //TABLA AEROPUERTOS
    		String nombre=rs.getString("nombre");
    		String pais=rs.getString("pais");
    		int año=rs.getInt("anio_inauguracion");
    		int capacidad=rs.getInt("capacidad");
    		
    		//TABLA CALLES
    		String idcalle=rs.getString("id_direccion");
    		String sqlCalle = "select * from aeropuertos_privados where id='"+idcalle+"'";
	        PreparedStatement psCalle = con.prepareStatement(sqlCalle);
	        ResultSet rsCalle= psCalle.executeQuery();
    		String ciudad=rsCalle.getString("ciudad");
    		String calle=rsCalle.getString("calle");
    		int num=rsCalle.getInt("numero");
    		
    		//TABLA AEROPUERTOS PRIVADOS
    		if(p.equals("privado")) {
    			 String sqLPriv = "select * from aeropuertos_privados where id_aeropuerto='"+id+"'";
    		        PreparedStatement psPriv = con.prepareStatement(sqLPriv);
    		        ResultSet rsPriv = psPriv.executeQuery();
    			int Nsocios=rsPriv.getInt("numero_socios");
    			a=new AeropuertoPrivado(id, nombre, pais, ciudad, calle, num, año, capacidad, Nsocios);
    			listP.add(a);
    		}
    		//TABLA AEROPUERTOS PUBLICOS
    		if(p.equals("publico")) {
   			 	String sqlPub = "select * from aeropuertos_publicos where id_aeropuerto='"+id+"'";
   		        PreparedStatement psPub = con.prepareStatement(sqlPub);
   		        ResultSet rsPub = psPub.executeQuery();
   		        double finan=rsPub.getDouble("financiacion");
   				int trabajadores=rsPub.getInt("num_trabajadores");
   				a=new AeropuertoPublico(id, nombre, pais, ciudad, calle, num, año, capacidad, finan, trabajadores);
   				listP.add(a);
    		}
            
          
//
        }
        rs.close();
        ps.close();
        con.close();
        return listP;		
    }
}
