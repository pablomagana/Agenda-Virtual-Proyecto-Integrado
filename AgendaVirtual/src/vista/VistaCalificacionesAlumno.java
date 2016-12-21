package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.json.JsonArray;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import modelo.DatosHashMap;
import modelo.DatosUsuario;
import modelo.DatosCalificaciones;
import modelo.DatosAsignatura;

public class VistaCalificacionesAlumno extends JPanel {
	private JLabel fondo,labelDestinatario0;
	private JComboBox despegableAsig,despegableClase,despegableCurso,despegableAlumno ,despegableNota;
	private FrameMediano medi;
	private JTextArea textArea;
	private JButton bEnviar,btnCancelar;
	private DatosHashMap datosetiquetas=DatosHashMap.getinstance();
	private DatosCalificaciones datosCalificaciones=new DatosCalificaciones();
	private JTable table;
	private JScrollPane scrollPane;
	
	
	private String[] columnas = {datosetiquetas.devuelveEtiquetas("eprofesor", DatosUsuario.idioma),datosetiquetas.devuelveEtiquetas("easignatura", DatosUsuario.idioma),datosetiquetas.devuelveEtiquetas("enota", DatosUsuario.idioma),datosetiquetas.devuelveEtiquetas("ecomentarios", DatosUsuario.idioma)};
	private  JsonArray arrayCalificaciones;
	public VistaCalificacionesAlumno(FrameMediano medi,DatosUsuario datos) {
		this.medi=medi;
		setForeground(SystemColor.activeCaption);
		setBackground(SystemColor.activeCaption);
		setBorder(null);
		setLayout(null);
		
		try{
			arrayCalificaciones = datosCalificaciones.getCalificacionesAlumno(datos.getId());//DEVUELVE ARRAY CON LOS OBJETOS DE LOS MENSAJES

			}catch(Exception e){
				arrayCalificaciones=null;
			}
		
		labelDestinatario0 = new JLabel(datosetiquetas.devuelveEtiquetas("ecalificaciones", DatosUsuario.idioma));
		labelDestinatario0.setBounds(42, 76, 128, 31);
		labelDestinatario0.setFont(new Font("Segoe Print", Font.BOLD, 19));
		add(labelDestinatario0);
	
		btnCancelar =new JButton(datosetiquetas.devuelveEtiquetas("eatras", DatosUsuario.idioma));
		btnCancelar.setBounds(27, 21, 125, 27);
		btnCancelar.setFont(new Font("Segoe Print", btnCancelar.getFont().getStyle() | Font.BOLD, 20));
		btnCancelar.setForeground(new Color(0, 102, 153));
		add(btnCancelar);
		
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				medi.setVisible(false);
			}
		});
		
		table = new JTable(toArrayCalificaciones(arrayCalificaciones),columnas);
		table.setEnabled(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(170);
		table.getColumnModel().getColumn(1).setPreferredWidth(170);
		table.getColumnModel().getColumn(2).setPreferredWidth(52);
		table.getColumnModel().getColumn(3).setPreferredWidth(330);
		
		scrollPane = new JScrollPane (table);
		scrollPane.setEnabled(false);
		scrollPane.setBounds(42, 118, 722, 319);
		add(scrollPane);
		
		
		fondo = new JLabel("");
		fondo.setBounds(0, 0, 800, 500);
		fondo.setIcon(new ImageIcon(VistaTareas.class.getResource("/images/FONDO VISTAS5.png")));
		add(fondo);
		
	}
	public Object[][] toArrayCalificaciones(JsonArray calificaciones){
		try{
		Object[][] filacalificaciones = new Object[calificaciones.size()][4];
		for(int i=0;i<calificaciones.size();i++){
			filacalificaciones[i][0] = calificaciones.getJsonObject(i).getString("nom_prof")+" "+calificaciones.getJsonObject(i).getString("ape_prof");
			filacalificaciones[i][1] = calificaciones.getJsonObject(i).getString("nom_asignatura");
			filacalificaciones[i][2] = calificaciones.getJsonObject(i).getString("nota");
			filacalificaciones[i][3] = calificaciones.getJsonObject(i).getString("comentarios");
		}
		return filacalificaciones;
		}catch(Exception e){
			Object[][] filacalificaciones = new Object[0][4];
			return filacalificaciones;
		}
	}
	
	public String toAsignatura(String id){
		String nombreAsignatura="";
		DatosAsignatura dasignaturas=new DatosAsignatura();
		nombreAsignatura=dasignaturas.getAsignaturaSimple(id);
		return nombreAsignatura;
	}
	
}

