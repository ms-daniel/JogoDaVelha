package Principal;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.awt.Cursor;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Nucleo {
	private JFrame Janela1;
	private ImageIcon logo = new ImageIcon(
			getClass().getClassLoader().getResource("logo.jpg"));
	
	//menu inicial
	private JLabel pdf_ini;
	private JButton bIni;
	private JButton bServ;
	
	private IniciarTabuleiro tabuleiro = new IniciarTabuleiro();
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Nucleo window = new Nucleo();
					window.Janela1.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Nucleo() throws IOException {
		initialize();
	}
	
	private void initialize() throws IOException {
		//cria a janela
		Janela1 = new JFrame("Jogo da Velha");
		Janela1.setBounds(320, 80, 750, 600);
		Janela1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Janela1.setIconImage(logo.getImage());
		Janela1.getContentPane().setLayout(null);
		
		//pega janela do tabuleiro
		Janela1 = tabuleiro.getBoard(Janela1);
		
	}

}
