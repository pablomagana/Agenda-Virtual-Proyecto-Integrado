package vista;
/*
 * Esta Clase de tipo Frame mostrará VistaCalificacionesAlumno, VistaCalificacionesProfesor, VistaMensajeAlumno, VistaTareas, VistaEventos y  VistaMisDatos.
 * @author: Adrián Rodríguez Yáñez y Lluís Ferrer Muñoz
 */

import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import modelo.DatosUsuario;

public class FrameMediano extends JFrame {
	
	private VistaMensajeProfesor msgProf;
	
	/*Frame mediano que contendrá los panels de las siguientes vistas= VistaMensajeProfesor, VistaMensajeAlumno, VistaNuevoMensaje, VistaLeerMensaje
	 * VistaCalificacionesAlumno, VistaCalificacionesProfesor, VistaTareas y VistaEventos.
	 */
	public FrameMediano() {
		
		setBounds(100, 100, 800, 500);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(VistaAlumno.class.getResource("/images/icono.png")));
		
		setTitle("Agenda Virtual");
		
		}
	
	//Metodos que cargan un nuevo panel y le pasa el propio frame mediano que habremos creado y los datos del usuario que esta ahora conectado.
	public void cambiaPanelCalificacionesAlum(FrameMediano medi,DatosUsuario datos){
		setContentPane(new VistaCalificacionesAlumno(medi,datos));
	}
	
	public void cambiaPanelCalificacionesProfe(FrameMediano medi,DatosUsuario datos){
		setContentPane(new VistaCalificacionesProfesor(medi,datos));
	}
	
	public void cambiaPanelMensajeAlumno(FrameMediano medi,DatosUsuario datos){
		setContentPane(new VistaMensajeAlumno(medi,datos));
	}
	
	public void cambiaPanelTareas(FrameMediano medi,DatosUsuario datos){
		setContentPane(new VistaTareas(medi,datos));
	}
	
	public void cambiaPanelVistaEventos(FrameMediano medi,DatosUsuario datos){
		setContentPane(new VistaEventos(medi,datos));
	}
	
	public void cambiaVistalMisDatos(FrameMediano medi,DatosUsuario datos){
		setContentPane(new VistaMisDatos(medi,datos));
	}
	//En este metodo guardamos la VistaMensajeProfesor ya que si cambiamos a la VistaNuevoMensage debemos eliminar esta para que se vea la otra
	public void cambiaPanelMensajeProfesor(FrameMediano medi,DatosUsuario datos){
		setContentPane(msgProf=new VistaMensajeProfesor(medi,datos));
		this.setVisible(true);
	}
	//En esta Vista como cambiamos de una a otra sin quitar el frame necesitamos eliminar la VistaMensajeProfesor que habremos guardado en una variable
	public void cambiaPanelNuevoMensaje(FrameMediano medi,DatosUsuario datos){
		this.remove(msgProf);
		setContentPane(new VistaNuevoMensaje(medi,datos));
		this.setVisible(true);
	}
	
	
	
}
