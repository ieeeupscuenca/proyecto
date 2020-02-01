package Negocio;

import java.util.List;

import javax.ejb.Local;

import Modelo.Historial;

@Local
public interface GestionHistorialLocal {

	public void insertar(Historial historial);
	public void actualizar(Historial historial);
	public void borrar(int codigo);
	public Historial leer(int codigo);
	public List<Historial> getHistoriales();
}