package vista;
/*
 * Esta Clase de tipo Frame mostrará VistaLeerMensaje, VistaTareaAmpliada y VistaEventoAmpliado.
 * @author: Adrián Rodríguez Yáñez
 */

import java.awt.Toolkit;

import javax.swing.JFrame;

import modelo.DatosMensaje;

import modelo.DatosUsuario;
import javax.swing.JLabel;


public class FrameIntermedio extends JFrame {


	public FrameIntermedio() {
		
		setBounds(100, 100, 611, 371);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(VistaAlumno.class.getResource("/images/icono.png")));
		
		setTitle("Agenda Virtual");
		getContentPane().setLayout(null);
	}
	
	//Metodos que cargan un nuevo panel y le pasa el propio frame intermedio que habremos creado y los datos del usuario que esta ahora conectado.
	
	public void cambiaPanelLeerMensaje(String identificador, FrameIntermedio intermedio,DatosMensaje datosMensajes){
		setContentPane(new VistaLeerMensaje(identificador, intermedio, datosMensajes));
	}
	
	public void cambiaPanelVistaTarea(String identificador, FrameIntermedio intermedio){
		setContentPane(new VistaTareaAmpliada(identificador, intermedio));
	}
	
	public void cambiaPanelVistaEvento(String identificador, FrameIntermedio intermedio){
		setContentPane(new VistaEventoAmpliado(identificador, intermedio));
	}
}
