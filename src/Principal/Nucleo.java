package Principal;

import java.awt.EventQueue;
import java.io.IOException;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

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
