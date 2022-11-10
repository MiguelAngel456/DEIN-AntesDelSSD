package model;

import java.util.Objects;

public class Evento{
	private String nom_Evento;
	
	private String nom_Olimpiada,temporada_Olimpiada,ciudad_Olimpiada;
	
	private String nom_Deporte;
	
	private int anio_Olimpiada;
	
	//CONTRUCTOR PARA LOS EVENTOS
	public Evento(String nom_Evento,String nom_Olimpiada,int anio_Olimpiada,String temporada_Olimpiada,String ciudad_Olimpiada,String nom_Deporte) {
		super();
		this.nom_Evento=nom_Evento;
		
		this.nom_Olimpiada=nom_Olimpiada;
		this.anio_Olimpiada=anio_Olimpiada;
		this.temporada_Olimpiada=temporada_Olimpiada;
		this.ciudad_Olimpiada=ciudad_Olimpiada;
		
		this.nom_Deporte=nom_Deporte;
	}
	public Evento(String nom_Evento,String nom_Olimpiada) {
		this.nom_Evento=nom_Evento;
		this.nom_Olimpiada=nom_Olimpiada;
	}
	public String toString() {
		return nom_Evento+", año: "+anio_Olimpiada;
	}
	public String getNom_Evento() {
		return nom_Evento;
	}

	public String getNom_Olimpiada() {
		return nom_Olimpiada;
	}

	public String getTemporada_Olimpiada() {
		return temporada_Olimpiada;
	}

	public String getCiudad_Olimpiada() {
		return ciudad_Olimpiada;
	}

	public String getNom_Deporte() {
		return nom_Deporte;
	}

	public int getAnio_Olimpiada() {
		return anio_Olimpiada;
	}
	@Override
	public int hashCode() {
		return Objects.hash(nom_Evento, nom_Olimpiada);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Evento other = (Evento) obj;
		return Objects.equals(nom_Evento, other.nom_Evento) && Objects.equals(nom_Olimpiada, other.nom_Olimpiada);
	}
	
}
