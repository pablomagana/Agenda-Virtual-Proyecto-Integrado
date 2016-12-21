package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;

import modelo.DatosAlumno;
import modelo.DatosAsignatura;
import modelo.DatosClase;
import modelo.DatosCurso;
import modelo.DatosHashMap;
import modelo.DatosUsuario;
import modelo.DatosCalificaciones;

import com.toedter.calendar.JDateChooser;

import javax.swing.ImageIcon;
import javax.swing.DefaultComboBoxModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class VistaCalificacionesProfesor extends JPanel {
	private JLabel fondo,lblComentarios;
	private JComboBox despegableAsig,despegableClase,despegableCurso,despegableAlumno ,despegableNota;
	private FrameMediano medi;
	private JTextArea textArea;
	private JButton bEnviar,btnCancelar;
	private DatosHashMap datosetiquetas= DatosHashMap.getinstance();
	
	private JsonArray arrayClase,arrayAlumno,arrayCursos,arrayAsignaturas;
	
	private DatosCurso dcursos;
	private DatosClase dclase;
	private DatosAlumno dalumno;
	private DatosAsignatura dasignatura;
	private DatosCalificaciones dcalificaciones;
	
	private String[] idalumnos;
	private String[] nomApellidos;
	
	private String[] idTarea;
	private String[] idNomTarea;
	
	
	public VistaCalificacionesProfesor(FrameMediano medi,DatosUsuario datos) {

		this.medi=medi;
		setForeground(SystemColor.activeCaption);
		setBackground(SystemColor.activeCaption);
		setBorder(null);
		setLayout(null);
		
		dcursos=new DatosCurso();
		dclase=new DatosClase();
		dalumno=new DatosAlumno();
		dasignatura=new DatosAsignatura();
		dcalificaciones=new DatosCalificaciones();
		
		
		despegableCurso = new JComboBox();
		despegableCurso.setFont(new Font("Segoe Print", Font.BOLD, 18));
		despegableCurso.setModel(new DefaultComboBoxModel(new String[] {datosetiquetas.devuelveEtiquetas("ecurso", DatosUsuario.idioma)}));
		despegableCurso.setForeground(new Color(0, 102, 153));
		despegableCurso.setBounds(67, 25, 125, 27);
		add(despegableCurso);
		
		try{
		arrayCursos = dcursos.getCursosProf(datos.getId());//AUNQUE SE LLAME ARRAY, NO ES UN ARRAY, ES UN OBJETO DE JSONARRAY
		
		//Rellenamos cursos
		for(int c=0;c<arrayCursos.size();c++){
			JsonObject cursojson = arrayCursos.getJsonObject(c);
			despegableCurso.addItem(cursojson.getString("nombre"));
		}
		}catch(Exception e){
			
		}
		
		despegableClase = new JComboBox();
		despegableClase.setFont(new Font("Segoe Print", Font.BOLD, 18));
		despegableClase.setModel(new DefaultComboBoxModel(new String[] {datosetiquetas.devuelveEtiquetas("eclase", DatosUsuario.idioma)}));
		despegableClase.setForeground(new Color(0, 102, 153));
		despegableClase.setBounds(234, 25, 125, 27);
		add(despegableClase);
		
		despegableAlumno = new JComboBox();
		despegableAlumno.setFont(new Font("Segoe Print", Font.BOLD, 18));
		despegableAlumno.setModel(new DefaultComboBoxModel(new String[] {datosetiquetas.devuelveEtiquetas("ealumno", DatosUsuario.idioma)}));
		despegableAlumno.setForeground(new Color(0, 102, 153));
		despegableAlumno.setBounds(424, 19, 239, 39);
		add(despegableAlumno);
		
		despegableAsig = new JComboBox();
		despegableAsig.setFont(new Font("Segoe Print", Font.BOLD, 18));
		despegableAsig.setModel(new DefaultComboBoxModel(new String[] {datosetiquetas.devuelveEtiquetas("easignatura", DatosUsuario.idioma)}));
		despegableAsig.setForeground(new Color(0, 102, 153));
		despegableAsig.setBounds(95, 83, 192, 39);
		add(despegableAsig);
	
		despegableNota = new JComboBox();
		despegableNota.setFont(new Font("Segoe Print", Font.BOLD, 18));
		despegableNota.setModel(new DefaultComboBoxModel(new String[] {datosetiquetas.devuelveEtiquetas("enota", DatosUsuario.idioma), "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
		despegableNota.setForeground(new Color(0, 102, 153));
		despegableNota.setBounds(317, 83, 125, 39);
		add(despegableNota);
		
		ItemListener cambiaComboBoxClase = new ItemListener(){
			public void itemStateChanged(ItemEvent eventoCombo){
					if(eventoCombo.getStateChange() == ItemEvent.SELECTED){
						despegableClase.removeAllItems();			
						String cursoPru = despegableCurso.getSelectedItem().toString();
						try{
						arrayClase = dclase.getClaseProf(cursoPru,datos.getId());
					
					for(int c=0;c<arrayClase.size();c++){
						JsonObject curso = arrayClase.getJsonObject(c);
						despegableClase.addItem(curso.getString("nombre_clase"));
					}
						}catch(Exception e){
							
						}
				}
			}
		};

		despegableCurso.addItemListener(cambiaComboBoxClase);
		
		
		ItemListener cambiaComboBoxAlumno = new ItemListener(){
			public void itemStateChanged(ItemEvent eventoCombo){
					if(eventoCombo.getStateChange() == ItemEvent.SELECTED){
						despegableAlumno.removeAllItems();
						despegableAsig.removeAllItems();
					
					
					try{
						String clases = despegableClase.getSelectedItem().toString();
						arrayAlumno = dalumno.getAlumno(clases);
						arrayAsignaturas= dasignatura.getAsignaturaProf(datos.getId(),clases);
						nomApellidos = new String[arrayAlumno.size()];//ESTOS ARRAYS SON PARA ALMACENAR LOS NOMBRES DE APELLIDOS Y LOS ID DE ALUMNO
						idalumnos = new String[arrayAlumno.size()];   //PARA LUEGO COMPARARLOS
						idTarea=new String[arrayAsignaturas.size()];
						idNomTarea=new String[arrayAsignaturas.size()];
						
					
					}catch(Exception e){
						//cambiar por ventana error
						despegableAlumno.addItem(datosetiquetas.devuelveEtiquetas("esinalumnos", DatosUsuario.idioma));
					}
					for(int w=0;w<arrayAsignaturas.size();w++){
						JsonObject Asignatura = arrayAsignaturas.getJsonObject(w);
						despegableAsig.addItem(Asignatura.getString("idasignatura")+" "+Asignatura.getString("nombre"));	
						idTarea[w]=Asignatura.getString("idasignatura");//SE RELLENAN LOS ARRAYS, ANTERIORMENTE COMENTADOS
						idNomTarea[w]=Asignatura.getString("idasignatura")+" "+Asignatura.getString("nombre");	
					}
					
					for(int j=0;j<arrayAlumno.size();j++){
						JsonObject clase = arrayAlumno.getJsonObject(j);
						despegableAlumno.addItem((clase.getString("nombre"))+" "+(clase.getString("apellidos")));
						idalumnos[j]=clase.getString("idalumno");//SE RELLENAN LOS ARRAYS, ANTERIORMENTE COMENTADOS
						nomApellidos[j]=clase.getString("nombre")+" "+clase.getString("apellidos");		
					}
				}
			}
		};
		despegableClase.addItemListener(cambiaComboBoxAlumno);
		
		
        lblComentarios = new JLabel(datosetiquetas.devuelveEtiquetas("ecomentarios", DatosUsuario.idioma));
        lblComentarios.setFont(new Font("Arial Black", Font.PLAIN, 14));
		lblComentarios.setBounds(48, 139, 156, 14);
		add(lblComentarios);
		
		textArea = new JTextArea();
		textArea.setBounds(48, 164, 462, 302);
		add(textArea);
		
		bEnviar = new JButton(datosetiquetas.devuelveEtiquetas("eenviar", DatosUsuario.idioma));
		bEnviar.setFont(new Font("Segoe Print", bEnviar.getFont().getStyle() | Font.BOLD, 20));
		bEnviar.setForeground(new Color(0, 102, 153));
		bEnviar.setBounds(515, 439, 125, 27);
		add(bEnviar);
		bEnviar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String codAsig=null;
				String usuarioa=null;
				
				for(int i=0;i<despegableAsig.getItemCount();i++){
					if(despegableAsig.getSelectedItem().equals(idNomTarea[i])){
						codAsig=idTarea[i];
						break;
					}
				}
				for(int i=0;i<despegableAlumno.getItemCount();i++){
					if(despegableAlumno.getSelectedItem().equals(nomApellidos[i])){
						usuarioa=idalumnos[i];
						break;
					}
				}
				if(despegableAlumno.getSelectedItem()!=datosetiquetas.devuelveEtiquetas("esinalumnos", DatosUsuario.idioma)){
					String parametro=dcalificaciones.setCalificaciones(datos.getId(), usuarioa, codAsig, despegableNota.getSelectedItem().toString(), textArea.getText());
					if (parametro.equals("ok")){
						FramePeq peque= new FramePeq();
						peque.setVisible (true);
						peque.abreVistaNotificacion(datosetiquetas.devuelveEtiquetas("eCalEv", DatosUsuario.idioma),peque);
						medi.dispose();
						
					}else{
						FramePeq peque= new FramePeq();
						peque.setVisible (true);
						peque.abreVistaNotificacion(datosetiquetas.devuelveEtiquetas("eNotEvMal", DatosUsuario.idioma),peque);
						medi.dispose();
					}
				}
			}
		});
		
		btnCancelar = new JButton(datosetiquetas.devuelveEtiquetas("ecancelar", DatosUsuario.idioma));
		btnCancelar.setFont(new Font("Segoe Print", btnCancelar.getFont().getStyle() | Font.BOLD, 20));
		btnCancelar.setForeground(new Color(0, 102, 153));
		btnCancelar.setBounds(645, 439, 125, 27);
		add(btnCancelar);
		
		btnCancelar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				medi.dispose();
			}
		});
				
		fondo = new JLabel("");
		fondo.setIcon(new ImageIcon(VistaTareas.class.getResource("/images/FONDO VISTAS.png")));
		fondo.setBounds(0, 0, 800, 500);
		add(fondo);
				
			
	}
}
