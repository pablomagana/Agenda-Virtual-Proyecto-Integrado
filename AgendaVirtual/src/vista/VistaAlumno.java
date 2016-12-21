package vista;
/*
 * Esta Clase de tipo Panel se encarga de importar el widget calendario, cargar los botones de acceso a calificaciones y mensajes.
 * @author:Silvia Reolid Dolz
 */

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import java.awt.Image;
import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import modelo.DatosUsuario;
import modelo.DatosHashMap;
import widgets.VistaCalendario;

import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VistaAlumno extends JPanel {

	private JLabel foto, etiqAlum , nomAlum ,lblMisDatos, fondoAlum;
	private JButton bcalif, bMensajes ;
	private VistaCalendario calendario;
	private FramePeq peque;
	private FrameMediano mediano;
	private DatosUsuario datosAlum;
	private Image image;
	private DatosHashMap datosetiquetas=DatosHashMap.getinstance();

	/**
	 * Create the panel.
	 * @param datos 
	 */
	
	public VistaAlumno(DatosUsuario datos) {
		
datosAlum=datos;
		
		//Configuración contenedor
		
		setLayout(null);
		
		//Elementos contenedor: foto,tipo usuario y nombre
		
		URL urlimage;
		try {
			urlimage =new URL(datos.getFoto());
			image = ImageIO.read(urlimage);
			
		} catch (MalformedURLException e) {
		} catch (IOException e) {
		}
		
		foto = new JLabel("");
		foto.setIcon(new ImageIcon(image));
		foto.setBounds(14, 11, 95, 95);
		add(foto);
		
		etiqAlum = new JLabel(datosetiquetas.devuelveEtiquetas("ealumno", DatosUsuario.idioma)+" : ");
		etiqAlum.setFont(new Font("Segoe Print", etiqAlum.getFont().getStyle() | Font.BOLD, 18));
		etiqAlum.setForeground(new Color (0,4,120));
		etiqAlum.setBounds(119, 39, 95, 14);
		add(etiqAlum);
		
		nomAlum = new JLabel(this.datosAlum.getNombre()+" "+this.datosAlum.getApellidos());
		nomAlum.setFont(new Font("Segoe Print", nomAlum.getFont().getStyle() | Font.BOLD, 18));
		nomAlum.setBounds(218, 32, 241, 28);
		add(nomAlum);
		
		//Elementos contenedor: etiqueta y boton de configuracion
		
		lblMisDatos = new JLabel(datosetiquetas.devuelveEtiquetas("emisdatos", DatosUsuario.idioma));
		lblMisDatos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				mediano=new FrameMediano();
				mediano.cambiaVistalMisDatos(mediano,datosAlum);
				mediano.setVisible (true);
			}
		});
		lblMisDatos.setFont(new Font("Segoe Print", lblMisDatos.getFont().getStyle() | Font.BOLD, 18));
		lblMisDatos.setForeground(new Color (0,4,120));
		lblMisDatos.setBounds(119, 64, 300, 28);
		add(lblMisDatos);
		
		//Elementos contenedor: Botones calificaciones y mensajes
		
		bcalif = new JButton(datosetiquetas.devuelveEtiquetas("ecalificaciones", DatosUsuario.idioma));
		
		//Listener del boton calificacion que crea un FrameMediano y llama al metodo de este para generar la vista de eventos.
				bcalif.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						mediano=new FrameMediano();
						mediano.cambiaPanelCalificacionesAlum(mediano,datosAlum);
						mediano.setVisible (true);
					}
				});

		bcalif.setFont(new Font("Segoe Print", bcalif.getFont().getStyle() | Font.BOLD, 16));
		bcalif.setForeground(Color.BLUE);
		bcalif.setIcon(null);
		bcalif.setBounds(832, 43, 152, 33);
		add(bcalif);
		
		bMensajes = new JButton(datosetiquetas.devuelveEtiquetas("emensajes", DatosUsuario.idioma));
		
		//Asigna accion al boton para que abra la vista pequeña (contraseña padres, la propia vista pequeña)
		bMensajes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent pass) {
				peque= new FramePeq();
				peque.abreVistaParent(datosAlum.getContPadres(),peque,datosAlum);
				peque.setVisible (true);
			}
		});
			
		
		bMensajes.setFont(new Font("Segoe Print", bMensajes.getFont().getStyle() | Font.BOLD, 16));
		bMensajes.setForeground(Color.BLUE);
		bMensajes.setIcon(null);
		bMensajes.setBounds(832, 87, 152, 33);
		add(bMensajes);
		
		//añadimos calendario
		
		calendario= new VistaCalendario(datosAlum);
		calendario.setBounds(100,133,800,398);
		calendario.setOpaque(false);
		this.add(calendario);
		
		//fondo pantalla
		fondoAlum = new JLabel("");
		fondoAlum.setFont(new Font("Tahoma", Font.PLAIN, 18));
		fondoAlum.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		fondoAlum.setHorizontalAlignment(SwingConstants.CENTER);
		fondoAlum.setIcon(new ImageIcon(VistaAlumno.class.getResource("/images/FONDO VISTAS3.png")));
		fondoAlum.setBounds(0, 0, 994, 572);
		add(fondoAlum);
	}
}
