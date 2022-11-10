package model;

import java.util.Objects;

public class Equipos {
	String nombre,iniciales;

	public Equipos(String nombre, String iniciales) {
		this.nombre = nombre;
		this.iniciales = iniciales;
	}
	public Equipos(String nombre) {
		this.nombre = nombre;
	}
	public String toString() {
		return nombre;
	}
	public String getNombre() {
		return nombre;
	}

	public String getIniciales() {
		return iniciales;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Equipos other = (Equipos) obj;
		return Objects.equals(nombre, other.nombre);
	}
	
}
