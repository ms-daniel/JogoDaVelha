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
	
	//label's
	private JLabel tabuleiro;
	private JLabel pdf;
	
	//fim label's
	
	//iamgens
	private ImageIcon pDeF = new ImageIcon(
			getClass().getClassLoader().getResource("backgroundU.jpg"));
	
	private ImageIcon tabu_icon = new ImageIcon(
			getClass().getClassLoader().getResource("tabuleiro.png"));
	private Image tenta = tabu_icon.getImage();
	//fim iamgens
	
	private Dados dados;
	
	//botões do tabuleiro
	
	
	private Insets margem = new Insets(0,0,0,0);
	//
	private JButton swit;
	
	
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
		
		dados = new Dados(Janela1);
		
		//botaõ de teste para trocar simbolo
		swit = new JButton("SWITCH");
		swit.setBounds(5, 5, 80, 50);
		swit.setFont(new Font("Tahoma", Font.PLAIN, 16));
		swit.setBackground(new Color(26,148,49));
		swit.setMargin(margem);
		swit.setToolTipText("Trocar o simbolo");
		swit.setFocusPainted(false); //remove o retangulo ao redor do texto
		Janela1.getContentPane().add(swit);
		
		
		
		//seta a imagem do tabuleiro
		tabuleiro = new JLabel();
		tabuleiro.setIcon(tabu_icon);
		tabuleiro.setBounds(140, 50, 450, 450);
		Janela1.getContentPane().add(tabuleiro);
		
		//plano de fundo
		pdf = new JLabel();
		pdf.setIcon(pDeF);
		pdf.setBounds(0, -20, 750, 600);
		Janela1.getContentPane().add(pdf);
		
		
		
		swit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dados.SwitchSymbol();	
			}
		});
	
	}

}
