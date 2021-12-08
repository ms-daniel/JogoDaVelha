package Principal;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Menu {
	private JFrame Jan;
	
	//botoes
	private JButton bIni;
	private JButton bMudIp;
	private JButton bMudPort;
	
	//box para digitar
	private JTextField ip;
	private JTextField port;
	
	//label's com nomes dos espaços
	private JLabel ip_l;
	private JLabel port_l;
	private JLabel aviso;
	
	//margem
	private Insets margem = new Insets(0,0,0,0);
	
	//ip e port para servidor
	private String Ip_serv = "";
	private String Port_serv = "";
	
	JLabel teste;
	
	//plano de 
	private ImageIcon img_F = new ImageIcon(
			getClass().getClassLoader().getResource("backgroundI.jpg"));;
	private JLabel fundo;
	
	public Menu() {
		//botoes
		bIni = new JButton("Iniciar");
		bIni.setBounds(250, 325, 250, 30);
		bIni.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bIni.setFont(new Font("Arial", Font.BOLD, 16));
		bIni.setFocusable(false);
		bIni.setEnabled(false);
		
		bMudIp = new JButton("Mudar");
		bMudIp.setBounds(450, 220, 50, 30);
		bMudIp.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bMudIp.setFont(new Font("Arial", Font.BOLD, 14));
		bMudIp.setMargin(margem);
		bMudIp.setFocusable(false);
		
		bMudPort = new JButton("Mudar");
		bMudPort.setBounds(450, 280, 50, 30);
		bMudPort.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bMudPort.setFont(new Font("Arial", Font.BOLD, 14));
		bMudPort.setMargin(margem);
		bMudPort.setFocusable(false);
		
		ip_l = new JLabel();
		ip_l.setText("Endereço IP:");
		ip_l.setBounds(250, 200, 98, 20);
		ip_l.setForeground(Color.WHITE);
		ip_l.setBackground(Color.BLACK);
		ip_l.setOpaque(true);
		ip_l.setFont(new Font("Arial", Font.BOLD, 16));
		
		aviso = new JLabel("");
		aviso.setBounds(250, 310, 102, 15);
		aviso.setForeground(Color.RED);
		aviso.setBackground(Color.BLACK);
		aviso.setOpaque(false);
		aviso.setFont(new Font("Arial", Font.BOLD, 12));
		
		port_l = new JLabel();
		port_l.setText("Porta:");
		port_l.setBounds(250, 260, 47, 20);
		port_l.setForeground(Color.WHITE);
		port_l.setBackground(Color.BLACK);
		port_l.setOpaque(true);
		port_l.setFont(new Font("Arial", Font.BOLD, 16));
		
		ip = new JTextField("");
		ip.setHorizontalAlignment(JTextField.CENTER);
		ip.setToolTipText("Ex.: 192.168.0.105");
		ip.setDocument(new JTextFieldLimit(15));
		ip.setBounds(250, 220, 200, 30);
		ip.setFont(new Font("Arial", Font.PLAIN, 16));
		
		ip.setText("192.168.0.196");
		
		port = new JTextField("");
		port.setHorizontalAlignment(JTextField.CENTER);
		port.setBounds(250, 280, 200, 30);
		port.setToolTipText("Ex.: 25565");
		port.setDocument(new JTextFieldLimit(5));
		port.setFont(new Font("Arial", Font.PLAIN, 16));
		
		port.setText("9000");
		
		//
		teste = new JLabel(ip.getText());
		teste.setBounds(20, 20, 250, 50);
		
		//jlabel da imagem para fundo
		fundo = new JLabel();
		fundo.setIcon(img_F);
		fundo.setBounds(0, -20, 750, 600);
		
		if(!ip.getText().equals(new String(""))) {
			bIni.setEnabled(true);
		}
		
		//area de ações quando os botoes são pressionados
		port.addKeyListener(new KeyAdapter() { //aceita apenas numeros na porta
	         public void keyPressed(KeyEvent ke) {
	             if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyChar() == '\b') {
	            	port.setEditable(true);
	            	aviso.setText("");
	            	aviso.setOpaque(false);
	             } else {
	            	port.setEditable(false);
	            	aviso.setText("Apenas numeros!");
	            	aviso.setOpaque(true);
	             }
	          }
	       });
		//botao de mudar ip
		bMudIp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Ip_serv = ip.getText();
				
				if(!Port_serv.equals("") && !Ip_serv.equals("")) 
					bIni.setEnabled(true);
				else
					bIni.setEnabled(false);
			}
		});
		//botao de mudar porta
		bMudPort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Port_serv = port.getText();
				
				if(!Port_serv.equals("") && !Ip_serv.equals("")) 
					bIni.setEnabled(true);
				else
					bIni.setEnabled(false);
			}
		});	
	}
	
	//a tela recebe todos os componentes do menu
	public JFrame getBoard(JFrame tela) {
		//botoes
		tela.getContentPane().add(bIni);
		tela.getContentPane().add(bMudIp);
		tela.getContentPane().add(bMudPort);
		
		//label's
		tela.getContentPane().add(ip_l);
		tela.getContentPane().add(port_l);
		tela.getContentPane().add(aviso);
		
		//text fields / campos de texto
		tela.getContentPane().add(ip);
		tela.getContentPane().add(port);
		
		tela.getContentPane().add(teste);
		tela.getContentPane().add(fundo);
		
		return tela;
	}
	
	//recebe a refernecia do botão de iniciar para poder executar o servidor na class nucleo
	//e melhorar o gerenciamento do servidor
	public JButton getBIni() {
		return bIni;
	}
	//retornar ip e porta
	public String getIp() {
		Ip_serv = ip.getText();
		return this.Ip_serv;
	}
	public int getPort() {
		Port_serv = port.getText();
		return Integer.parseInt(this.Port_serv);
	}
	
	//seta port ou ip
	public void resetPort() {
		this.Port_serv = "";
		this.port.setText("");
	}
	public void resetIp() {
		
		this.Ip_serv = "";
		this.ip.setText("");
	}
}
