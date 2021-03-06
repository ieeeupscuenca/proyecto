package Modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class FacturaCabecera {

	@Id
	private String numeroFactura;
	@OneToOne
	private Medico medico;
	@OneToOne
	private Paciente paciente;
	private Date Fecha;
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name = "cabeceraId", referencedColumnName = "numeroFactura")
	private List<FacturaDetalle> facturaDetalles;
	private double total;
	private double subtotal;
	private double iva;
	private String estado;
	
	
	public void addFacturaDetalle(FacturaDetalle fd) {
		if(this.facturaDetalles==null) this.facturaDetalles=new ArrayList<>();
		this.facturaDetalles.add(fd);
	}
	
	public String getNumeroFactura() {
		return numeroFactura;
	}
	public void setNumeroFactura(String numeroFactura) {
		this.numeroFactura = numeroFactura;
	}
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	public Date getFecha() {
		return Fecha;
	}
	public void setFecha(Date fecha) {
		Fecha = fecha;
	}
	public List<FacturaDetalle> getFacturaDetalles() {
		return facturaDetalles;
	}
	public void setFacturaDetalles(List<FacturaDetalle> facturaDetalles) {
		this.facturaDetalles = facturaDetalles;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public Medico getMedico() {
		return medico;
	}
	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public double getIva() {
		return iva;
	}

	public void setIva(double iva) {
		this.iva = iva;
	}

	@Override
	public String toString() {
		return "FacturaCabecera [numeroFactura=" + numeroFactura + ", medico=" + medico + ", paciente=" + paciente
				+ ", Fecha=" + Fecha + ", facturaDetalles=" + facturaDetalles + ", total=" + total + "]";
	}
	
	
	
	
	
}
