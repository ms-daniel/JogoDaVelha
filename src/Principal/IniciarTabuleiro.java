package Principal;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.Arrays;
import java.util.concurrent.TimeoutException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class IniciarTabuleiro extends Thread{
	//janela criada anteriormente 
		private JFrame Janela1;
		
		private boolean First; //saber se sera o primeiro
		private boolean win = false; //quando houver um ganhador
		private boolean clickou = false; //saber quando clicar em um botao
		private String toServer = ""; //msg para o servidor
		
		//classe do aviso de "esperando outro jogador"
		private LoadThread load = new LoadThread();
		
		//sons
		private AudioInputStream click;
		
		//usado para saber quais botoes foram clicados
		private boolean[] clicked = {
				false,false,false,
				false,false,false,
				false,false,false
		};
		//som ao clicar
		private Clip click_sound;
		
		//label's
		private JLabel tabuleiro;
		private JLabel pdf;
		
		//imagens dos icones
		private ImageIcon x = new ImageIcon(
				getClass().getClassLoader().getResource("x.png"));
		
		private ImageIcon o = new ImageIcon(
				getClass().getClassLoader().getResource("o.png"));
		
		//seu icone
		private ImageIcon sim = x;
		private ImageIcon nao = o;
		
		private int[][] matriz = new int[3][3];
		
		//imagens
		private ImageIcon pDeF = new ImageIcon(
				getClass().getClassLoader().getResource("backgroundU.jpg"));
		
		private ImageIcon tabu_icon = new ImageIcon(
				getClass().getClassLoader().getResource("tabuleiro.png"));
		
		//fim iamgens
		
		private Insets margem = new Insets(0,0,0,0);
		
		//botao de switch(troca)
		private JButton swit;
		
		//declração dos botões
		private JButton bTabu1 = new JButton();
		private JButton bTabu2 = new JButton();
		private JButton bTabu3 = new JButton();
		private JButton bTabu4 = new JButton();
		private JButton bTabu5 = new JButton();
		private JButton bTabu6 = new JButton();
		private JButton bTabu7 = new JButton();
		private JButton bTabu8 = new JButton();
		private JButton bTabu9 = new JButton();
		
		//entrada e saida de dados
		private Socket conexao;
		private BufferedReader conexao_entrada; 
		private DataOutputStream conexao_saida;

	public IniciarTabuleiro() {
		//preenche a matriz com 0
		for(int i= 0; i<3; i++) {
			Arrays.fill(matriz[i], 0);
		}
			
		try {
			LoadSounds();
		} catch (UnsupportedAudioFileException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		//botões tabuleiro
		bTabu1.setBounds(149, 61, 140, 140);
		bTabu1.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bTabu1.setFocusable(false);
		bTabu1.setContentAreaFilled(false);
		bTabu1.setBorderPainted(false);
				
		bTabu2.setBounds(293, 61, 140, 140);
		bTabu2.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bTabu2.setFocusable(false);
		bTabu2.setContentAreaFilled(false);
		bTabu2.setBorderPainted(false);

				
		bTabu3.setBounds(440, 61, 140, 140);
		bTabu3.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bTabu3.setFocusable(false);
		bTabu3.setContentAreaFilled(false);
		bTabu3.setBorderPainted(false);


		bTabu4.setBounds(149, 207, 140, 140);
		bTabu4.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bTabu4.setFocusable(false);
		bTabu4.setContentAreaFilled(false);
		bTabu4.setBorderPainted(false);
				
		bTabu5.setBounds(293, 207, 140, 140);
		bTabu5.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bTabu5.setFocusable(false);
		bTabu5.setContentAreaFilled(false);
		bTabu5.setBorderPainted(false);

				
		bTabu6.setBounds(440, 207, 140, 140);
		bTabu6.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bTabu6.setFocusable(false);
		bTabu6.setContentAreaFilled(false);
		bTabu6.setBorderPainted(false);

				
		bTabu7.setBounds(149, 353, 140, 140);
		bTabu7.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bTabu7.setFocusable(false);
		bTabu7.setContentAreaFilled(false);
		bTabu7.setBorderPainted(false);
		
				
		bTabu8.setBounds(293, 353, 140, 140);
		bTabu8.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bTabu8.setFocusable(false);
		bTabu8.setContentAreaFilled(false);
		bTabu8.setBorderPainted(false);

				
		bTabu9.setBounds(440, 353, 140, 140);
		bTabu9.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bTabu9.setFocusable(false);
		bTabu9.setContentAreaFilled(false);
		bTabu9.setBorderPainted(false);
		
		//ações dentro dos botoes
		bTabu1.addMouseListener(new MouseAdapter() {
				    @Override
				    public void mouseEntered(MouseEvent e) {
				    	if(!clicked[0])
				    		bTabu1.setIcon(sim);
				    }
				 
				    @Override
				    public void mouseClicked(MouseEvent e) {
				    	if(!clicked[0] && bTabu1.isEnabled()) {
				    		setButtonIcon(bTabu1, 0);
				    	    click_sound.setMicrosecondPosition(0);
				    	    
				    	    clickou = true; //sinaliza que foi clicado
				    	    toServer = "1"; //diz qual botao foi clicado
				    	    
				    	    try {
								sleep(10);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
				    	    click_sound.start();
				    	    
				    	}
				    }
				    
				    @Override
				    public void mouseExited(MouseEvent e) {
				    	if(!clicked[0])
				    		bTabu1.setIcon(null);
				    }
				});
				
		bTabu2.addMouseListener(new MouseAdapter() {
				    @Override
				    public void mouseEntered(MouseEvent e) {
				    	if(!clicked[1])
				    		bTabu2.setIcon(sim);
				    }
				    
				    @Override
				    public void mouseClicked(MouseEvent e) {
				    	if(!clicked[1] && bTabu2.isEnabled()) {
				    		setButtonIcon(bTabu2, 1);
				    		click_sound.setMicrosecondPosition(0);
				    		
				    		clickou = true; //sinaliza que foi clicado
				    	    toServer = "2"; //diz qual botao foi clicado
				    		
				    		try {
								sleep(10);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
				    		
				    		click_sound.start();
				    	}
				    }
				 
				    @Override
				    public void mouseExited(MouseEvent e) {
				    	if(!clicked[1])
				    		bTabu2.setIcon(null);
				    }
				});
				
		bTabu3.addMouseListener(new MouseAdapter() {
				    @Override
				    public void mouseEntered(MouseEvent e) {
				    	if(!clicked[2])
				    		bTabu3.setIcon(sim);
				    }
				    
				    @Override
				    public void mouseClicked(MouseEvent e) {
				    	if(!clicked[2] && bTabu3.isEnabled()) {
				    		setButtonIcon(bTabu3, 2);
				    		click_sound.setMicrosecondPosition(0);
				    		
				    		clickou = true; //sinaliza que foi clicado
				    	    toServer = "3"; //diz qual botao foi clicado
				    		
				    		try {
								sleep(10);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
				    		
				    		click_sound.start();
				    	}
				    }
				 
				    @Override
				    public void mouseExited(MouseEvent e) {
				    	if(!clicked[2])
				    		bTabu3.setIcon(null);
				    }
				});
				
		bTabu4.addMouseListener(new MouseAdapter() {
				    @Override
				    public void mouseEntered(MouseEvent e) {
				    	if(!clicked[3])
				    		bTabu4.setIcon(sim);
				    }
				    
				    @Override
				    public void mouseClicked(MouseEvent e) {
				    	if(!clicked[3] && bTabu4.isEnabled()) {
				    		setButtonIcon(bTabu4, 3);
				    		click_sound.setMicrosecondPosition(0);
				    		
				    		clickou = true; //sinaliza que foi clicado
				    	    toServer = "4"; //diz qual botao foi clicado
				    		
				    		try {
								sleep(10);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
				    		
				    		click_sound.start();
				    	}
				    }
				 
				    @Override
				    public void mouseExited(MouseEvent e) {
				    	if(!clicked[3])
				    		bTabu4.setIcon(null);
				    }
				});
				
		bTabu5.addMouseListener(new MouseAdapter() {
				    @Override
				    public void mouseEntered(MouseEvent e) {
				    	if(!clicked[4])
				    		bTabu5.setIcon(sim);
				    }
				    
				    @Override
				    public void mouseClicked(MouseEvent e) {
				    	if(!clicked[4] && bTabu5.isEnabled()) {
				    		setButtonIcon(bTabu5, 4);
				    		click_sound.setMicrosecondPosition(0);
				    		
				    		clickou = true; //sinaliza que foi clicado
				    	    toServer = "5"; //diz qual botao foi clicado
				    		
				    		try {
								sleep(10);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
				    		
				    		click_sound.start();
				    	}
				    }
				 
				    @Override
				    public void mouseExited(MouseEvent e) {
				    	if(!clicked[4])
				    		bTabu5.setIcon(null);
				    }
				});
			
		bTabu6.addMouseListener(new MouseAdapter() {
				    @Override
				    public void mouseEntered(MouseEvent e) {
				    	if(!clicked[5])
				    		bTabu6.setIcon(sim);
				    }
				    
				    @Override
				    public void mouseClicked(MouseEvent e) {
				    	if(!clicked[5] && bTabu6.isEnabled()) {
				    		setButtonIcon(bTabu6, 5);
				    		click_sound.setMicrosecondPosition(0);
				    		
				    		clickou = true; //sinaliza que foi clicado
				    	    toServer = "6"; //diz qual botao foi clicado
				    		
				    		try {
								sleep(10);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
				    		
				    		click_sound.start();
				    	}
				    }
				 
				    @Override
				    public void mouseExited(MouseEvent e) {
				    	if(!clicked[5])
				    		bTabu6.setIcon(null);
				    }
				});
				
		bTabu7.addMouseListener(new MouseAdapter() {
				    @Override
				    public void mouseEntered(MouseEvent e) {
				    	if(!clicked[6])
				    		bTabu7.setIcon(sim);
				    }
				    
				    @Override
				    public void mouseClicked(MouseEvent e) {
				    	if(!clicked[6] && bTabu7.isEnabled()) {
				    		setButtonIcon(bTabu7, 6);
				    		click_sound.setMicrosecondPosition(0);
				    		
				    		clickou = true; //sinaliza que foi clicado
				    	    toServer = "7"; //diz qual botao foi clicado
				    		
				    		try {
								sleep(10);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
				    		
				    		click_sound.start();
				    	}
				    }
				 
				    @Override
				    public void mouseExited(MouseEvent e) {
				    	if(!clicked[6])
				    		bTabu7.setIcon(null);
				    }
				});
				
		bTabu8.addMouseListener(new MouseAdapter() {
				    @Override
				    public void mouseEntered(MouseEvent e) {
				    	if(!clicked[7])
				    		bTabu8.setIcon(sim);
				    }
				    
				    @Override
				    public void mouseClicked(MouseEvent e) {
				    	if(!clicked[7] && bTabu8.isEnabled()) {
				    		setButtonIcon(bTabu8, 7);
				    		click_sound.setMicrosecondPosition(0);
				    		
				    		clickou = true; //sinaliza que foi clicado
				    	    toServer = "8"; //diz qual botao foi clicado
				    		
				    		try {
								sleep(10);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
				    		
				    		click_sound.start();
				    	}
				    }
				 
				    @Override
				    public void mouseExited(MouseEvent e) {
				    	if(!clicked[7])
				    		bTabu8.setIcon(null);
				    }
				
			}
		);
				
		bTabu9.addMouseListener(new MouseAdapter() {
				    @Override
				    public void mouseEntered(MouseEvent e) {
				    	if(!clicked[8])
				    		bTabu9.setIcon(sim);
				    }
				    
				    @Override
				    public void mouseClicked(MouseEvent e) {
				    	if(!clicked[8] && bTabu9.isEnabled()) {
				    		setButtonIcon(bTabu9, 8);
				    		click_sound.setMicrosecondPosition(0);
				    		
				    		clickou = true; //sinaliza que foi clicado
				    	    toServer = "9"; //diz qual botao foi clicado
				    		
				    		try {
								sleep(10);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
				    		
				    		click_sound.start();
				    	}
				    }
				 
				    @Override
				    public void mouseExited(MouseEvent e) {
				    	if(!clicked[8])
				    		bTabu9.setIcon(null);
				    }
				});

		//botaõ de teste para trocar simbolo
		swit = new JButton("SWITCH");
		swit.setBounds(5, 5, 80, 50);
		swit.setFont(new Font("Tahoma", Font.PLAIN, 16));
		swit.setBackground(new Color(26,148,49));
		swit.setMargin(margem);
		swit.setToolTipText("Trocar o simbolo");
		swit.setFocusPainted(false); //remove o retangulo ao redor do texto
		
				
		//seta a imagem do tabuleiro
		tabuleiro = new JLabel();
		tabuleiro.setIcon(tabu_icon);
		tabuleiro.setBounds(140, 50, 450, 450);
				
		//plano de fundo
		pdf = new JLabel();
		pdf.setIcon(pDeF);
		pdf.setBounds(0, -20, 750, 600);
				
		swit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SwitchSymbol();	
			}
		});
		
		
	}
	
	
	public void run(){
		boolean close = true;
		DisableButtons();
		DisableAll();
		load.start();
		String msg_serv;
		
		while(close) {
			try {
				//espera outro jogador, avisado pelo servidor
				//se o servidor mandar "sair" é pq houve algum erro
				msg_serv = conexao_entrada.readLine();
				System.out.println(msg_serv);
				//caso ocorra algum erro o servidor manda "sair"
				if(msg_serv.equals("sair")) {
					conexao.close();
					conexao_entrada.close();
					conexao_saida.close();
					load.close();
					JOptionPane.showMessageDialog(null, "Disconectado!");
					close = false;
				}
				
				//caso outro jogador se conect o servidor manda "D" (done)
				else if(msg_serv.equals("D")) {
					close = false;
					load.close();
				}

			} catch(SocketException e) {
				JOptionPane.showMessageDialog(Janela1, "Erro! O Servidor pode ter fechado!");
				load.close();
				close = false;
			}catch (IOException e) {
				JOptionPane.showMessageDialog(Janela1, "Erro! O Servidor pode ter fechado!");
				load.close();
				close = false;
			}
		}
		
		Play();
		
		
	}
	
	public void setSocket(Socket conec) {
		this.conexao = conec;
	}
	
	//passa todos os componentes pro Frame principal 
	public JFrame getBoard(JFrame Jan) {
		Janela1 = Jan;
		
		Janela1.getContentPane().add(bTabu1);
		Janela1.getContentPane().add(bTabu2);
		Janela1.getContentPane().add(bTabu3);
		Janela1.getContentPane().add(bTabu4);
		Janela1.getContentPane().add(bTabu5);
		Janela1.getContentPane().add(bTabu6);
		Janela1.getContentPane().add(bTabu7);
		Janela1.getContentPane().add(bTabu8);
		Janela1.getContentPane().add(bTabu9);
		
		Janela1.getContentPane().add(swit);
		Janela1.getContentPane().add(tabuleiro);
		Janela1.getContentPane().add(pdf);
		
		return this.Janela1;
	}
	
	//troca o simbolo 
	public void SwitchSymbol() {
		if(sim.equals(x)) {
			SwitchB(o);
			sim = o;
		}else {
			SwitchB(x);
			sim = x;
		}
	}
	
	//troca os espaços ja clicados
	private void SwitchB(ImageIcon s) {
		if(clicked[0])
			bTabu1.setIcon(s);
		
		if(clicked[1])
			bTabu2.setIcon(s);
		
		if(clicked[2])
			bTabu3.setIcon(s);
		
		if(clicked[3])
			bTabu4.setIcon(s);
		
		if(clicked[4])
			bTabu5.setIcon(s);
		
		if(clicked[5])
			bTabu6.setIcon(s);
		
		if(clicked[6])
			bTabu7.setIcon(s);
		
		if(clicked[7])
			bTabu8.setIcon(s);
		
		if(clicked[8])
			bTabu9.setIcon(s);
	}
	
	//carrega os sons dos clicks
	private void LoadSounds() throws UnsupportedAudioFileException, IOException {
		this.click = AudioSystem.getAudioInputStream(
				getClass().getClassLoader().getResource("awn.wav"));
		
		try {
			this.click_sound = AudioSystem.getClip();
			click_sound.open(click);
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//muda o estado do botão ao clicar nele
	private void setButtonIcon(JButton botao, int n) {
		botao.setIcon(sim);
		clicked[n] = true;
	}
	
	public void setInput(BufferedReader in) {
		this.conexao_entrada = in;
	}
	
	public void setOutput(DataOutputStream out) {
		this.conexao_saida = out;
	}
	
	//desativa os botoes
	private void DisableButtons() {
		bTabu1.setEnabled(false);
		bTabu2.setEnabled(false);
		bTabu3.setEnabled(false);
		bTabu4.setEnabled(false);
		bTabu5.setEnabled(false);
		bTabu6.setEnabled(false);
		bTabu7.setEnabled(false);
		bTabu8.setEnabled(false);
		bTabu9.setEnabled(false);
	}
	
	private void DisableAll() {
		tabuleiro.setEnabled(false);
		pdf.setEnabled(false);
		swit.setEnabled(false);
	}
	
	//ativa os botoes
	private void EnableButtons(){
		bTabu1.setEnabled(true);
		bTabu2.setEnabled(true);
		bTabu3.setEnabled(true);
		bTabu4.setEnabled(true);
		bTabu5.setEnabled(true);
		bTabu6.setEnabled(true);
		bTabu7.setEnabled(true);
		bTabu8.setEnabled(true);
		bTabu9.setEnabled(true);
	}
	
	private void EnableAll() {
		tabuleiro.setEnabled(true);
		pdf.setEnabled(true);
	}
	
	private void Play() {
		String msg_serv;
		try {
			Dialogo("Selecionado primeiro jogador", "Sorte ou Azar?", 2000);//msg para esperar quem será o primeiro
			
			msg_serv = conexao_entrada.readLine();//o servidor envia quem será o primeiro
			
			if(msg_serv.equals("first")) {
				Dialogo("Você será o primeiro!", "Sorte!", 2000);
				First = true;
			}else{
				Dialogo("Você será o segundo!", "Azar ):", 2000);
				First = false;
			}
			
			while(!win) {
				//poe timeout paras mandar resposta
				
				System.out.println("entrou no while");
				
				if(First) {
					EnableButtons();
					EnableAll();
					conexao.setSoTimeout(30000);
					try {
						System.out.println("espernado leitura");
						conexao_saida.writeBytes(getClickButton() + '\n');
						System.out.println("leeeu");
						System.out.println(toServer);
					}catch(SocketTimeoutException e) {
						clickou = true;
						toServer = "0";
						
						conexao.setSoTimeout(0);
						Dialogo("Passou a vez", "Quac", 800);
						conexao_saida.writeBytes("0");
					}
					DisableButtons();
					DisableAll();
				}
				
					
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//metodo para chamar uma caixa de dialogo temporaria
	private void Dialogo(String msg, String title, long time) {
		Object[] opt = {};
		JDialog show;
		JOptionPane con = new JOptionPane();
		//con.setMessage();
		con.setMessage(msg);
		con.setMessageType(1);
		con.setOptions(opt);
		
		show = con.createDialog(title);
		
		//criação de uma thread pra poder rodar um sleep e fechar a dialog box sozinho
		Thread paralelo =
			    new Thread(){
			        public void run(){
			        	try {
			    			Thread.sleep(time);
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
	
	//retorna botão clicado
	private String getClickButton() {
		while(!clickou) {
			
		}
		clickou = false;
		return toServer;
	}
	
}
