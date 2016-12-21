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


public class VistaMensajeProfesor extends JPanel{
	private DatosHashMap datosetiquetas=DatosHashMap.getinstance();
	
	
	//LABELS ASUNTO, DESTINATARIO, FECHA Y FONDO. ESTOS NO SE PUEDEN AUTOMATIZAR YA QUE LA INFORMACION
	//SIEMPRE SERÁ LA MISMA Y NO CAMBIARÁ
	private JLabel fondo ;

	private DatosMensaje datosMensajes = new DatosMensaje();
	
	private JScrollPane scrollPane;
	
	private JButton botonNuevoMensaje, btnAtras;
	
	private JTable table;
	
	private String[] columnas = {
			datosetiquetas.devuelveEtiquetas("edestinatario", DatosUsuario.idioma),
			datosetiquetas.devuelveEtiquetas("easunto", DatosUsuario.idioma),
	};
	private ArrayList<String> id = new ArrayList<String>();
	private JsonArray arrayMensajes;
	
	/**
	 * Create the panel.
	 */
	public VistaMensajeProfesor(FrameMediano vistaMediana,DatosUsuario datosUsuario) {
		
		setLayout(null);
		
		botonNuevoMensaje = new JButton(datosetiquetas.devuelveEtiquetas("enuevomensaje", DatosUsuario.idioma));
		botonNuevoMensaje.setBounds(593, 32, 169, 23);
		add(botonNuevoMensaje);
		botonNuevoMensaje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					vistaMediana.cambiaPanelNuevoMensaje(vistaMediana,datosUsuario);
				}catch(NullPointerException n){
					FramePeq peque= new FramePeq();
					peque.setVisible (true);
					peque.abreVistaError(datosetiquetas.devuelveEtiquetas("eNoCursos", DatosUsuario.idioma),peque);
					vistaMediana.dispose();
				}
			}
		});	
		
	    btnAtras = new JButton(datosetiquetas.devuelveEtiquetas("eatras", DatosUsuario.idioma));
		btnAtras.setBounds(68, 32, 89, 23);
		add(btnAtras);
		
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				vistaMediana.dispose();
				}
			});
		
		try{
			arrayMensajes = datosMensajes.getMensajeProfesor(datosUsuario.getId());//DEVUELVE ARRAY CON LOS OBJETOS DE LOS MENSAJES

			}catch(Exception e){
			}	
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
		fondo.setBounds(0, 0, 800, 500);
		add(fondo);
		
	}
	
	public Object[][] toArrayMensajes(JsonArray mensajes){
		try{
		Object[][] filamensajes = new Object[mensajes.size()][2];

		for(int i=0;i<mensajes.size();i++){
			id.add(mensajes.getJsonObject(i).getString("idmensajes"));
			filamensajes[i][0] = mensajes.getJsonObject(i).getString("nom_alu")+" "+mensajes.getJsonObject(i).getString("ape_alu");
			filamensajes[i][1] = mensajes.getJsonObject(i).getString("asunto");
			
		}
		return filamensajes;
		}catch(Exception e){
			Object[][] filamensajes = new Object[0][2];
			return filamensajes;
		}

		
	}

}
