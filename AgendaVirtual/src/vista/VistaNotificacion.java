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

public class VistaNotificacion extends JPanel {
	private JButton bAceptar;
	private JLabel fondo, lblImgError;
	private String notificacion;
	private FramePeq peq;
	private JTextArea txtrMens;
	private DatosHashMap datosetiquetas=DatosHashMap.getinstance();

	
	public VistaNotificacion(String notificacion,FramePeq peque) {
		
		this.peq=peque;
		this.notificacion= notificacion;
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
					 	peq.setVisible(false);
			        }
			}
		});

		
		bAceptar.setForeground(new Color(0, 102, 153));
		bAceptar.setFont(new Font("Segoe Print", Font.BOLD, 18));
		bAceptar.setBounds(119, 172, 114, 34);
		add(bAceptar);
		
		lblImgError = new JLabel("");
		lblImgError.setIcon(new ImageIcon(VistaError.class.getResource("/images/notificacion.png")));
		lblImgError.setBounds(26, 88, 40, 40);
		add(lblImgError);
		
		txtrMens = new JTextArea(this.notificacion);
		txtrMens.setEditable(false);
		txtrMens.setBackground(Color.WHITE);
		txtrMens.setWrapStyleWord(true);
		txtrMens.setForeground(new Color (0,4,120));;
		txtrMens.setLineWrap(true);
		txtrMens.setOpaque(false);
		txtrMens.setRows(2);
		txtrMens.setFont(new Font("Segoe Print", Font.BOLD, 16));
		txtrMens.setBounds(87, 62, 182, 99);
		add(txtrMens);
		
		fondo = new JLabel("");
		fondo.setIcon(new ImageIcon(VistaParental.class.getResource("/images/molinillo.png")));
		fondo.setBounds(0, 0, 354, 235);
		add(fondo);
		
		


	}
}