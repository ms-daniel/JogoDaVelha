package Principal;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Nucleo {
	private JFrame Janela1;
	private ImageIcon logo = new ImageIcon(
			getClass().getClassLoader().getResource("logo.jpg"));
	
	private JButton bIniMenu;
	
	//instancia um objeto que cria os componentes da tela de tabuleiro
	private IniciarTabuleiro tabuleiro = new IniciarTabuleiro();
	
	//instancia um objeto que cria os componentes do menu
	private Menu menu = new Menu();
	
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
		Janela1.setBounds(0, 0, 750, 600);
		Janela1.setLocationRelativeTo(null); //alinha a janela no centro da tela
		Janela1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Janela1.setIconImage(logo.getImage()); //define o icone da aplicação
		Janela1.getContentPane().setLayout(null);
		
		//pega janela do tabuleiro
//		Janela1 = tabuleiro.getBoard(Janela1);
		
		Janela1 = menu.getBoard(Janela1);
		
		bIniMenu = menu.getBIni();
		
		bIniMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("apertou");
			}
		});
		
	}

}
