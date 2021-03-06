package Vista;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.swing.JOptionPane;

import Exception.ExcepcionCita;
import Modelo.Cita;
import Modelo.Historial;
import Modelo.Medico;
import Modelo.Paciente;
import Negocio.GestionCitaLocal;
import Negocio.GestionHistorialLocal;
import Negocio.GestionMedicoLocal;
import Negocio.GestionPacienteLocal;

@ManagedBean
@ViewScoped
public class GestionIndexPacienteBean {
	

	@Inject
	private GestionMedicoLocal gml;
	
	@Inject
	private GestionCitaLocal gcl;
	
	private String fecha;
	private String hora;
	
	@Inject
	private GestionPacienteLocal gpl;
	@Inject
	private GestionHistorialLocal ghl;
	
	private String idMedico;
	
	private Cita cita;
	
	
	private List<Medico> medicos;
	private List<Cita> citas;
	private List<Historial> historiales;
	
	private boolean banderaCita = false;
	
	@PostConstruct
	public void init() {
		this.cita = new Cita();
		this.listarMedico();
		this.citas= new ArrayList<Cita>();
		
	}
	
	
	
	
	
	
	public String getFecha() {
		return fecha;
	}






	public void setFecha(String fecha) {
		this.fecha = fecha;
	}






	public String getHora() {
		return hora;
	}






	public void setHora(String hora) {
		this.hora = hora;
	}






	public Cita getCita() {
		return cita;
	}






	public void setCita(Cita cita) {
		this.cita = cita;
	}






	public String getIdMedico() {
		return idMedico;
	}




	public void setIdMedico(String idMedico) {
		this.idMedico = idMedico;
	}




	public GestionHistorialLocal getGhl() {
		return ghl;
	}



	public void setGhl(GestionHistorialLocal ghl) {
		this.ghl = ghl;
	}



	public List<Historial> getHistoriales() {
		return historiales;
	}



	public void setHistoriales(List<Historial> historiales) {
		this.historiales = historiales;
	}



	public List<Medico> getMedicos() {
		return medicos;
	}
	public void setMedicos(List<Medico> medicos) {
		this.medicos = medicos;
	}
	public GestionCitaLocal getGcl() {
		return gcl;
	}
	public void setGcl(GestionCitaLocal gcl) {
		this.gcl = gcl;
	}
	
	
	
	public GestionMedicoLocal getGml() {
		return gml;
	}

	public void setGml(GestionMedicoLocal gml) {
		this.gml = gml;
	}

	public GestionPacienteLocal getGpl() {
		return gpl;
	}

	public void setGpl(GestionPacienteLocal gpl) {
		this.gpl = gpl;
	}

	
	
	public List<Cita> getCitas() {
		return citas;
	}

	public void setCitas(List<Cita> citas) {
		this.citas = citas;
	}

	public void listarMedico() {
		this.medicos= this.gml.getMedicos();
	}
	
	
	
	
	public boolean isBanderaCita() {
		return banderaCita;
	}






	public void setBanderaCita(boolean banderaCita) {
		this.banderaCita = banderaCita;
	}






	public String guardarCita(String user) {
		ExcepcionCita exp = new ExcepcionCita();
		try {
			SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
			System.out.println(user);
			cita.setCodigo(this.gcl.getCitas().size()+1);
			cita.setPaciente(this.buscarPacientexEmail(user));
			cita.setEstado("Pendiente");
			cita.setHora(hora);
			Date fecha=formato.parse(this.fecha);
			cita.setFecha(fecha);
		}catch(Exception e) {
			e.getMessage();
		}
		
		try {
			banderaCita=exp.verificarCita(this.gcl.obtenerCitasHorario(cita));
			System.out.println(banderaCita);
			if(banderaCita==true) {
				throw new ExcepcionCita();
			}else {
				this.gcl.insertar(cita);
				return "misCitas?faces-redirect=true";
			}
		}catch (ExcepcionCita e){
			e.getMessage();
			return null;
		}
	}
	
	
	
	public Paciente buscarPacientexEmail(String user) {
		List<Paciente> pacientes = new ArrayList<Paciente>();
		pacientes = this.gpl.getPacientes();
		for(Paciente p: pacientes) {
			if(p.getEmail().equals(user)) {
				return p;
			}
		}
		return null;
	}
	
	public String eliminar(int codigo) {
		
		try {
			this.gcl.borrar(codigo);
			System.out.println("Cita eliminado");
			return "misCitas?faces-redirect=true";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error al eliminar");
			e.printStackTrace();			
		}		
		return null;
	}
	
	public List<Cita> obtenerCitas(String email){
		System.out.println(email);
		List<Cita> aux= new ArrayList<Cita>();
		this.citas=this.gcl.getCitas();
		for(Cita ci: citas) {
			if(ci.getPaciente().getEmail().equals(email)) {
				aux.add(ci);
			}
		}
		return aux;
	}
	
	public List<Historial> obtenerHistoriales(String email){
		System.out.println(email);
		List<Historial> aux= new ArrayList<Historial>();
		this.historiales= this.ghl.getHistoriales();
		for(Historial ci: historiales) {
			if(ci.getPaciente().getEmail().equals(email)) {
				aux.add(ci);
			}
		}
		return aux;
	}
	
	public String MedicoPorId(Medico medico) {
		return "crearCitaPorMedico?faces-redirect=true&id="+medico.getCodigo();
	}
	
	public void cargarMedico() {
		this.cita = new Cita();
		this.cita.setMedico(this.gml.leer(Integer.parseInt(idMedico)));
	}


}
