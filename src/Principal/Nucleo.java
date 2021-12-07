package Principal;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Nucleo {
	private JFrame Janela1;
	private Socket conexao;
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
		Janela1 = StartWindows(Janela1);
		
		Janela1 = menu.getBoard(Janela1);
		
		
		//pega referencia do botao "iniciar do menu"
		//para realizar ações aqui
		bIniMenu = menu.getBIni();
		
		bIniMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Janela1.setCursor(new Cursor(Cursor.WAIT_CURSOR));
				
				//tentara se conectar ao servidor especificado na tela menu
				try {
					conexao = new Socket(menu.getIp(),menu.getPort());
					
				}catch(IllegalArgumentException e) {
					JOptionPane.showMessageDialog(Janela1, "Porta não está no intervalo permitido!", "Port Error", 0); 
					menu.resetPort();
				}catch(UnknownHostException e) {
					JOptionPane.showMessageDialog(Janela1, "Houve algum problema com o IP");
					menu.resetIp();
				}catch(SecurityException e) {
					JOptionPane.showMessageDialog(Janela1, "Houve algum problema de segurança", "Security Error", 0);
				}catch(IOException e) {
					JOptionPane.showMessageDialog(Janela1, "Houve algum problema de execução ou com conexão ao servidor", "I/O Error", 0);
				}finally {
					Janela1.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}
				
				if(conexao != null && conexao.isConnected()) {
					JOptionPane.showMessageDialog(Janela1, "Conectado a partida!");
					Janela1.getContentPane().removeAll();
					Janela1.repaint();
					Janela1 = tabuleiro.getBoard(Janela1);
				}
			}
		});
		
	}
	
	private JFrame StartWindows(JFrame Janela) {
		Janela.setBounds(0, 0, 750, 600);
		Janela.setLocationRelativeTo(null); //alinha a janela no centro da tela
		Janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Janela.setIconImage(this.logo.getImage()); //define o icone da aplicação
		Janela.getContentPane().setLayout(null);
		return Janela;
	}

}
