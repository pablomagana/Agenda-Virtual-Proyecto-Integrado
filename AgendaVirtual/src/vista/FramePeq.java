package vista;
/*
 * Esta Clase de tipo Frame mostrará abre VistaParent, VistaError y VistaNotificacion.
 * @author:Silvia Reolid Dolz
 */

import java.awt.Toolkit;
import javax.swing.JFrame;
import modelo.DatosUsuario;

public class FramePeq extends JFrame {

	
	private VistaParental parent;

	//Frame pequeño que contendrá los panels de las siguientes vistas= VistaParental,VistaError.
	
	public FramePeq() {
		//Configuracion  ventana
		setTitle("Agenda Virtual");
		setIconImage(Toolkit.getDefaultToolkit().getImage(FramePeq.class.getResource("/images/icono.png")));
		setResizable(false);
		
		//Creamos y configuramos el contenedor de la ventana 
		setBounds(450, 300, 360, 264);
	}
	//Metodo que cargan un nuevo panel y le pasa el propio frame pequeño que habremos creado y los datos del usuario que esta ahora conectado.
	public void abreVistaParent (String contras,FramePeq peque,DatosUsuario datosUsuario){
		setContentPane(new VistaParental(contras,peque,datosUsuario));

	}
	
	//Metodo que cargan un nuevo panel y le pasa el propio frame pequeño que habremos creado y el nombre del error que debe mostrar.
	public void abreVistaError (String error,FramePeq peque){
		setContentPane(new VistaError(error,peque));

	}
	public void abreVistaNotificacion (String notificacion,FramePeq peque){
		setContentPane(new VistaNotificacion(notificacion,peque));

	}

}
