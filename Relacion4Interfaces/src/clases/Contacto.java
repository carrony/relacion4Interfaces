package clases;

import java.util.Objects;

public class Contacto {

	private String nombre;
	private String telefono;
	
	public Contacto() {
		this.nombre="";
		this.telefono="";
	}

	public Contacto(String nombre, String telefono) {
		super();
		this.nombre = nombre;
		this.telefono = telefono;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return "Nombre:" + nombre + "\ntelefono:" + telefono;
	}

	@Override
	public int hashCode() {
		return Objects.hash(telefono);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contacto other = (Contacto) obj;
		return Objects.equals(telefono, other.telefono);
	}
	
	
	
}
