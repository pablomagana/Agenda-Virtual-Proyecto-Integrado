package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.json.JsonArray;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import modelo.DatosHashMap;
import modelo.DatosMensaje;
import modelo.DatosUsuario;

public class VistaMensajeAlumno extends JPanel {
	
	private DatosHashMap datosetiquetas=DatosHashMap.getinstance();
	
		private FrameMediano vistamediana;
	
		//LABELS ASUNTO, DESTINATARIO, FECHA Y FONDO. ESTOS NO SE PUEDEN AUTOMATIZAR YA QUE LA INFORMACION
		//SIEMPRE SERÁ LA MISMA Y NO CAMBIARÁ
		private JLabel fondo;
		
		private DatosMensaje datosMensajes = new DatosMensaje();
		
		//ARRAYS PARA CREAR LOS LABELS DE ASUNTO, DESTINATARIO Y FECHA
		private JLabel[] imagenBotonBorrar = new JLabel[15];
		
		private JScrollPane scrollPane;
		
		private JButton button;
		
		//ARRAYLIST QUE ALMACENARÁ LOS ID DE MENSAJES
		private ArrayList<String> id = new ArrayList<String>();
		
		private JTable table;
		
		//COLUMNAS DEL JTABLE
		private String[] columnas = {
				datosetiquetas.devuelveEtiquetas("erecibidode", DatosUsuario.idioma),
				datosetiquetas.devuelveEtiquetas("easunto", DatosUsuario.idioma)};
		
		private JsonArray arrayMensajes;
		
	public VistaMensajeAlumno(FrameMediano vistamediana,DatosUsuario datosUsuario) {
		setLayout(null);
		
		//SI EL PROFESOR HA ENVIADO ALGÚN CORREO, SE MOSTRARÁ EN EL JTABLE, SI NO SE MOSTRARÁ UNA TABLA VACÍA
		this.vistamediana = vistamediana;
		try{
		arrayMensajes = datosMensajes.getMensajeAlumno(datosUsuario.getId());//DEVUELVE ARRAY CON LOS OBJETOS DE LOS MENSAJES
		
		}catch(Exception e){
			
		}	 
					
		//BOTON ATRÁS
		button = new JButton(datosetiquetas.devuelveEtiquetas("eatras", DatosUsuario.idioma));
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				vistamediana.setVisible(false);
			}
		});
		button.setBounds(68, 32, 89, 23);
		add(button);
		
		//SI HAY ALGÚN MENSAJE EN LA TABLA Y SE HACE CLICK EN ALGUNO DE ELLOS, SE ABRIRÁ VISTALEERMENSAJE, SI NO ATRAPARÁ LA EXCEPCIÓN NULLPOINTEREXCEPTION
		try{
			table = new JTable(toArrayMensajes(arrayMensajes),columnas);
			table.setRowSelectionAllowed(true);
			add(table);
			table.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent arg0) {
					int fila = table.getSelectedRow();
					String identificador = (id.get(fila));
					FrameIntermedio intermedio = new FrameIntermedio();
					intermedio.setVisible(true);
					intermedio.cambiaPanelLeerMensaje(identificador, intermedio,datosMensajes);
				}
			});
		}catch(Exception e){
			table = new JTable();
			add(table);
		}
			
		scrollPane = new JScrollPane(table);
		scrollPane.setEnabled(false);
		scrollPane.setBounds(42, 118, 722, 319);
		add(scrollPane);
		
		fondo = new JLabel("");
		fondo.setIcon(new ImageIcon(VistaMensajeProfesor.class.getResource("/images/FONDO VISTAS5.png")));
		fondo.setBounds(-22, -21, 832, 523);
		add(fondo);

	}
	
	//SE RELLENARÁ EL ARRAYMENSAJES CON EL NOMBRE, APELLIDO DEL PROFESOR Y EL ASUNTO Y EL ARRAYLIST CON LOS ID DE MENSAJES
	public Object[][] toArrayMensajes(JsonArray mensajes){
		try{
		Object[][] filamensajes = new Object[mensajes.size()][2];
		for(int i=0;i<mensajes.size();i++){
			id.add(mensajes.getJsonObject(i).getString("idmensajes"));
			filamensajes[i][0] = mensajes.getJsonObject(i).getString("nom_prof")+" "+mensajes.getJsonObject(i).getString("ape_prof");
			filamensajes[i][1] = mensajes.getJsonObject(i).getString("asunto");
		}
		return filamensajes;
		}catch(Exception e){
			e.printStackTrace();
			Object[][] filamensajes = new Object[0][2];
			return filamensajes;
		}
	}
	
	public JButton getButton() {
		return button;
	}
	
}
