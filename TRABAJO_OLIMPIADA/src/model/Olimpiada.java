package model;

import java.util.Objects;

public class Olimpiada {
	String nombre,temporada,ciudad;
	int anio;
	
	
	public Olimpiada(String nombre, int anio, String temporada, String ciudad) {
		this.nombre = nombre;
		this.anio = anio;
		this.temporada = temporada;
		this.ciudad = ciudad;
	}
	
	public String getNombre() {
		return nombre;
	}

	public int getAnio() {
		return anio;
	}

	public String getTemporada() {
		return temporada;
	}

	public String getCiudad() {
		return ciudad;
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
		Olimpiada other = (Olimpiada) obj;
		return Objects.equals(nombre, other.nombre);
	}
	
	
}
