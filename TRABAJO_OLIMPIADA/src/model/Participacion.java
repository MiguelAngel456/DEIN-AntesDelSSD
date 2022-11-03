package model;

import java.util.Objects;

public class Participacion {
	private String nomDeportista,sexo,peso,altura;
	
	private String edad, medalla;
	
	private String nomEvento;
	
	private String equipo;
	


	public Participacion(String nomDeportista, String sexo, String peso, String altura, String edad, String medalla, String equipo, String nomEvento) {
		
		this.nomDeportista = nomDeportista;
		this.sexo = sexo;
		this.peso = peso;
		this.altura = altura;
		this.edad = edad;
		this.medalla = medalla;
		this.nomEvento = nomEvento;
		this.equipo=equipo;
	}

	public String getNomDeportista() {
		return nomDeportista;
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

	public String getEdad() {
		return edad;
	}

	public String getMedalla() {
		return medalla;
	}

	public String getNomEvento() {
		return nomEvento;
	}
	public String getEquipo() {
		return equipo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(altura, edad, equipo, medalla, nomDeportista, nomEvento, peso, sexo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Participacion other = (Participacion) obj;
		return Objects.equals(altura, other.altura) && Objects.equals(edad, other.edad)
				&& Objects.equals(equipo, other.equipo) && Objects.equals(medalla, other.medalla)
				&& Objects.equals(nomDeportista, other.nomDeportista) && Objects.equals(nomEvento, other.nomEvento)
				&& Objects.equals(peso, other.peso) && Objects.equals(sexo, other.sexo);
	}


	
}
