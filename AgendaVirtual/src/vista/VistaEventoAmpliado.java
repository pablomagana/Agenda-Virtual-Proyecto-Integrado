package vista;

import java.awt.Color;
import java.awt.Font;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import modelo.DatosEventos;
import modelo.DatosHashMap;
import modelo.DatosTareas;
import modelo.DatosUsuario;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VistaEventoAmpliado extends JPanel {

	private DatosEventos dEventos;
	private JTextArea txtrTarea;
	private JsonArray arrayJsonEventos;
	private JLabel lblAsignatura, lblDescripcion, lblEvento, lblAsignatura_1,lblNomTarea,fondo;
	private DatosHashMap datosetiquetas=DatosHashMap.getinstance();
	JButton btnAtras;

	/**
	 * Create the panel.
	 */
	public VistaEventoAmpliado(String identificador, FrameIntermedio intermedio) {

		dEventos = new DatosEventos();
		arrayJsonEventos = dEventos.getEvAsig(identificador);
		JsonObject tarea = arrayJsonEventos.getJsonObject(0);

		setLayout(null);

		lblAsignatura_1 = new JLabel(datosetiquetas.devuelveEtiquetas("easignatura", DatosUsuario.idioma));
		lblAsignatura_1.setFont(new Font("Segoe Print", Font.BOLD, 14));
		lblAsignatura_1.setForeground(new Color (0,4,120));
		lblAsignatura_1.setBounds(33, 17, 122, 25);
		add(lblAsignatura_1);

		lblAsignatura = new JLabel(tarea.getString("nom_asig"));
		lblAsignatura.setHorizontalAlignment(SwingConstants.LEFT);
		lblAsignatura.setFont(new Font("Segoe Print", Font.BOLD, 14));
		lblAsignatura.setForeground(Color.WHITE);
		lblAsignatura.setBounds(171, 14, 178, 30);
		add(lblAsignatura);

		lblEvento = new JLabel(datosetiquetas.devuelveEtiquetas("eevento", DatosUsuario.idioma));
		lblEvento.setFont(new Font("Segoe Print", Font.BOLD, 14));
		lblEvento.setForeground(new Color (0,4,120));
		lblEvento.setBounds(33, 53, 49, 25);
		add(lblEvento);

		lblNomTarea = new JLabel(tarea.getString("nombre"));
		lblNomTarea.setHorizontalAlignment(SwingConstants.LEFT);
		lblNomTarea.setForeground(Color.WHITE);
		lblNomTarea.setFont(new Font("Segoe Print", Font.BOLD, 14));
		lblNomTarea.setBounds(171, 53, 271, 25);
		add(lblNomTarea);

		lblDescripcion = new JLabel(datosetiquetas.devuelveEtiquetas("edescripcion", DatosUsuario.idioma));
		lblDescripcion.setFont(new Font("Segoe Print", Font.BOLD, 14));
		lblDescripcion.setForeground(new Color (0,4,120));
		lblDescripcion.setBounds(33, 89, 122, 25);
		add(lblDescripcion);

		txtrTarea = new JTextArea(tarea.getString("descripcion"));
		txtrTarea.setEditable(false);
		txtrTarea.setBackground(Color.WHITE);
		txtrTarea.setWrapStyleWord(true);
		txtrTarea.setForeground(new Color(208, 186, 61));
		txtrTarea.setLineWrap(true);
		txtrTarea.setOpaque(false);
		txtrTarea.setRows(2);
		txtrTarea.setFont(new Font("Segoe Print", Font.BOLD, 16));
		txtrTarea.setBounds(33, 116, 536, 216);
		add(txtrTarea);
		
		btnAtras = new JButton(datosetiquetas.devuelveEtiquetas("eatras", DatosUsuario.idioma));
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				intermedio.dispose();
			}
		});
		btnAtras.setFont(new Font("Segoe Print", Font.BOLD, 14));
		btnAtras.setBounds(479, 19, 89, 23);
		add(btnAtras);

		fondo = new JLabel("");
		fondo.setIcon(new ImageIcon(VistaTareaAmpliada.class.getResource("/images/FONDO VISTAS4.png")));
		fondo.setBounds(0, 0, 600, 343);
		add(fondo);

	}
}
