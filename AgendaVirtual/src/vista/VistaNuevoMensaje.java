package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.PrintStream;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import modelo.DatosAlumno;
import modelo.DatosClase;
import modelo.DatosCurso;
import modelo.DatosHashMap;
import modelo.DatosMensaje;
import modelo.DatosUsuario;

public class VistaNuevoMensaje extends JPanel {
	
	private DatosHashMap datosetiquetas=DatosHashMap.getinstance();

	//TAMAÑO JPANEL 800X500
	public JComboBox comboBoxCurso, comboBoxClase, comboBoxAlumnos;
	public JTextField jtextAsunto;
	private JLabel labelAsunto, fondo;
	public JTextArea escribirMensaje;
	private JButton botonEnviar, botonCancelar;

	private DatosCurso cursos;
	private DatosClase clase;
	private DatosAlumno alumno;
	private DatosMensaje mensajes;
	
	private JsonArray arrayClase, arrayAlumno, arrayCursos;
	
	public String[] nomApellidos;
	public String[] idalumnos;	
		
	public VistaNuevoMensaje(FrameMediano vistamediana,DatosUsuario datosUsuario) {
		setLayout(null);
		
		mensajes = new DatosMensaje();
		comboBoxCurso = new JComboBox();
		comboBoxCurso.setFont(new Font("Segoe Print", Font.BOLD, 12));
		
		comboBoxCurso.setToolTipText(datosetiquetas.devuelveEtiquetas("ecurso", DatosUsuario.idioma));
		comboBoxCurso.setBounds(45, 44, 102, 20);
		add(comboBoxCurso);
		comboBoxCurso.addItem(datosetiquetas.devuelveEtiquetas("ecurso", DatosUsuario.idioma));
		
		cursos=new DatosCurso();
		arrayCursos = cursos.getCursosProf(datosUsuario.getId());
		
			for(int c=0;c<arrayCursos.size();c++){
				JsonObject curso = arrayCursos.getJsonObject(c);
				comboBoxCurso.addItem(curso.getString("nombre"));
			}
		
		comboBoxClase = new JComboBox();
		comboBoxClase.setFont(new Font("Segoe Print", Font.BOLD, 12));
		comboBoxClase.setToolTipText(datosetiquetas.devuelveEtiquetas("eclase", DatosUsuario.idioma));
		comboBoxClase.setBounds(219, 44, 145, 20);
		add(comboBoxClase);
		comboBoxClase.addItem(datosetiquetas.devuelveEtiquetas("eclase", DatosUsuario.idioma));
		clase = new DatosClase();
		
		comboBoxAlumnos = new JComboBox();
		comboBoxAlumnos.setFont(new Font("Segoe Print", Font.BOLD, 12));
		comboBoxAlumnos.setToolTipText(datosetiquetas.devuelveEtiquetas("ealumno", DatosUsuario.idioma));
		comboBoxAlumnos.setBounds(401, 44, 226, 20);
		add(comboBoxAlumnos);
		comboBoxAlumnos.addItem(datosetiquetas.devuelveEtiquetas("ealumno", DatosUsuario.idioma));
		alumno = new DatosAlumno();
		
		botonEnviar = new JButton(datosetiquetas.devuelveEtiquetas("eenviar", DatosUsuario.idioma));
		botonEnviar.setFont(new Font("Segoe Print", Font.BOLD, 14));
		botonEnviar.setBounds(651, 432, 102, 23);
		add(botonEnviar);
		
		jtextAsunto = new JTextField();
		jtextAsunto.setBounds(106, 110, 521, 20);
		add(jtextAsunto);
		jtextAsunto.setColumns(10);
		
		labelAsunto = new JLabel(datosetiquetas.devuelveEtiquetas("easunto", DatosUsuario.idioma));
		labelAsunto.setFont(new Font("Segoe Print", Font.BOLD, 14));
		labelAsunto.setForeground(new Color (0,4,120));
		labelAsunto.setBounds(28, 113, 68, 14);
		add(labelAsunto);
		
		escribirMensaje = new JTextArea();
		escribirMensaje.setBounds(28, 143, 599, 312);
		add(escribirMensaje);
		
		botonCancelar = new JButton(datosetiquetas.devuelveEtiquetas("ecancelar", DatosUsuario.idioma));
		botonCancelar.setFont(new Font("Segoe Print", Font.BOLD, 14));
		botonCancelar.setBounds(651, 380, 102, 23);
		add(botonCancelar);
		botonCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				vistamediana.cambiaPanelMensajeProfesor(vistamediana,datosUsuario);
			}
		});
		fondo = new JLabel("");
		fondo.setIcon(new ImageIcon(VistaNuevoMensaje.class.getResource("/images/FONDO VISTAS5.png")));
		fondo.setBounds(0, 0, 800, 500);
		add(fondo);
		
		//LISTENERS PARA COLOCAR LOS ALUMNOS EN EL JCOMBOBOX
		ItemListener cambiaComboBoxClase = new ItemListener(){
			public void itemStateChanged(ItemEvent eventoCombo){//CUANDO EL COMBOBOX DE CURSO SEA SELECCIONADO
				if(eventoCombo.getStateChange() == ItemEvent.SELECTED){//SE RELLENARÁ CON TODAS LAS CLASES DEL CURSO
					try{
						comboBoxClase.removeAllItems();	
						comboBoxClase.addItem(datosetiquetas.devuelveEtiquetas("eclase", DatosUsuario.idioma));
						String cursoPru = comboBoxCurso.getSelectedItem().toString();//SE COGE Y SE PASA A UN STRING EL CURSO SELECCIONADO
						arrayClase = clase.getclase(cursoPru);//SE ACCEDE A DATOSCLASE Y SE RECOGE  EL CURSO MEDIANTE LA CONSULTA DE LA CLASE
					
						for(int c=0;c<arrayClase.size();c++){//ARRAY PARA LLENAR EL COMBOBOX DE CLASE
							JsonObject curso = arrayClase.getJsonObject(c);//SE NECESITA UN JSONOBJECT PORQUE DATOSCLASE ENVIA UN JSONOBJECT
							comboBoxClase.addItem(curso.getString("nombre_clase"));
						}
						//comboBoxClase.setSelectedItem(arrayClase.getJsonArray(0));//SIEMPRE SERÁ SELECCIONADO EL SEGUNDO ITEM ANTES QUE EL PRIMERO. EL PRIMER ITEM ES "CLASE". ESTE MÉTODO LANZA LA EXCEPCIÓN ILLEGALARGUMENTEXCEPTION
						
					}catch(NullPointerException e){
						comboBoxClase.removeAllItems();	
						comboBoxClase.addItem("NO TIENES CLASES");
					}catch(IllegalArgumentException i){
						
					}
				}
			}
		};

		comboBoxCurso.addItemListener(cambiaComboBoxClase);//POR ÚLTIMO SE AÑADE EL LISTENER AL COMBOBOX
		
		
		ItemListener cambiaComboBoxAlumno = new ItemListener(){	
			public void itemStateChanged(ItemEvent eventoCombo){		//CUANDO EL COMBOBOX DE CLASE SEA SELECCIONADO
				if(eventoCombo.getStateChange() == ItemEvent.SELECTED){	//SE RELLENARÁ CON TODAS LOS ALUMNOS DE LA CLASE
					try{
						comboBoxAlumnos.removeAllItems();
						comboBoxAlumnos.addItem(datosetiquetas.devuelveEtiquetas("ealumno", DatosUsuario.idioma));
						String clases = comboBoxClase.getSelectedItem().toString(); //SE COGE Y SE PASA A UN STRING LA CLASE SELECCIONADO
						arrayAlumno = alumno.getAlumno(clases);//SE ACCEDE A DATOSALUMNO Y SE RECOGE EL ALUMNO MEDIANTE LA CONSULTA DE LA CLASE
						nomApellidos = new String[arrayAlumno.size()];//ESTOS ARRAYS SON PARA ALMACENAR LOS NOMBRES DE APELLIDOS Y LOS ID DE ALUMNO
						idalumnos = new String[arrayAlumno.size()];   //PARA LUEGO COMPARARLOS
					
						for(int j=0;j<arrayAlumno.size();j++){						
							JsonObject clase = arrayAlumno.getJsonObject(j);
							comboBoxAlumnos.addItem((clase.getString("nombre"))+" "+(clase.getString("apellidos")));				
							idalumnos[j]=clase.getString("idalumno");//SE RELLENAN LOS ARRAYS, ANTERIORMENTE COMENTADOS
							nomApellidos[j]=clase.getString("nombre")+" "+clase.getString("apellidos");							
						}
					}catch(NullPointerException e){
						comboBoxAlumnos.removeAllItems();
						comboBoxAlumnos.addItem(datosetiquetas.devuelveEtiquetas("eNingunAlumno", DatosUsuario.idioma));
					}
				}
			}
		};
		comboBoxClase.addItemListener(cambiaComboBoxAlumno);
		
		//SI EL PROFESOR ENVIA UN MENSAJE SIN ALUMNOS SALTRÁ FRAMEPEQUEÑO CON EL RESPECTIVO MENSAJE
		botonEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if((comboBoxAlumnos.getSelectedItem()=="No tienes alumnos asignados")||(comboBoxAlumnos.getSelectedItem()=="Alumno")||(comboBoxAlumnos.getSelectedItem()=="No students")){
					FramePeq peque= new FramePeq();
					peque.setVisible (true);
					peque.abreVistaError(datosetiquetas.devuelveEtiquetas("eNoAlumnos", DatosUsuario.idioma),peque);
				}else{//SI EL MENSAJE ES ENVIADO A UN ALUMNO, AL STRING USUARIO SE LE ASIGNARÁ EL IDALUMNO CORRESPONDIENTE, QUE LO COMPARAREMOS CON EL NOMBRE Y APELLIDO DEL COMBOBOX
					String usuario=null;
					for(int i=0;i<comboBoxAlumnos.getItemCount();i++){
						if(comboBoxAlumnos.getSelectedItem().equals(nomApellidos[i])){
							usuario=idalumnos[i];
							break;
						}
					}//SI NO HAY UN ASUNTO O CUERPO ESCRITO SE ENVIARÁ UN TEXTO POR DEFECTO
					if(jtextAsunto.getText()==""){
						jtextAsunto.setText("Sin asunto");;
					}
					if(escribirMensaje.getText()==""){	
						escribirMensaje.setText("Sin contenido de mensaje");
					}//SI EL PARAMETRO RECIBIDO, DESPUÉS DE LLAMAR AL MÉTODO DE ENVIAR EL MENSAJE EN DATOS MENSAJES, ES IGUAL A OK, SALTARÁ EL FRAME PEQUEÑO AVISANDO SI SE HA ENVIADO CORRECTAMENTE O NO
					String parametro = mensajes.setEnviarMensaje(datosUsuario.getId(),usuario,jtextAsunto.getText(),escribirMensaje.getText());
					if (parametro.equals("ok")){
						FramePeq peque= new FramePeq();
						peque.setVisible (true);
						peque.abreVistaNotificacion(datosetiquetas.devuelveEtiquetas("eMensBien", DatosUsuario.idioma),peque);
						vistamediana.dispose();
					}else{
						FramePeq peque= new FramePeq();
						peque.setVisible (true);
						peque.abreVistaError(datosetiquetas.devuelveEtiquetas("eMensMal", DatosUsuario.idioma),peque);
					}
				}
				
			}
		});
		
	}
	
}
