package model;

public class Aeropuerto {
	private String nombre,pais,ciudad,calle;
	private int id,num,capacidad,año;
	
	public Aeropuerto(int id,String nombre,String pais,String ciudad,String calle,int num, int año,int capacidad) {
		// TODO Auto-generated constructor stub
		this.id=id;
		this.nombre=nombre;
		this.pais=pais;
		this.ciudad=ciudad;
		this.calle=calle;
		this.num=num;
		this.año=año;
		this.capacidad=capacidad;
		
	}
}
