package widgets;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JLabel;

import vista.FrameIntermedio;
import modelo.DatosTareas;
import modelo.DatosUsuario;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VistaUnaTarea extends JPanel {
	DatosTareas dTareas;
	JLabel lblNomTarea;
	JCheckBox checkRealiza;
	private String idtarea;
	private String idUsuario;
	/**
	 * Create the panel.
	 */
	public VistaUnaTarea(String idUsu,String idTar, String nomTarea,String realizada ) {
		dTareas=new DatosTareas();
		this.idUsuario= idUsu;
		this.idtarea=idTar;
		setOpaque(false);
		setBackground(Color.WHITE);
		setLayout(null);
		
		 lblNomTarea = new JLabel(nomTarea);
		 lblNomTarea.addMouseListener(new MouseAdapter() {
		 	@Override
		 	public void mouseClicked(MouseEvent arg0) {
		 		FrameIntermedio fraInter= new FrameIntermedio();
		 		fraInter.cambiaPanelVistaTarea(idtarea, fraInter);
		 		fraInter.setVisible(true);
		 	}
		 });
		lblNomTarea.setBackground(Color.WHITE);
		lblNomTarea.setBounds(35, 0, 308, 28);
		add(lblNomTarea);
			
		if(DatosUsuario.tipo== "Alumno"){
			
		
		checkRealiza = new JCheckBox("");
		checkRealiza.setOpaque(false);
		checkRealiza.setBounds(6, 4, 23, 21);
		if(realizada.equals("1")){
			checkRealiza.setSelected(true);
			}
			add(checkRealiza);
			
			checkRealiza.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					if(checkRealiza.isSelected()){
						dTareas.updateTarea(1, idUsuario,idtarea);
					}else{
						dTareas.updateTarea(0, idUsuario,idtarea);
					}
				}
			});
		}

	}
}
