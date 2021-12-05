package Principal;

import java.awt.Cursor;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Menu {
	private JFrame Jan;
	
	//botoes
	private JButton bIni;
	private JTextField ip;
	private JTextField port;
	
	//ip e port para servidor
	private String Ip_serv;
	private int Port_serv;
	
	JLabel teste;
	
	//plano de 
	private ImageIcon img_F = new ImageIcon(
			getClass().getClassLoader().getResource("backgroundI.jpg"));;
	private JLabel fundo;
	
	public Menu() {
		bIni = new JButton("Iniciar");
		bIni.setBounds(250, 325, 250, 30);
		bIni.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bIni.setFont(new Font("Arial", Font.PLAIN, 16));
		bIni.setFocusable(false);
//		bIni.setContentAreaFilled(false);
//		bIni.setBorderPainted(false);
		
		
		ip = new JTextField("IP");
		ip.setBounds(250, 235, 250, 30);
		ip.setFont(new Font("Arial", Font.PLAIN, 16));
		
		port = new JTextField("Port");
		port.setBounds(250, 280, 80, 30);
		port.setFont(new Font("Arial", Font.PLAIN, 16));
		
		//
		teste = new JLabel(ip.getText());
		teste.setBounds(20, 20, 250, 50);
		
		//jlabel da imagem para fundo
		fundo = new JLabel();
		fundo.setIcon(img_F);
		fundo.setBounds(0, -20, 750, 600);		
	}
	
	public JFrame getBoard(JFrame tela) {
		tela.getContentPane().add(bIni);
		tela.getContentPane().add(ip);
		tela.getContentPane().add(port);
		tela.getContentPane().add(teste);
		tela.getContentPane().add(fundo);
		
		return tela;
	}
}
