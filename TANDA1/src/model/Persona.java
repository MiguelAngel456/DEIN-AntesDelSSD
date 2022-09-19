package model;

public class Persona {
	private String nombre,apellido,edad;
	
	public Persona() {
		this(null, null, null);
	}
	public Persona(String nom,String ape,String edad) {
		this.nombre=nom;
		this.apellido=ape;
		this.edad=edad;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getEdad() {
		return edad;
	}
	public void setEdad(String edad) {
		this.edad = edad;
	}
	
}
