package model;

import java.util.Objects;

public class Deportista {
	String nombre,sexo,peso,altura;

	public Deportista(String nombre, String sexo, String peso, String altura) {
		this.nombre = nombre;
		this.sexo = sexo;
		this.peso = peso;
		this.altura = altura;
	}

	public String getNombre() {
		return nombre;
	}

	public String getSexo() {
		return sexo;
	}

	public String getPeso() {
		return peso;
	}

	public String getAltura() {
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
