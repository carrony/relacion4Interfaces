package clases;

import java.util.Objects;

public class Profesor {

	private String dni;
	private String nombre;
	private double salario;
	private boolean fijo;
	
	public Profesor() {
		this.dni="";
		this.nombre="";
		this.salario=0;
		this.fijo=false;
	}

	public Profesor(String dni, String nombre, double salario, 
			boolean fijo) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.salario = salario;
		this.fijo = fijo;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public boolean isFijo() {
		return fijo;
	}

	public void setFijo(boolean fijo) {
		this.fijo = fijo;
	}

	@Override
	public String toString() {
		return "Profesor [dni=" + dni + ", nombre=" + nombre 
				+ ", salario=" + salario + ", fijo=" + fijo + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(dni);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Profesor other = (Profesor) obj;
		return Objects.equals(dni, other.dni);
	}
	
	
	
	
}
