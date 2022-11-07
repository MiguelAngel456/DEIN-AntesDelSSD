package model;

import java.util.Objects;

public class Deportista {
	String nombre,sexo;
	int peso,altura;

	public Deportista(String nombre, String sexo, int peso, int altura) {
		this.nombre = nombre;
		this.sexo = sexo;
		this.peso = peso;
		this.altura = altura;
	}

	public String toString() {
		return nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public String getSexo() {
		return sexo;
	}

	public int getPeso() {
		return peso;
	}

	public int getAltura() {
		return altura;
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
		Deportista other = (Deportista) obj;
		return Objects.equals(nombre, other.nombre);
	}

	
	
}
