package widgets;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import modelo.DatosHashMap;
import modelo.DatosTareas;
import modelo.DatosEventos;
import modelo.DatosUsuario;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JDayChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VistaCalendario extends JPanel {

	private JTabbedPane tabbedPane;
	private JPanel panel_dia, panel_Tareas, panel_Eventos;
	private JDateChooser eligeDia;
	private JLabel nomDia, lblTareas, lblEventos;
	private JPanel panel_semana, panel_1, panel_2, panel_3, panel_4, panel_5,
			panel_6, panel_7;
	private JLabel label_Lun, label_Mar, label_Mier, label_Jue, label_Vie,
			label_Sab, label_Dom;
	private JLabel lunes, martes, miercoles, jueves, viernes, sabado, domingo;
	private JLabel lblTareas_1, lblEventos_1;
	private JLabel lema;
	private JLabel fondocalend;
	private Calendar c2;
	private JDayChooser calenmes;
	private DatosTareas dTareas;
	private DatosEventos dEventos;
	private JLabel[] arrayLabelEventos;
	private VistaUnaTarea[] arrayVTareas;
	private VistaUnEvento[] arrayVEvent;
	private JsonArray arrayEventos;

	private JsonArray arrayTareas;
	private DatosHashMap etiquetadatos = DatosHashMap.getinstance();

	String id;
	String fecha;

	/**
	 * Create the panel.
	 */
	public VistaCalendario(DatosUsuario datos) {
		dTareas = new DatosTareas();
		dEventos = new DatosEventos();
		setLayout(null);
		// insertamos tabbedpane
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 800, 400);
		tabbedPane.setOpaque(false);
		tabbedPane.setBorder(null);
		tabbedPane.setFont(new Font("Segoe Print", Font.BOLD, 18));
		add(tabbedPane);

		// Pestañas del tabbedpane(dia,semana,mes)

		/*
		 * Pestaña dia: muestra el dia elegido o actual con las tareas y eventos
		 * asignadas.
		 */

		// creamos el contenedor dia y su configuracion

		panel_dia = new JPanel();
		panel_dia.setOpaque(false);
		panel_dia.setBackground(SystemColor.activeCaption);
		tabbedPane.addTab(
				etiquetadatos.devuelveEtiquetas("edia", DatosUsuario.idioma),
				null, panel_dia, null);
		panel_dia.setLayout(null);

		// panel_Tareas.removeAll();
		panel_Tareas = new JPanel();
		panel_Tareas.setOpaque(false);
		panel_Tareas.setBorder(new LineBorder(new Color(255, 255, 255), 3));
		panel_Tareas.setBounds(10, 56, 385, 284);
		panel_dia.add(panel_Tareas);
		panel_Tareas.setLayout(null);

		lblTareas = new JLabel(etiquetadatos.devuelveEtiquetas("etareas",
				DatosUsuario.idioma));
		lblTareas.setHorizontalAlignment(SwingConstants.CENTER);
		lblTareas.setBounds(71, 11, 230, 45);
		lblTareas.setForeground(Color.WHITE);
		lblTareas.setFont(new Font("Segoe Print", Font.BOLD, 24));
		panel_Tareas.add(lblTareas);

		panel_Eventos = new JPanel();
		panel_Eventos.setOpaque(false);
		panel_Eventos.setBorder(new LineBorder(Color.WHITE, 3));
		panel_Eventos.setBounds(400, 56, 385, 284);
		panel_dia.add(panel_Eventos);
		panel_Eventos.setLayout(null);

		lblEventos = new JLabel(etiquetadatos.devuelveEtiquetas("eeventos",
				DatosUsuario.idioma));
		lblEventos.setHorizontalAlignment(SwingConstants.CENTER);
		lblEventos.setBounds(91, 11, 217, 45);
		lblEventos.setForeground(Color.WHITE);
		lblEventos.setFont(new Font("Segoe Print", Font.BOLD, 24));
		panel_Eventos.add(lblEventos);

		// agregamos datechooser que mostrará por defecto el dia actual al
		// pasarle el calendario gregoriano

		c2 = new GregorianCalendar();
		eligeDia = new JDateChooser();
		eligeDia.setDateFormatString("dd-MMMM-yyyy");

		eligeDia.setFont(new Font("Segoe Print", Font.BOLD, 12));
		getIdiomaDia();
		eligeDia.setBounds(401, 11, 178, 34);
		panel_dia.add(eligeDia);
		eligeDia.setCalendar(c2);

		// etiqueta que mostrara el nombre del dia en curso

		nomDia = new JLabel(dimeDia());
		nomDia.setHorizontalAlignment(SwingConstants.CENTER);
		nomDia.setForeground(Color.WHITE);
		nomDia.setFont(new Font("Segoe Print", Font.BOLD, 18));
		nomDia.setBounds(251, 11, 133, 34);
		panel_dia.add(nomDia);

		// imagen de fondo del panel_dia

		fondocalend = new JLabel("");
		fondocalend.setFont(new Font("Segoe Print", Font.BOLD, 14));
		fondocalend.setIcon(new ImageIcon(VistaCalendario.class
				.getResource("/images/fondoCalendario.png")));
		fondocalend.setBounds(0, 0, 795, 353);
		panel_dia.add(fondocalend);

		/*
		 * Pestaña semana: muestra un conjunto de panels con los dias de la
		 * semana en curso
		 */

		/* creamos el contenedor semana y su configuracion

		panel_semana = new JPanel();
		panel_semana.setBackground(SystemColor.textHighlight);
		tabbedPane
				.addTab(etiquetadatos.devuelveEtiquetas("esemana",
						DatosUsuario.idioma), null, panel_semana, null);
		tabbedPane.setFont(new Font("Segoe Print", panel_semana.getFont()
				.getStyle() | Font.BOLD, 18));
		panel_semana.setLayout(null);

		// LUNES
		panel_1 = new JPanel();
		panel_1.setBounds(4, 9, 190, 166);
		panel_1.setBorder(null);
		panel_semana.add(panel_1);
		panel_1.setLayout(null);

		lunes = new JLabel("");
		lunes.setBounds(0, 0, 190, 60);
		panel_1.add(lunes);
		lunes.setIcon(new ImageIcon(VistaCalendario.class
				.getResource(etiquetadatos.devuelveEtiquetas("eimglunes",
						DatosUsuario.idioma))));

		lblTareas_1 = new JLabel(etiquetadatos.devuelveEtiquetas("etareas",
				DatosUsuario.idioma));
		lblTareas_1.setFont(new Font("Segoe Print", lblTareas_1.getFont()
				.getStyle() | Font.BOLD, 18));
		lblTareas_1.setBounds(27, 58, 72, 24);
		panel_1.add(lblTareas_1);

		lblEventos_1 = new JLabel(etiquetadatos.devuelveEtiquetas("eeventos",
				DatosUsuario.idioma));
		lblEventos_1.setFont(new Font("Segoe Print", lblEventos_1.getFont()
				.getStyle() | Font.BOLD, 18));
		lblEventos_1.setBounds(27, 127, 72, 24);
		panel_1.add(lblEventos_1);

		label_Lun = new JLabel("");
		label_Lun.setBounds(0, 0, 190, 166);
		panel_1.add(label_Lun);
		label_Lun.setIcon(new ImageIcon(VistaCalendario.class
				.getResource("/images/cajadia1.png")));

		// MARTES
		panel_2 = new JPanel();
		panel_2.setBorder(null);
		panel_2.setBounds(199, 9, 190, 166);
		panel_semana.add(panel_2);
		panel_2.setLayout(null);

		martes = new JLabel("");
		martes.setBounds(0, 0, 190, 60);
		panel_2.add(martes);
		martes.setIcon(new ImageIcon(VistaCalendario.class
				.getResource(etiquetadatos.devuelveEtiquetas("eimgmartes",
						DatosUsuario.idioma))));

		label_Mar = new JLabel("");
		label_Mar.setBounds(0, 0, 190, 166);
		panel_2.add(label_Mar);
		label_Mar.setIcon(new ImageIcon(VistaCalendario.class
				.getResource("/images/cajadia1.png")));

		// MIERCOLES
		panel_3 = new JPanel();
		panel_3.setBorder(null);
		panel_3.setBounds(395, 9, 190, 166);
		panel_semana.add(panel_3);
		panel_3.setLayout(null);

		miercoles = new JLabel("");
		miercoles.setBounds(0, 0, 190, 60);
		panel_3.add(miercoles);
		miercoles.setIcon(new ImageIcon(VistaCalendario.class
				.getResource(etiquetadatos.devuelveEtiquetas("eimgmiercoles",
						DatosUsuario.idioma))));

		label_Mier = new JLabel("");
		label_Mier.setBounds(0, 0, 190, 166);
		panel_3.add(label_Mier);
		label_Mier.setIcon(new ImageIcon(VistaCalendario.class
				.getResource("/images/cajadia1.png")));

		// JUEVES
		panel_4 = new JPanel();
		panel_4.setBorder(null);
		panel_4.setBounds(592, 9, 190, 166);
		panel_semana.add(panel_4);
		panel_4.setLayout(null);

		jueves = new JLabel("");
		jueves.setBounds(0, 0, 190, 60);
		panel_4.add(jueves);
		jueves.setIcon(new ImageIcon(VistaCalendario.class
				.getResource(etiquetadatos.devuelveEtiquetas("eimgjueves",
						DatosUsuario.idioma))));

		label_Jue = new JLabel("");
		label_Jue.setBounds(0, 0, 190, 200);
		panel_4.add(label_Jue);
		label_Jue.setIcon(new ImageIcon(VistaCalendario.class
				.getResource("/images/cajadia1.png")));

		// VIERNES
		panel_5 = new JPanel();
		panel_5.setBorder(null);
		panel_5.setBounds(199, 180, 190, 166);
		panel_semana.add(panel_5);
		panel_5.setLayout(null);

		viernes = new JLabel("");
		viernes.setBounds(0, 0, 190, 60);
		panel_5.add(viernes);
		viernes.setIcon(new ImageIcon(VistaCalendario.class
				.getResource(etiquetadatos.devuelveEtiquetas("eimgviernes",
						DatosUsuario.idioma))));

		label_Vie = new JLabel("");
		label_Vie.setBounds(0, 0, 190, 200);
		panel_5.add(label_Vie);
		label_Vie.setIcon(new ImageIcon(VistaCalendario.class
				.getResource("/images/cajadia1.png")));

		// SABADO
		panel_6 = new JPanel();
		panel_6.setBorder(null);
		panel_6.setBounds(395, 180, 190, 166);
		panel_semana.add(panel_6);
		panel_6.setLayout(null);

		sabado = new JLabel("");
		sabado.setBounds(0, 0, 190, 60);
		panel_6.add(sabado);
		sabado.setIcon(new ImageIcon(VistaCalendario.class
				.getResource(etiquetadatos.devuelveEtiquetas("eimgsabado",
						DatosUsuario.idioma))));

		label_Sab = new JLabel("");
		label_Sab.setBounds(0, 0, 190, 167);
		panel_6.add(label_Sab);
		label_Sab.setIcon(new ImageIcon(VistaCalendario.class
				.getResource("/images/cajadia1.png")));

		// DOMINGO
		panel_7 = new JPanel();
		panel_7.setBorder(null);
		panel_7.setBounds(592, 180, 190, 166);
		panel_semana.add(panel_7);
		panel_7.setLayout(null);

		domingo = new JLabel("");
		domingo.setBounds(0, 0, 190, 60);
		panel_7.add(domingo);
		domingo.setIcon(new ImageIcon(VistaCalendario.class
				.getResource(etiquetadatos.devuelveEtiquetas("eimgdomingo",
						DatosUsuario.idioma))));

		label_Dom = new JLabel("");
		label_Dom.setBounds(0, 0, 190, 166);
		panel_7.add(label_Dom);
		label_Dom.setIcon(new ImageIcon(VistaCalendario.class
				.getResource("/images/cajadia1.png")));

		// agregamos lema de la aplicacion
		lema = new JLabel("");
		lema.setIcon(new ImageIcon(VistaCalendario.class.getResource(etiquetadatos.devuelveEtiquetas("elema",
						DatosUsuario.idioma))));
		lema.setBounds(0, 205, 194, 121);
		panel_semana.add(lema);

		// fondo de la pestaña semana
		fondocalend = new JLabel("");
		fondocalend.setIcon(new ImageIcon(VistaCalendario.class
				.getResource("/images/fondoCalendario.png")));
		fondocalend.setBounds(0, 0, 795, 353);
		panel_semana.add(fondocalend);*/

		/**
		 * Pestaña mes: mediante de datechooser se muestra al usuario el
		 * calendario completo del mes en curso.
		 */

		calenmes = new JDayChooser();
		getCalendario();
		calenmes.getDayPanel().setOpaque(false);
		calenmes.setSize(new Dimension(15, 0));
		calenmes.setDecorationBackgroundVisible(true);
		calenmes.getDayPanel().setBorder(null);

		calenmes.getDayPanel().setFont(new Font("Segoe Print", Font.BOLD, 18));
		calenmes.setOpaque(false);
		calenmes.getDayPanel().setBounds(0, 0, 795, 353);
		calenmes.setWeekOfYearVisible(true);
		tabbedPane.addTab(
				etiquetadatos.devuelveEtiquetas("emes", DatosUsuario.idioma),
				null, calenmes, null);
		calenmes.setLayout(null);

		fondocalend = new JLabel("");
		fondocalend.setIcon(new ImageIcon(VistaCalendario.class
				.getResource("/images/fondoCalendario.png")));
		fondocalend.setBounds(0, 0, 795, 353);
		calenmes.add(fondocalend);

		/**
		 * Listener que actua sobre el calendario mes, permitiendo hacer clic
		 * sobre cualquier dia y se abra la pestaña "dia" con la fecha
		 * seleccionada.
		 */

		calenmes.addPropertyChangeListener("day", new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent e) {
				tabbedPane.setSelectedComponent(panel_dia);

				int año = eligeDia.getCalendar().get(Calendar.YEAR);
				int mes = eligeDia.getCalendar().get(Calendar.MONTH);
				int dia = (int) e.getNewValue();
				String fecha = año + " " + dia + " " + mes;

				c2 = new GregorianCalendar(año, mes, dia);
				eligeDia.setCalendar(c2);
			}
		});

		/**
		 * Listener que permite, al usuario, cambiar la fecha en el panel dia
		 */
		eligeDia.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent e) {

				try {
					for (int t = 0; t < arrayVTareas.length; t++) {
						panel_Tareas.remove(arrayVTareas[t]);

					}
				} catch (Exception s) {
				}
				try{
					for (int l = 0; l < arrayVEvent.length; l++) {
						panel_Eventos.remove(arrayVEvent[l]);
					}
				} catch (Exception s) {
				}

				int año = eligeDia.getCalendar().get(Calendar.YEAR);
				int mes = eligeDia.getCalendar().get(Calendar.MONTH);
				int dia = eligeDia.getCalendar().get(Calendar.DAY_OF_MONTH);
				String fecha = año + "-" + (mes+1)+ "-" + dia;
				c2 = new GregorianCalendar(año, mes, dia);
				nomDia.setText(dimeDia());
				
				if(datos.tipo == "Alumno"){
					
				try {
					arrayTareas = dTareas.getTareas(datos.getId(), fecha);
					arrayVTareas = new VistaUnaTarea[arrayTareas.size()];

					int posicion = 73;
					for (int i = 0; i < arrayTareas.size(); i++) {

						JsonObject tarea = arrayTareas.getJsonObject(i);
						try{
						arrayVTareas[i] = new VistaUnaTarea(datos.getId(),tarea.getString("id_tarea"), tarea.getString("nombre"), tarea.getString("realizada"));
						arrayVTareas[i].setBounds(15, posicion, 350, 30);
						panel_Tareas.add(arrayVTareas[i]);

						posicion = posicion + 30;
						} catch (Exception e1) {
						}
					}
				} catch (Exception e1) {
				}
				panel_Tareas.repaint();

				try {
					arrayEventos = dEventos.getEventos(datos.getId(), fecha);
					arrayVEvent = new VistaUnEvento[arrayEventos.size()];
					
					int posicion = 73;
					
					for (int z = 0; z < arrayEventos.size(); z++) {

						JsonObject evento = arrayEventos.getJsonObject(z);
						arrayVEvent[z] = new VistaUnEvento(evento.getString("ideventos"), evento.getString("nombre"));
						arrayVEvent[z].setBounds(15, posicion, 350, 30);
						panel_Eventos.add(arrayVEvent[z]);
						
						posicion = posicion + 30;

					}
				} catch (Exception e1) {
				}
				panel_Eventos.repaint();
			
			
			}else if(datos.tipo == "Profesor"){
				
				try {
					arrayTareas = dTareas.getTareasProf(datos.getId(), fecha);
					arrayVTareas = new VistaUnaTarea[arrayTareas.size()];

					int posicion = 73;
					for (int i = 0; i < arrayTareas.size(); i++) {

						JsonObject tarea = arrayTareas.getJsonObject(i);
						try{
						arrayVTareas[i] = new VistaUnaTarea(datos.getId(),tarea.getString("idtareas"), tarea.getString("nombre"), "false");
						arrayVTareas[i].setBounds(15, posicion, 350, 30);
						panel_Tareas.add(arrayVTareas[i]);

						posicion = posicion + 30;
						} catch (Exception e1) {
						}
					}
				} catch (Exception e1) {
				}
				panel_Tareas.repaint();

				try {
					arrayEventos = dEventos.getEventosProf(datos.getId(), fecha);
					arrayVEvent = new VistaUnEvento[arrayEventos.size()];
					
					int posicion = 73;
					
					for (int z = 0; z < arrayEventos.size(); z++) {

						JsonObject evento = arrayEventos.getJsonObject(z);
						arrayVEvent[z] = new VistaUnEvento(evento.getString("ideventos"), evento.getString("nombre"));
						arrayVEvent[z].setBounds(15, posicion, 350, 30);
						panel_Eventos.add(arrayVEvent[z]);
						
						posicion = posicion + 30;

					}
				} catch (Exception e1) {
				}
				panel_Eventos.repaint();
			}
			}
		});
		
	}

	/**
	 * metodo que devuelve el string del dia de la semana seleccionado (pestaña
	 * dia)
	 */

	public String dimeDia() {

		int dia = (c2.get(Calendar.DAY_OF_WEEK));
		String diaString = null;

		switch (dia) {
		case 1:
			diaString = etiquetadatos.devuelveEtiquetas("edomingo",
					DatosUsuario.idioma);
			break;
		case 2:
			diaString = etiquetadatos.devuelveEtiquetas("elunes",
					DatosUsuario.idioma);
			break;
		case 3:
			diaString = etiquetadatos.devuelveEtiquetas("emartes",
					DatosUsuario.idioma);
			break;
		case 4:
			diaString = etiquetadatos.devuelveEtiquetas("emiercoles",
					DatosUsuario.idioma);
			break;
		case 5:
			diaString = etiquetadatos.devuelveEtiquetas("ejueves",
					DatosUsuario.idioma);
			break;
		case 6:
			diaString = etiquetadatos.devuelveEtiquetas("eviernes",
					DatosUsuario.idioma);
			break;
		case 7:
			diaString = etiquetadatos.devuelveEtiquetas("esabado",
					DatosUsuario.idioma);
			break;

		}
		return diaString;
	}

	/**
	 * metodo que cambia el idioma mostrado en el calendario (pestaña mes)
	 * dependiendo del idioma elegido por el usuario al iniciar sesion.
	 */
	public void getCalendario() {

		if (DatosUsuario.idioma == 0) {
			calenmes.setLocale(new Locale("es"));
		} else {
			calenmes.setLocale(Locale.ENGLISH);
		}
	}

	/**
	 * metodo que cambia el idioma mostrado en el datechooser (pestaña dia)
	 * dependiendo del idioma elegido por el usuario al iniciar sesion.
	 */
	public void getIdiomaDia() {
		if (DatosUsuario.idioma == 0) {
			eligeDia.setLocale(new Locale("es"));
		} else {
			eligeDia.setLocale(new Locale("en"));
		}
	}

}
