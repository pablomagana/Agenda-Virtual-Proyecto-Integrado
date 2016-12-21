package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;

import com.toedter.calendar.JDateChooser;

import javax.swing.ImageIcon;

import modelo.DatosAlumno;
import modelo.DatosAsignatura;
import modelo.DatosClase;
import modelo.DatosCurso;
import modelo.DatosEventos;
import modelo.DatosHashMap;
import modelo.DatosUsuario;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;

import javax.swing.JTextField;

public class VistaEventos extends JPanel {

	
	private JComboBox curso, clase,  asignatura,  tipo; 
	private JDateChooser eligeDia;
	private JTextArea asuntoTarea;
	private JLabel fondo;
	private JButton bEnviar;
	private JButton btnCancelar;
	
	private DatosHashMap datosetiquetas=DatosHashMap.getinstance();
	private DatosEventos datosEventos;
	
	private JsonArray arrayClase,arrayCursos,arrayAsignaturas;
	
	private DatosCurso dcursos;
	private DatosClase dclase;
	private DatosAsignatura dasignatura;
	
	private String[] idnomEventos;
	private String[] idEventos;
	private JTextField comboBoxNombre;

	public VistaEventos(FrameMediano mediano,DatosUsuario datosUsuario) {
		setForeground(SystemColor.activeCaption);
		setBackground(SystemColor.activeCaption);
		setBorder(null);
		setLayout(null);
		

		dcursos=new DatosCurso();
		dclase=new DatosClase();
		dasignatura=new DatosAsignatura();
		
		//creamos los distintos desplegables y la fecha para seleccionar y enviar la tarea
		
				curso = new JComboBox();
				curso.setFont(new Font("Segoe Print", curso.getFont().getStyle() | Font.BOLD, 18));
				curso.setBounds(102, 35, 125, 27);
				curso.addItem (datosetiquetas.devuelveEtiquetas("ecurso", DatosUsuario.idioma));
				curso.setForeground(new Color(0, 102, 153));
				add(curso);
				try{
				arrayCursos = dcursos.getCursosProf(datosUsuario.getId());//OBJETO DE JSONARRAY
				//añadimos los cursos
				for(int c=0;c<arrayCursos.size();c++){
					JsonObject cursojson = arrayCursos.getJsonObject(c);
					curso.addItem(cursojson.getString("nombre"));
				}
				}catch(Exception e){
					
				}
				clase = new JComboBox();
				clase.setFont(new Font("Segoe Print", clase.getFont().getStyle() | Font.BOLD, 18));
				clase.setBounds(284, 35, 125, 27);
				clase.addItem (datosetiquetas.devuelveEtiquetas("eclase", DatosUsuario.idioma));
				clase.setForeground(new Color(0, 102, 153));
				add(clase);
				
				asignatura = new JComboBox();
				asignatura.setFont(new Font("Segoe Print", asignatura.getFont().getStyle() | Font.BOLD, 15));
				asignatura.setBounds(43, 84, 125, 27);
				asignatura.addItem (datosetiquetas.devuelveEtiquetas("easignatura", DatosUsuario.idioma));
				asignatura.setForeground(new Color(0, 102, 153));
				add(asignatura);
				
				tipo = new JComboBox();
				tipo.setFont(new Font("Segoe Print", tipo.getFont().getStyle() | Font.BOLD, 18));
				tipo.setBounds(211, 83, 125, 27);
				tipo.addItem (datosetiquetas.devuelveEtiquetas("etipo", DatosUsuario.idioma));
				tipo.addItem(datosetiquetas.devuelveEtiquetas("eexamen", DatosUsuario.idioma));//		falta
				tipo.addItem(datosetiquetas.devuelveEtiquetas("etrabajo", DatosUsuario.idioma));//	añadir
				tipo.addItem(datosetiquetas.devuelveEtiquetas("eexcursion", DatosUsuario.idioma));//	a HashMap
				tipo.setForeground(new Color(0, 102, 153));
				add(tipo);
				
				eligeDia = new JDateChooser();
				eligeDia.setBounds(380, 84, 125, 27);
				add(eligeDia);
				
				ItemListener cambiaComboBoxClase = new ItemListener(){
					public void itemStateChanged(ItemEvent eventoCombo){
							if(eventoCombo.getStateChange() == ItemEvent.SELECTED){
							clase.removeAllItems();			
							String cursoPru = curso.getSelectedItem().toString();
							try{
							arrayClase = dclase.getClaseProf(cursoPru,datosUsuario.getId());
							
							for(int c=0;c<arrayClase.size();c++){
								JsonObject curso = arrayClase.getJsonObject(c);
								clase.addItem(curso.getString("nombre_clase"));
							}
							}catch(Exception e){
								
							}
							
						}
					}
				};
				curso.addItemListener(cambiaComboBoxClase);
				
				ItemListener cambiaComboBoxAlumno = new ItemListener(){
					public void itemStateChanged(ItemEvent eventoCombo){
							if(eventoCombo.getStateChange() == ItemEvent.SELECTED){
							//alumnos.removeAllItems();
							asignatura.removeAllItems();
							
							String clases = clase.getSelectedItem().toString();
							try{
							arrayAsignaturas=dasignatura.getAsignaturaProf(datosUsuario.getId(), clases);
							idnomEventos = new String[arrayAsignaturas.size()];//ESTOS ARRAYS SON PARA ALMACENAR LOS NOMBRES DE APELLIDOS Y LOS ID DE ALUMNO
							idEventos = new String[arrayAsignaturas.size()];   //PARA LUEGO COMPARARLOS
							
							for(int w=0;w<arrayAsignaturas.size();w++){
								JsonObject Asignatura = arrayAsignaturas.getJsonObject(w);
								asignatura.addItem(Asignatura.getString("idasignatura")+" "+Asignatura.getString("nombre"));				
								idEventos[w]=Asignatura.getString("idasignatura");//SE RELLENAN LOS ARRAYS, ANTERIORMENTE COMENTADOS
								idnomEventos[w]=Asignatura.getString("idasignatura")+" "+Asignatura.getString("nombre");		
							}
							}catch(Exception e){
								
							}
							}
							
							
						}
					
				};
				clase.addItemListener(cambiaComboBoxAlumno);
				
				//Creamos un textArea para escribir la tarea
				asuntoTarea = new JTextArea();
				asuntoTarea.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, new Color(0, 0, 255), null, null));
				asuntoTarea.setFont(new Font("Segoe Print", asuntoTarea.getFont().getStyle(), 18));
				asuntoTarea.setLineWrap(true);
				asuntoTarea.setBounds(43, 165, 462, 302);
				add(asuntoTarea);
				
				//Boton para enviar la tarea
				bEnviar = new JButton(datosetiquetas.devuelveEtiquetas("eenviar", DatosUsuario.idioma));
				bEnviar.setFont(new Font("Segoe Print", bEnviar.getFont().getStyle() | Font.BOLD, 20));
				bEnviar.setForeground(new Color(0, 102, 153));
				bEnviar.setBounds(515, 440, 125, 27);
				add(bEnviar);
				
				//Boton para cancelar la tarea
				btnCancelar = new JButton(datosetiquetas.devuelveEtiquetas("ecancelar", DatosUsuario.idioma));
				btnCancelar.setFont(new Font("Segoe Print", btnCancelar.getFont().getStyle() | Font.BOLD, 20));
				btnCancelar.setForeground(new Color(0, 102, 153));
				btnCancelar.setBounds(650, 440, 125, 27);
				add(btnCancelar);
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e){
						mediano.dispose();
					}
				});
				
				JLabel lblnombre = new JLabel(datosetiquetas.devuelveEtiquetas("enomev", DatosUsuario.idioma));
				lblnombre.setFont(new Font("Segoe Print", Font.BOLD, 14));
				lblnombre.setForeground(new Color (0,4,120));
				lblnombre.setBounds(43, 140, 156, 14);
				add(lblnombre);
				
				comboBoxNombre = new JTextField();
				comboBoxNombre.setBounds(183, 138, 322, 20);
				add(comboBoxNombre);
				comboBoxNombre.setColumns(10);
				
				//fondo ventana
				fondo = new JLabel("");
				fondo.setIcon(new ImageIcon(VistaTareas.class.getResource("/images/FONDO VISTAS.png")));
				fondo.setBounds(0, 0, 800, 500);
				add(fondo);
				
				bEnviar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String codAsig=null;
						
						for(int i=0;i<asignatura.getItemCount();i++){
							if(asignatura.getSelectedItem().equals(idnomEventos[i])){
								codAsig=idEventos[i];
								break;
							}
						}
						
						int año = eligeDia.getCalendar().get(Calendar.YEAR);
		                int mes = eligeDia.getCalendar().get(Calendar.MONTH) ;
		                int dia = eligeDia.getCalendar().get(Calendar.DAY_OF_MONTH);
		                String fecha=año+"-"+(mes+1)+"-"+dia;
		                System.out.println(fecha);
						datosEventos=new DatosEventos();
						String parametro=datosEventos.setEventos(comboBoxNombre.getText(), tipo.getSelectedItem().toString(), fecha, asuntoTarea.getText(), codAsig, datosUsuario.getId());
					
						if (parametro.equals("ok")){
							FramePeq peque= new FramePeq();
							peque.setVisible (true);
							peque.abreVistaNotificacion(datosetiquetas.devuelveEtiquetas("eNotEv", DatosUsuario.idioma),peque);
							mediano.dispose();
							
						}else{
							FramePeq peque= new FramePeq();
							peque.setVisible (true);
							peque.abreVistaNotificacion(datosetiquetas.devuelveEtiquetas("eNotEvMal", DatosUsuario.idioma),peque);
							mediano.dispose();
						}
					}
				});
	}
	
}

