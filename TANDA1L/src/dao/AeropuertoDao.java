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
        ObservableList<Aeropuerto> vuelos = FXCollections.observableArrayList();
        String sql;
        
        try {
            conexion = new ConexionDB();
            Connection con = conexion.getConexion();
            
            
            //AEROPUERTOS PRIVADOS
            if (p.equals("privado")) {
                sql = "SELECT * FROM aeropuertos a, direcciones d, aeropuertos_privados p WHERE id_direccion = d.id AND p.id_aeropuerto = a.id;";
            }
            //AEROPUERTOS PUBLICOS
            else {
                sql = "SELECT * FROM aeropuertos a, direcciones d, aeropuertos_publicos p WHERE id_direccion = d.id AND p.id_aeropuerto = a.id;";
            }
            
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (p.equals("privado")) {
                	int id=rs.getInt("a.id");
                	String nom= rs.getString("nombre");
                	String pais=rs.getString("pais");
                	String  ciudad=rs.getString("ciudad");
                	String calle=rs.getString("calle");
                	int num= rs.getInt("numero");
                	int anio=rs.getInt("anio_inauguracion");
                	int capacidad=rs.getInt("capacidad");
                	System.out.println("aaaaaa");
                	int numSocios= rs.getInt("numero_socios");
                	
                    AeropuertoPrivado v = new AeropuertoPrivado(id,nom, pais, ciudad, calle, num, anio, capacidad, numSocios);
                    vuelos.add(v);
                }else {
                	int id=rs.getInt("a.id");
                	String nom= rs.getString("nombre");
                	String pais=rs.getString("pais");
                	String  ciudad=rs.getString("ciudad");
                	String calle=rs.getString("calle");
                	int num= rs.getInt("numero");
                	int anio=rs.getInt("anio_inauguracion");
                	int capacidad=rs.getInt("capacidad");
                	System.out.println("Vvvvvvv");
                	double finan=rs.getDouble("financiacion");
                	int traba=rs.getInt("num_trabajadores");
                    AeropuertoPublico v = new AeropuertoPublico(id,nom, pais, ciudad, calle, num, anio, capacidad,finan,traba);
                    vuelos.add(v);
                }
            }
            rs.close();
            ps.close();
            
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vuelos;
    }

}
