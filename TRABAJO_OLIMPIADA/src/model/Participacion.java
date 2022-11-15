package model;

import java.util.Objects;

public class Participacion {

	private String medalla;
	private int edad;

	
	Deportista dep;
	
	Equipos eq;
	
	Evento ev;


	public Participacion(Deportista dep, int edad, String medalla, Equipos eq, Evento ev) {
		
		this.dep=dep;
		this.ev=ev;
		this.edad = edad;
		this.medalla = medalla;

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

	public int getEdad() {
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
