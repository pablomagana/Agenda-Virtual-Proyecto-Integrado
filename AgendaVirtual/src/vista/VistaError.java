package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import modelo.DatosHashMap;
import modelo.DatosUsuario;
import javax.swing.JCheckBox;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.beans.VetoableChangeListener;

public class VistaError extends JPanel {
	private JButton bAceptar;
	private JLabel fondo, lblImgError;
	private String error;
	private FramePeq peq;
	private JTextArea txtrMens;
	private DatosHashMap datosetiquetas=DatosHashMap.getinstance();

	
	public VistaError(String error,FramePeq peque) {
		this.peq=peque;
		this.error= error;
		setLayout(null);
		
		bAceptar = new JButton(datosetiquetas.devuelveEtiquetas("eaceptar", DatosUsuario.idioma));
		bAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					peq.dispose();
					
			}
		});
		bAceptar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				 if(e.getKeyCode()==KeyEvent.VK_ENTER)
			        {
					 	lblImgError.setFocusable(false);
					 	bAceptar.setFocusable(true);
					 	peq.dispose();
			        }
			}
		});

		
		bAceptar.setForeground(new Color(0, 102, 153));
		bAceptar.setFont(new Font("Segoe Print", Font.BOLD, 18));
		bAceptar.setBounds(119, 172, 114, 28);
		add(bAceptar);
		
		lblImgError = new JLabel("");
		lblImgError.setIcon(new ImageIcon(VistaError.class.getResource("/images/error.png")));
		lblImgError.setBounds(26, 88, 40, 40);
		add(lblImgError);
		
		txtrMens = new JTextArea(this.error);
		txtrMens.setEditable(false);
		txtrMens.setBackground(Color.WHITE);
		txtrMens.setWrapStyleWord(true);
		txtrMens.setForeground(new Color (0,4,120));;
		txtrMens.setLineWrap(true);
		txtrMens.setOpaque(false);
		txtrMens.setRows(2);
		txtrMens.setFont(new Font("Segoe Print", Font.BOLD, 16));
		txtrMens.setBounds(86, 59, 182, 99);
		add(txtrMens);
		
		
		
		fondo = new JLabel("");
		fondo.setIcon(new ImageIcon(VistaParental.class.getResource("/images/molinillo.png")));
		fondo.setBounds(0, 0, 354, 235);
		add(fondo);
		
		


	}
}