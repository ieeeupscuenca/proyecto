package Modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Administrador {

	@Id
	@SequenceGenerator(name="seq_id", sequenceName="seq_id", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_id")
	private int codigo;
	private String cedula;
	private String nombre;
	private String apellido;
	private String email;
	private String clave;
	@OneToOne
	private Rol rol;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clabe) {
		this.clave = clabe;
	}
	public Rol getRol() {
		return rol;
	}
	public void setRol(Rol rol) {
		this.rol = rol;
	}
	@Override
	public String toString() {
		return "Administrador [codigo=" + codigo + ", cedula=" + cedula + ", nombre=" + nombre + ", apellido="
				+ apellido + ", email=" + email + ", clave=" + clave + ", rol=" + rol + "]";
	}
	
	
}
