package vista;

import java.awt.Color;

import javax.json.JsonArray;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import modelo.DatosHashMap;
import modelo.DatosUsuario;
import modelo.DatosMensaje;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaLeerMensaje extends JPanel {
	
	private FrameIntermedio intermedio;
	private JTextField textProfesor, textAsunto,textAlumno;
	private JLabel label, labelAsunto, fondo;
	public JButton botonAtras;
	private JTextArea textAreaMensaje;
	private DatosHashMap datosetiquetas=DatosHashMap.getinstance();
	
	private JsonArray mensaje;
	/**
	 * Create the panel.
	 */
	public VistaLeerMensaje(String identificador,FrameIntermedio intermedio,DatosMensaje datosMensajes) {
		setLayout(null);
		
		this.intermedio = intermedio;
			
		if(DatosUsuario.tipo == "Profesor"){
		this.mensaje=datosMensajes.getMensajeP(identificador);
	
		label = new JLabel(datosetiquetas.devuelveEtiquetas("ealumno", DatosUsuario.idioma));
		label.setForeground(new Color (0,4,120));
		label.setFont(new Font("Segoe Print", Font.BOLD, 14));
		label.setBounds(21, 50, 66, 14);
		add(label);
		
		textProfesor = new JTextField(mensaje.getJsonObject(0).getString("nom_alu")+" "+mensaje.getJsonObject(0).getString("ape_alu"));
		textProfesor.setEditable(false);
		textProfesor.setBounds(97, 48, 259, 20);
		
		textProfesor.setColumns(10);
		add(textProfesor);
		
		}else if(DatosUsuario.tipo == "Alumno"){
			this.mensaje=datosMensajes.getMensajeA(identificador);

			label = new JLabel(datosetiquetas.devuelveEtiquetas("eprofesor", DatosUsuario.idioma));
			label.setBounds(21, 50, 66, 14);
			label.setFont(new Font("Segoe Print", Font.BOLD, 14));
			label.setForeground(new Color (0,4,120));
			add(label);
			textAlumno = new JTextField(mensaje.getJsonObject(0).getString("nom_prof")+" "+mensaje.getJsonObject(0).getString("ape_prof"));
			textAlumno.setEditable(false);
			textAlumno.setBounds(97, 48, 259, 20);
			
			textAlumno.setColumns(10);
			add(textAlumno);
		}
		
		labelAsunto = new JLabel(datosetiquetas.devuelveEtiquetas("easunto", DatosUsuario.idioma));
		labelAsunto.setFont(new Font("Segoe Print", Font.BOLD, 14));
		labelAsunto.setForeground(new Color (0,4,120));
		labelAsunto.setBounds(21, 92, 66, 20);
		add(labelAsunto);
		
		textAsunto = new JTextField();
		textAsunto.setEditable(false);
		textAsunto.setBounds(97, 93, 259, 20);
		add(textAsunto);
		textAsunto.setColumns(10);
		textAsunto.setText(mensaje.getJsonObject(0).getString("asunto"));
		
		textAreaMensaje = new JTextArea();
		textAreaMensaje.setEditable(false);
		textAreaMensaje.setBounds(97, 140, 453, 149);
		add(textAreaMensaje);
		textAreaMensaje.setText(mensaje.getJsonObject(0).getString("cuerpo"));
		
		botonAtras = new JButton(datosetiquetas.devuelveEtiquetas("eatras", DatosUsuario.idioma));
		botonAtras.setBounds(461, 92, 89, 23);
		add(botonAtras);
		
		botonAtras.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				intermedio.dispose();
			}
		});
		
		fondo = new JLabel("");
		fondo.setIcon(new ImageIcon(VistaLeerMensaje.class.getResource("/images/FONDO VISTAS5.png")));
		fondo.setBounds(0, 0, 605, 344);
		add(fondo);
		
		

	}
}
