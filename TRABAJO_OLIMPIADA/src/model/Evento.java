package model;

import java.util.Objects;

public class Evento{
	private String nom_Evento;
	

	
	private Olimpiada ol;
	
	private Deporte dep;
	
	//CONTRUCTOR PARA LOS EVENTOS
	public Evento(String nom_Evento,Olimpiada ol, Deporte dep) {
		super();
		this.nom_Evento=nom_Evento;
		
		this.ol=ol;
		
		this.dep=dep;
	}
	public Evento(String nom_Evento, Olimpiada ol) {
		this.nom_Evento=nom_Evento;
		this.ol=ol;
	}
	public String toString() {
		return nom_Evento+", año: "+ol.getAnio();
	}
	public String getNom_Evento() {
		return nom_Evento;
	}
	//****************************************
	//Geters de Olimpiadas
	public String getOlNombre() {
		return ol.getNombre();
	}
	public int getOlAnio() {
		return ol.getAnio();
	}
	public String getOlTemporada() {
		return ol.getTemporada();
	}
	public String getOlCiudad() {
		return ol.getCiudad();
	}
	//****************************************
	//Geters de Deporte
	public String getDepNombre() {
		return dep.getDeporte();
	}
	
	

	
	
	
}
