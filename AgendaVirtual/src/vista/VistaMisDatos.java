package vista;

import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import modelo.DatosHashMap;
import modelo.DatosUsuario;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingConstants;

public class VistaMisDatos extends JPanel {
	private JLabel fondo, lblMisDatos,lblNombre,lblApellidos,lblTelefono,lblFechaDeNacimiento,lblDireccin,lblLocalidad,lblCodigoPostal,lblEmail;
	private JLabel lblNom,lblApe,lblTel,lblMail,lblFNac,lblDirec,lblLocal,lblCP;
	private JButton atras;
	private FrameMediano mediano;
	private DatosHashMap datosetiquetas=DatosHashMap.getinstance();
	

	public VistaMisDatos(FrameMediano mediano,DatosUsuario datos) {
		String[] misDatos=datos.misDatos();
		setForeground(SystemColor.activeCaption);
		setBackground(SystemColor.activeCaption);
		setBorder(null);
		setLayout(null);
				
				lblMisDatos = new JLabel(datosetiquetas.devuelveEtiquetas("emisdatos", DatosUsuario.idioma));
				lblMisDatos.setHorizontalAlignment(SwingConstants.CENTER);
				lblMisDatos.setForeground(Color.WHITE);
				lblMisDatos.setFont(new Font("Segoe Print", Font.BOLD, 24));
				lblMisDatos.setBounds(290, 17, 290, 44);
				add(lblMisDatos);
				
				lblNombre = new JLabel(datosetiquetas.devuelveEtiquetas("enombre", DatosUsuario.idioma)+": ");
				lblNombre.setFont(new Font("Segoe Print", Font.BOLD, 20));
				lblNombre.setForeground(new Color(255, 75, 43));
				lblNombre.setBounds(65, 90, 98, 27);
				add(lblNombre);
				
				lblApellidos = new JLabel(datosetiquetas.devuelveEtiquetas("eapellidos", DatosUsuario.idioma)+": ");
				lblApellidos.setForeground(new Color(255, 75, 43));
				lblApellidos.setFont(new Font("Segoe Print", Font.BOLD, 20));
				lblApellidos.setBounds(391, 90, 121, 27);
				add(lblApellidos);
				
				lblTelefono = new JLabel(datosetiquetas.devuelveEtiquetas("etelefono", DatosUsuario.idioma)+": ");
				lblTelefono.setForeground(new Color(255, 75, 43));
				lblTelefono.setFont(new Font("Segoe Print", Font.BOLD, 20));
				lblTelefono.setBounds(65, 162, 110, 27);
				add(lblTelefono);
				
				lblFechaDeNacimiento = new JLabel(datosetiquetas.devuelveEtiquetas("efnac", DatosUsuario.idioma)+": ");
				lblFechaDeNacimiento.setForeground(new Color(255, 75, 43));
				lblFechaDeNacimiento.setFont(new Font("Segoe Print", Font.BOLD, 20));
				lblFechaDeNacimiento.setBounds(391, 232, 220, 27);
				add(lblFechaDeNacimiento);
				
				lblDireccin = new JLabel(datosetiquetas.devuelveEtiquetas("edireccion", DatosUsuario.idioma)+": ");
				lblDireccin.setForeground(new Color(255, 75, 43));
				lblDireccin.setFont(new Font("Segoe Print", Font.BOLD, 20));
				lblDireccin.setBounds(65, 232, 110, 27);
				add(lblDireccin);
				
				lblLocalidad = new JLabel(datosetiquetas.devuelveEtiquetas("elocalidad", DatosUsuario.idioma)+": ");
				lblLocalidad.setForeground(new Color(255, 75, 43));
				lblLocalidad.setFont(new Font("Segoe Print", Font.BOLD, 20));
				lblLocalidad.setBounds(391, 304, 110, 27);
				add(lblLocalidad);
				
				lblCodigoPostal = new JLabel(datosetiquetas.devuelveEtiquetas("ecp", DatosUsuario.idioma)+": ");
				lblCodigoPostal.setForeground(new Color(255, 75, 43));
				lblCodigoPostal.setFont(new Font("Segoe Print", Font.BOLD, 20));
				lblCodigoPostal.setBounds(65, 304, 148, 27);
				add(lblCodigoPostal);
				
				lblEmail = new JLabel(datosetiquetas.devuelveEtiquetas("eemail", DatosUsuario.idioma)+": ");
				lblEmail.setForeground(new Color(255, 75, 43));
				lblEmail.setFont(new Font("Segoe Print", Font.BOLD, 20));
				lblEmail.setBounds(391, 162, 86, 27);
				add(lblEmail);
				
				lblNom = new JLabel(misDatos[0]);
				lblNom.setForeground(Color.WHITE);
				lblNom.setFont(new Font("Segoe Print", Font.BOLD, 20));
				lblNom.setBounds(161, 90, 220, 27);
				add(lblNom);
				
				lblApe = new JLabel(misDatos[1]);
				lblApe.setForeground(Color.WHITE);
				lblApe.setFont(new Font("Segoe Print", Font.BOLD, 20));
				lblApe.setBounds(501, 90, 273, 27);
				add(lblApe);
				
				lblTel = new JLabel(misDatos[2]);
				lblTel.setForeground(Color.WHITE);
				lblTel.setFont(new Font("Segoe Print", Font.BOLD, 20));
				lblTel.setBounds(175, 162, 209, 27);
				add(lblTel);
				
				lblMail = new JLabel(misDatos[7]);
				lblMail.setForeground(Color.WHITE);
				lblMail.setFont(new Font("Segoe Print", Font.BOLD, 20));
				lblMail.setBounds(472, 162, 290, 27);
				add(lblMail);
				
				lblFNac = new JLabel(misDatos[3]);
				lblFNac.setForeground(Color.WHITE);
				lblFNac.setFont(new Font("Segoe Print", Font.BOLD, 20));
				lblFNac.setBounds(605, 232, 157, 27);
				add(lblFNac);
				
				lblDirec = new JLabel(misDatos[4]);
				lblDirec.setForeground(Color.WHITE);
				lblDirec.setFont(new Font("Segoe Print", Font.BOLD, 20));
				lblDirec.setBounds(165, 232, 220, 27);
				add(lblDirec);
				
				lblLocal = new JLabel(misDatos[5]);
				lblLocal.setForeground(Color.WHITE);
				lblLocal.setFont(new Font("Segoe Print", Font.BOLD, 20));
				lblLocal.setBounds(501, 304, 261, 27);
				add(lblLocal);
				
				lblCP = new JLabel(misDatos[6]);
				lblCP.setForeground(Color.WHITE);
				lblCP.setFont(new Font("Segoe Print", Font.BOLD, 20));
				lblCP.setBounds(212, 304, 169, 27);
				add(lblCP);
				
				atras = new JButton(datosetiquetas.devuelveEtiquetas("eatras", DatosUsuario.idioma));
				atras.setFont(new Font("Segoe Print", Font.BOLD, 14));
				atras.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						mediano.setVisible(false);
					}
				});
				atras.setBounds(68, 32, 89, 23);
				add(atras);
				
				//fondo ventana
				fondo = new JLabel("");
				fondo.setIcon(new ImageIcon(VistaTareas.class.getResource("/images/FONDO VISTAS.png")));
				fondo.setBounds(0, 0, 800, 500);
				add(fondo);
	}
}

