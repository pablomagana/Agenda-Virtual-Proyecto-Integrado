package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;

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
import modelo.DatosClase;
import modelo.DatosCurso;
import modelo.DatosAsignatura;
import modelo.DatosEventos;
import modelo.DatosHashMap;
import modelo.DatosTareas;
import modelo.DatosUsuario;

import javax.swing.JTextField;

public class VistaTareas extends JPanel {
	
	private DatosHashMap datosetiquetas=DatosHashMap.getinstance();
	
	private JComboBox curso, clase,  asignatura,  tipo, alumnos; 
	private JDateChooser eligeDia;
	private JTextArea descripcion;
	private JLabel fondo;
	private JButton bEnviar;
	private JButton bCancelar;
	private FrameMediano medi;

	private JsonArray arrayClase,arrayCursos,arrayAsignaturas;
	
	private DatosCurso dcursos;
	private DatosClase dclase;
	private DatosAsignatura dasignatura;
	private DatosTareas dtareas;
	
	private JTextField nombre;
	private String[] idTarea;
	private String[] idnomTarea;
	public VistaTareas(FrameMediano mediano,DatosUsuario datosUsuario) {
		
		setForeground(SystemColor.activeCaption);
		setBackground(SystemColor.activeCaption);
		setBorder(null);
		setLayout(null);
		
		dcursos=new DatosCurso();
		dclase=new DatosClase();
		dasignatura=new DatosAsignatura();
		dtareas=new DatosTareas();
		//creamos los distintos desplegables y la fecha para seleccionar y enviar la tarea
		
				curso = new JComboBox();
				curso.setFont(new Font("Segoe Print", curso.getFont().getStyle() | Font.BOLD, 18));
				curso.setBounds(43, 35, 125, 27);
				curso.addItem (datosetiquetas.devuelveEtiquetas("ecurso", DatosUsuario.idioma));
				curso.setForeground(new Color(0, 102, 153));
				add(curso);
				
				try{
				arrayCursos = dcursos.getCursosProf(datosUsuario.getId());//OBJETO DE JSONARRAY
				
				for(int c=0;c<arrayCursos.size();c++){
					JsonObject cursojson = arrayCursos.getJsonObject(c);
					curso.addItem(cursojson.getString("nombre"));
				}
				}catch(Exception e){
					
				}
				clase = new JComboBox();
				clase.setFont(new Font("Segoe Print", clase.getFont().getStyle() | Font.BOLD, 18));
				clase.setBounds(212, 35, 125, 27);
				clase.addItem (datosetiquetas.devuelveEtiquetas("eclase", DatosUsuario.idioma));
				clase.setForeground(new Color(0, 102, 153));
				add(clase);
				/*
				alumnos = new JComboBox();
				alumnos.setFont(new Font("Segoe Print", alumnos.getFont().getStyle() | Font.BOLD, 18));
				alumnos.setBounds(380, 35, 125, 27);
				alumnos.addItem (datosetiquetas.devuelveEtiquetas("ealumno", DatosUsuario.idioma));
				alumnos.setForeground(new Color(0, 102, 153));
				add(alumnos);
				*/
				asignatura = new JComboBox();
				asignatura.setFont(new Font("Segoe Print", asignatura.getFont().getStyle() | Font.BOLD, 15));
				asignatura.setBounds(43, 84, 125, 27);
				asignatura.addItem (datosetiquetas.devuelveEtiquetas("easignatura", DatosUsuario.idioma));
				asignatura.setForeground(new Color(0, 102, 153));
				add(asignatura);
				/*
				tipo = new JComboBox();
				tipo.setFont(new Font("Segoe Print", tipo.getFont().getStyle() | Font.BOLD, 18));
				tipo.setBounds(212, 84, 125, 27);
				tipo.addItem (datosetiquetas.devuelveEtiquetas("etipo", DatosUsuario.idioma));
				tipo.addItem("examen");//		falta
				tipo.addItem("trabajo");//	añadir
				tipo.addItem("excursión");//	a HashMap
				tipo.setForeground(new Color(0, 102, 153));
				add(tipo);
				*/
				eligeDia = new JDateChooser();
				eligeDia.setBounds(380, 84, 125, 27);
				add(eligeDia);
				

				ItemListener cambiaComboBoxClase = new ItemListener(){
					public void itemStateChanged(ItemEvent eventoCombo){
							if(eventoCombo.getStateChange() == ItemEvent.SELECTED){
							clase.removeAllItems();			
							String cursoPru = curso.getSelectedItem().toString();
							try{
							arrayClase = dclase.getClaseProf(cursoPru, datosUsuario.getId());
							
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
							
							//arrayAlumno = dalumno.getAlumno(clases);
							try{
							arrayAsignaturas=dasignatura.getAsignaturaProf(datosUsuario.getId(), clases);
							idnomTarea = new String[arrayAsignaturas.size()];//ESTOS ARRAYS SON PARA ALMACENAR LOS NOMBRES DE APELLIDOS Y LOS ID DE ALUMNO
							idTarea = new String[arrayAsignaturas.size()];   //PARA LUEGO COMPARARLOS
							
							for(int w=0;w<arrayAsignaturas.size();w++){
								JsonObject Asignatura = arrayAsignaturas.getJsonObject(w);
								
								asignatura.addItem(Asignatura.getString("idasignatura")+" "+Asignatura.getString("nombre"));	
								idTarea[w]=Asignatura.getString("idasignatura");//SE RELLENAN LOS ARRAYS, ANTERIORMENTE COMENTADOS
								idnomTarea[w]=Asignatura.getString("idasignatura")+" "+Asignatura.getString("nombre");	
							}
							}catch(Exception e){
								
							}
							
							
						}
						}
					
				};
				clase.addItemListener(cambiaComboBoxAlumno);
			
				nombre = new JTextField();
				nombre.setBounds(43, 150, 462, 27);
				add(nombre);
				nombre.setColumns(10);
				
				//Creamos un textArea para escribir la tarea
				descripcion = new JTextArea();
				descripcion.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, new Color(0, 0, 255), null, null));
				descripcion.setFont(new Font("Segoe Print", descripcion.getFont().getStyle(), 18));
				descripcion.setLineWrap(true);
				descripcion.setBounds(43, 196, 462, 237);
				add(descripcion);
				
				//Boton para enviar la tarea
				bEnviar = new JButton(datosetiquetas.devuelveEtiquetas("eenviar", DatosUsuario.idioma));
				bEnviar.setFont(new Font("Segoe Print", bEnviar.getFont().getStyle() | Font.BOLD, 20));
				bEnviar.setForeground(new Color(0, 102, 153));
				bEnviar.setBounds(515, 407, 125, 27);
				add(bEnviar);
				
				bEnviar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String codAsig=null;
						
						for(int i=0;i<asignatura.getItemCount();i++){
							if(asignatura.getSelectedItem().equals(idnomTarea[i])){
								codAsig=idTarea[i];
								break;
							}
						}
						
						int año = eligeDia.getCalendar().get(Calendar.YEAR);
		                int mes = eligeDia.getCalendar().get(Calendar.MONTH) ;
		                int dia = eligeDia.getCalendar().get(Calendar.DAY_OF_MONTH);
		                String fecha=año+"-"+(mes+1)+"-"+dia;
		                
						
						String parametro=dtareas.setTareas(nombre.getText(), fecha, descripcion.getText(), codAsig, datosUsuario.getId());
						if (parametro.equals("ok")){
							FramePeq peque= new FramePeq();
							peque.setVisible (true);
							peque.abreVistaNotificacion(datosetiquetas.devuelveEtiquetas("eNottar", DatosUsuario.idioma),peque);
							mediano.dispose();
							
						}else{
							FramePeq peque= new FramePeq();
							peque.setVisible (true);
							peque.abreVistaNotificacion(datosetiquetas.devuelveEtiquetas("eNotEvMal", DatosUsuario.idioma),peque);
							mediano.dispose();
						}
					}
					
				});
				
				bCancelar = new JButton(datosetiquetas.devuelveEtiquetas("ecancelar", DatosUsuario.idioma));
				bCancelar.setFont(new Font("Segoe Print", bCancelar.getFont().getStyle() | Font.BOLD, 20));
				bCancelar.setForeground(new Color(0, 102, 153));
				bCancelar.setBounds(650, 407, 125, 27);
				add(bCancelar);
				bCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e){
						mediano.dispose();
					}
				});
				
				
				
				//fondo ventana
				fondo = new JLabel("");
				fondo.setIcon(new ImageIcon(VistaTareas.class.getResource("/images/FONDO VISTAS.png")));
				fondo.setBounds(0, 0, 800, 500);
				add(fondo);
	}
}
