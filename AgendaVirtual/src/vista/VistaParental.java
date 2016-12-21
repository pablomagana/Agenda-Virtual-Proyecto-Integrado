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
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import modelo.DatosHashMap;
import modelo.DatosUsuario;

public class VistaParental extends JPanel {
	
	private DatosHashMap datosetiquetas=DatosHashMap.getinstance();
	
	private JPasswordField passwordField;
	private JButton bEntrar;
	private JLabel controlParental, fondo;
	private String contra;
	private FrameMediano medi;
	private VistaMensajeAlumno mens;
	private FramePeq peq;

	
	public VistaParental(String contras,FramePeq peque,DatosUsuario datosUsuario) {
		this.peq=peque;
		this.contra= contras;
		setLayout(null);
		
		controlParental = new JLabel(datosetiquetas.devuelveEtiquetas("econtrolparental", DatosUsuario.idioma));
		controlParental.setHorizontalAlignment(SwingConstants.CENTER);
		controlParental.setBounds(61, 86, 238, 44);
		controlParental.setForeground(Color.WHITE);
		controlParental.setFont(new Font("Segoe Print", Font.BOLD, 24));
		add(controlParental);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(106, 141, 150, 32);
		passwordField.setHorizontalAlignment(SwingConstants.LEFT);
		passwordField.setFont(new Font("Segoe Print", Font.PLAIN, 14));
		add(passwordField);
		
		
		
		bEntrar = new JButton(datosetiquetas.devuelveEtiquetas("eentrar", DatosUsuario.idioma));
		bEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(String.valueOf(passwordField.getPassword()).compareTo(contra)==0){
					peq.setVisible(false);
					medi= new FrameMediano();
					mens= new VistaMensajeAlumno(medi,datosUsuario);
					medi.setContentPane(mens);
					medi.setVisible(true); 
					
					
				}else {

					FramePeq peque= new FramePeq();
					peque.setVisible (true);
					peque.abreVistaError(datosetiquetas.devuelveEtiquetas("eerrorcont", DatosUsuario.idioma),peque);				}
			
			}
		});
		bEntrar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
			if(String.valueOf(passwordField.getPassword()).compareTo(contra)==0){	
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
			        {
					 	passwordField.setFocusable(false);
					 	bEntrar.setFocusable(true);
					 	peq.setVisible(false);
					 	medi= new FrameMediano();
						mens= new VistaMensajeAlumno(medi,datosUsuario);
						medi.setContentPane(mens);
						medi.setVisible(true); 
			        }
				}
			}
		});

		
		bEntrar.setForeground(new Color(0, 102, 153));
		bEntrar.setFont(new Font("Segoe Print", Font.BOLD, 18));
		bEntrar.setBounds(134, 196, 95, 28);
		add(bEntrar);
		
		
		fondo = new JLabel("");
		fondo.setIcon(new ImageIcon(VistaParental.class.getResource("/images/molinillo.png")));
		fondo.setBounds(0, 0, 354, 235);
		add(fondo);

	}
}
