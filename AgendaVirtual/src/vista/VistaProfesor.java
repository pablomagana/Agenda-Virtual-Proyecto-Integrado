package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.DatosHashMap;
import modelo.DatosUsuario;
import widgets.VistaCalendario;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VistaProfesor extends JPanel {
	private DatosHashMap datosetiquetas=DatosHashMap.getinstance();
	
	private JLabel fotoProf, etipProf, nomProf, lblMisDatos, fondo ;
	private JButton bTarea, bCalificaciones, bMensajes, bEvento ;
	private VistaCalendario calendario;
	private FrameMediano mediano;
	private DatosUsuario datosProf;
	private Image image;

	public VistaProfesor(DatosUsuario datos) {
		
		this.datosProf=datos;
		
		
		setBounds(100, 100, 994, 572);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		//foto
		URL urlimage;
		try {
			urlimage = new URL("http://192.168.1.69/api/media/fotos/Teacher.png");
			urlimage =new URL(datos.getFoto());
			image = ImageIO.read(urlimage);
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		fotoProf = new JLabel("");
		fotoProf.setIcon(new ImageIcon(image));
		fotoProf.setBounds(14, 11, 95, 95);
		add(fotoProf);
		
		etipProf = new JLabel(datosetiquetas.devuelveEtiquetas("eprofesor", DatosUsuario.idioma)+" : ");
		etipProf.setFont(new Font("Segoe Print", etipProf.getFont().getStyle() | Font.BOLD, 18));
		etipProf.setForeground(new Color (0,4,120));
		etipProf.setBounds(117, 38, 109, 23);
		add(etipProf);
		
		nomProf = new JLabel(this.datosProf.getNombre()+" "+this.datosProf.getApellidos());
		nomProf.setFont(new Font("Segoe Print", nomProf.getFont().getStyle() | Font.BOLD, 18));
		nomProf.setBounds(227, 36, 241, 26);
		add(nomProf);
		
		lblMisDatos = new JLabel(datosetiquetas.devuelveEtiquetas("emisdatos", DatosUsuario.idioma));
		lblMisDatos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				mediano=new FrameMediano();
				mediano.cambiaVistalMisDatos(mediano,datosProf);
				mediano.setVisible (true);
			}
		});
		lblMisDatos.setFont(new Font("Segoe Print", lblMisDatos.getFont().getStyle() | Font.BOLD, 18));
		lblMisDatos.setForeground(new Color (0,4,120));
		lblMisDatos.setBounds(119, 83, 324, 25);
		add(lblMisDatos);
		
		//Elementos contenedor: Botones tareas, calificaciones, evento  y mensajes
		
		bTarea = new JButton(datosetiquetas.devuelveEtiquetas("etarea", DatosUsuario.idioma));
		
		//accion del boton tareas, que abrira la vista mediana
		bTarea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mediano=new FrameMediano();
				mediano.cambiaPanelTareas(mediano,datosProf);
				mediano.setVisible (true);
			}
		});
		
		bTarea.setForeground(Color.BLUE);
		bTarea.setFont(new Font("Segoe Print", bTarea.getFont().getStyle() | Font.BOLD, 18));
		bTarea.setIcon(null);
		bTarea.setBounds(507, 98, 165, 35);
		add(bTarea);
		
		bCalificaciones = new JButton(datosetiquetas.devuelveEtiquetas("ecalificaciones", DatosUsuario.idioma));
		//accion del boton calificaciones, que abrira la vista mediana
		bCalificaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediano=new FrameMediano();
				mediano.cambiaPanelCalificacionesProfe(mediano,datosProf);
				mediano.setVisible (true);
			}
		});
			

		bCalificaciones.setForeground(Color.BLUE);
		bCalificaciones.setFont(new Font("Segoe Print", bCalificaciones.getFont().getStyle() | Font.BOLD, 18));
		bCalificaciones.setBounds(728, 39, 165, 35);
		add(bCalificaciones);
		
		bMensajes = new JButton(datosetiquetas.devuelveEtiquetas("emensajes", DatosUsuario.idioma));
		//accion del boton mensajes, que abrira la vista mediana
		bMensajes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mediano=new FrameMediano();
				mediano.cambiaPanelMensajeProfesor(mediano,datosProf);
				mediano.setVisible (true);
				}
		});
		bMensajes.setForeground(Color.BLUE);
		bMensajes.setFont(new Font("Segoe Print", bMensajes.getFont().getStyle() | Font.BOLD, 18));
		bMensajes.setBounds(728, 98, 165, 35);
		add(bMensajes);
		
		bEvento = new JButton(datosetiquetas.devuelveEtiquetas("eevento", DatosUsuario.idioma));
		//accion del boton eventos, que abrira la vista mediana
		bEvento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediano=new FrameMediano();
				mediano.cambiaPanelVistaEventos(mediano,datosProf);
				mediano.setVisible (true);
				
			}
		});
		bEvento.setForeground(Color.BLUE);
		bEvento.setFont(new Font("Segoe Print", bEvento.getFont().getStyle() | Font.BOLD, 18));
		bEvento.setBounds(507, 39, 165, 35);
		add(bEvento);
		
		//añadimos calendario
		
		calendario= new VistaCalendario(datosProf);
		calendario.setBounds(100,137,800,398);
		calendario.setOpaque(false);
		add(calendario);
		
		//fondo 
		
		fondo = new JLabel("");
		fondo.setIcon(new ImageIcon(VistaProfesor.class.getResource("/images/FONDO VISTAS3.png")));
		fondo.setBounds(0, 0, 994, 572);
		add(fondo);

}
}
