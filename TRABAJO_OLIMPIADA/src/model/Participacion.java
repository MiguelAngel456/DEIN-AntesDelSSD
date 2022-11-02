package model;

public class Participacion {
	private String nomDeportista,sexo,peso,altura;
	
	private String edad, medalla;
	
	private String nomEvento;
	
	public Participacion(String nomDeportista, String sexo, String peso, String altura, String edad, String medalla, String nomEvento) {
		
		this.nomDeportista = nomDeportista;
		this.sexo = sexo;
		this.peso = peso;
		this.altura = altura;
		this.edad = edad;
		this.medalla = medalla;
		this.nomEvento = nomEvento;
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


	
}
