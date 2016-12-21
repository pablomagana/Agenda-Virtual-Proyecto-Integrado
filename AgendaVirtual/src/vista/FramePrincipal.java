package vista;
/*
 * Esta Clase de tipo Frame mostrará abre VistaInicial y según el usuario mostrará VistaAlumno o VistaProfesor.
 * @author:Silvia Reolid Dolz
 */

import java.awt.Toolkit;

import javax.swing.JFrame;

import modelo.DatosUsuario;

public class FramePrincipal extends JFrame {

	private VistaAlumno alumno;
	private VistaInicial inicial;
	private VistaProfesor profesor;
	
	/**
	 * Create the frame.
	 */
	public FramePrincipal() {
				
		inicial= new VistaInicial(this);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(VistaAlumno.class.getResource("/images/icono.png")));
		setTitle("Agenda Virtual");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		setContentPane(inicial);
	}
	
	public void cambiaPanelAlumno(DatosUsuario datos){
		this.remove(inicial);
		this.setContentPane(alumno= new VistaAlumno(datos));
		this.setVisible(true);
	}
	public void cambiaPanelProfesor(DatosUsuario datos){
		this.remove(inicial);
		setContentPane(profesor= new VistaProfesor(datos));
		this.setVisible(true);
	}

}
