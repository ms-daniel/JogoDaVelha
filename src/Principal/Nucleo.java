package Principal;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
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
		//para realizar a��es aqui
		bIniMenu = menu.getBIni();
		
		bIniMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Janela1.setCursor(new Cursor(Cursor.WAIT_CURSOR));
				
				//tentara se conectar ao servidor especificado na tela menu
				try {
					conexao = new Socket(menu.getIp(),menu.getPort());
				
				//tratamento de exce��es
				}catch(IllegalArgumentException e) {
					System.out.println(menu.getPort());
					JOptionPane.showMessageDialog(Janela1, "Porta n�o est� no intervalo permitido!", "Port Error", 0); 
					menu.resetPort();
				}catch(UnknownHostException e) {
					JOptionPane.showMessageDialog(Janela1, "Houve algum problema com o IP");
					menu.resetIp();
				}catch(SecurityException e) {//problema de seguran�a
					JOptionPane.showMessageDialog(Janela1, "Houve algum problema de seguran�a", "Security Error", 0);
				}catch(IOException e) { //problema com conexao com servidor
					JOptionPane.showMessageDialog(Janela1, "Houve algum problema de execu��o ou com conex�o ao servidor", "I/O Error", 0);
				}finally {
					Janela1.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}
				
				if(conexao != null && conexao.isConnected()) {
					//dialog box de conectado
					Conectado();
					
					//remove todos os components do menu
					Janela1.getContentPane().removeAll();
					Janela1.repaint(); //repinta a tela
					
					//abre o tabuleiro
					tabuleiro.setSocket(conexao);
					Janela1 = tabuleiro.getBoard(Janela1);
					
					//tenta ligar a entrada de dados
					try {
						tabuleiro.setInput(new BufferedReader(
								new InputStreamReader(conexao.getInputStream())));
					} catch (IOException e) {
						JOptionPane.showMessageDialog(Janela1, "Erro ao conectar entrada de dados!");
						e.printStackTrace();
					}
					
					//tenta ligar a saida de dados
					try {
						tabuleiro.setOutput(new DataOutputStream(
								conexao.getOutputStream()));
					} catch (IOException e) {
						JOptionPane.showMessageDialog(Janela1, "Erro ao conectar saida de dados!");
						e.printStackTrace();
					}
					
					tabuleiro.start();
				}
			}
		});//fim do bot�o
		
	}
	
	//apenas para iniciar a tela inicial
	private JFrame StartWindows(JFrame Janela) {
		Janela.setBounds(0, 0, 750, 600);
		Janela.setLocationRelativeTo(null); //alinha a janela no centro da tela
		Janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Janela.setIconImage(this.logo.getImage()); //define o icone da aplica��o
		Janela.getContentPane().setLayout(null);
		return Janela;
	}
	
	//usado para mostrar a caixa de que foi conectado e sumir sozinho com ela
	private void Conectado(){
		JDialog show;
		JOptionPane con = new JOptionPane();
		con.setMessage("Conectado!");
		con.setMessageType(1);
		
		show = con.createDialog("Servidor");
		
		//cria��o de uma thread pra poder rodar um sleep e fechar a dialog box sozinho
		Thread paralelo =
			    new Thread(){
			        public void run(){
			        	try {
			    			Thread.sleep(800);
			    			show.setVisible(false);
			    			show.dispose();
			    		} catch (InterruptedException e) {
			    			// TODO Auto-generated catch block
			    			e.printStackTrace();
			    		}
			        }
			    };
		paralelo.start();
		//fim thread
		
		show.setVisible(true);
	}

}
