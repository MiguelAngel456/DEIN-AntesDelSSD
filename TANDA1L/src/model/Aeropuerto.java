package model;

public class Aeropuerto {
	private String nombre,pais,ciudad,calle,año;
	private int id,num,capacidad;
	
	public Aeropuerto(int id,String nombre,String Pais,String Ciudad,String Calle,int Numero, String Año,int capacidad,int num) {
		// TODO Auto-generated constructor stub
		this.id=id;
		this.nombre=nombre;
		this.pais=Pais;
		this.ciudad=Ciudad;
		this.calle=Calle;
		this.num=Numero;
		this.año=Año;
		this.capacidad=capacidad;
		this.num=num;
		
	}
}
