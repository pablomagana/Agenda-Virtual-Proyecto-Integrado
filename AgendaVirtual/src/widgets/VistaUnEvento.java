package widgets;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import modelo.DatosEventos;
import vista.FrameIntermedio;

public class VistaUnEvento extends JPanel {

	DatosEventos dEventos;
	JLabel lblNomEvento;
	JCheckBox checkRealiza;
	private String idEvento;
	/**
	 * Create the panel.
	 */
	public VistaUnEvento(String idEven, String nomEven ) {
		this.dEventos=new DatosEventos();
		this.idEvento=idEven;
		
		setOpaque(false);
		setBackground(Color.WHITE);
		setLayout(null);
		
		lblNomEvento = new JLabel(nomEven);
		lblNomEvento.addMouseListener(new MouseAdapter() {
		 	@Override
		 	public void mouseClicked(MouseEvent arg0) {
		 		FrameIntermedio fraInter= new FrameIntermedio();
		 		fraInter.cambiaPanelVistaEvento(idEvento, fraInter);
		 		fraInter.setVisible(true);
		 	}
		 });
		lblNomEvento.setBackground(Color.WHITE);
		lblNomEvento.setBounds(35, 0, 308, 28);
		add(lblNomEvento);
			
	}
}
