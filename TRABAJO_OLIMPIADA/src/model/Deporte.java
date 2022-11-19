package model;

import java.util.Objects;

public class Deporte {

	private String deporte;
	private int id;
	
	public Deporte(int id,String deporte) {
		this.deporte = deporte;
		this.id=id;
	}
	
	public int getId() {
		return id;
	}

	public String getDeporte() {
		return deporte;
	}
	@Override
	public String toString() {
		return deporte;
	}
	@Override
	public int hashCode() {
		return Objects.hash(deporte);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Deporte other = (Deporte) obj;
		return Objects.equals(deporte, other.deporte);
	}
	
	
}
