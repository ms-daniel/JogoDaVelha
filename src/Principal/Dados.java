package Principal;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Dados extends Thread{
	//janela criada anteriormente 
	private JFrame Janela1;

	//sons
	private AudioInputStream click;
	
	//usado para saber quais botoes foram clicados
	private boolean[] clicked = {
			false,false,false,
			false,false,false,
			false,false,false
	};
	
	private Clip oClip;
	
	//imagens dos icones
	private ImageIcon x = new ImageIcon(
			getClass().getClassLoader().getResource("x.png"));
	
	private ImageIcon o = new ImageIcon(
			getClass().getClassLoader().getResource("o.png"));
	
	//seu icone
	private ImageIcon sim = x;
	
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
	
	public Dados(JFrame Janela1) {
		
		try {
			LoadSounds();
		} catch (UnsupportedAudioFileException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		this.Janela1 = Janela1;
		//botões tabuleiro
		bTabu1.setBounds(149, 61, 140, 140);
		bTabu1.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bTabu1.setFocusable(false);
		bTabu1.setContentAreaFilled(false);
		bTabu1.setBorderPainted(false);
		Janela1.getContentPane().add(bTabu1);
				
		bTabu2.setBounds(293, 61, 140, 140);
		bTabu2.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bTabu2.setFocusable(false);
		bTabu2.setContentAreaFilled(false);
		bTabu2.setBorderPainted(false);
		Janela1.getContentPane().add(bTabu2);
				
		bTabu3.setBounds(440, 61, 140, 140);
		bTabu3.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bTabu3.setFocusable(false);
		bTabu3.setContentAreaFilled(false);
		bTabu3.setBorderPainted(false);
		Janela1.getContentPane().add(bTabu3);

		bTabu4.setBounds(149, 207, 140, 140);
		bTabu4.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bTabu4.setFocusable(false);
		bTabu4.setContentAreaFilled(false);
		bTabu4.setBorderPainted(false);
		Janela1.getContentPane().add(bTabu4);
				
		bTabu5.setBounds(293, 207, 140, 140);
		bTabu5.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bTabu5.setFocusable(false);
		bTabu5.setContentAreaFilled(false);
		bTabu5.setBorderPainted(false);
		Janela1.getContentPane().add(bTabu5);
				
		bTabu6.setBounds(440, 207, 140, 140);
		bTabu6.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bTabu6.setFocusable(false);
		bTabu6.setContentAreaFilled(false);
		bTabu6.setBorderPainted(false);
		Janela1.getContentPane().add(bTabu6);
				
		bTabu7.setBounds(149, 353, 140, 140);
		bTabu7.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bTabu7.setFocusable(false);
		bTabu7.setContentAreaFilled(false);
		bTabu7.setBorderPainted(false);
		Janela1.getContentPane().add(bTabu7);
				
		bTabu8.setBounds(293, 353, 140, 140);
		bTabu8.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bTabu8.setFocusable(false);
		bTabu8.setContentAreaFilled(false);
		bTabu8.setBorderPainted(false);
		Janela1.getContentPane().add(bTabu8);
				
		bTabu9.setBounds(440, 353, 140, 140);
		bTabu9.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bTabu9.setFocusable(false);
		bTabu9.setContentAreaFilled(false);
		bTabu9.setBorderPainted(false);
		Janela1.getContentPane().add(bTabu9);
		
		//ações dentro dos botoes
		bTabu1.addMouseListener(new MouseAdapter() {
				    @Override
				    public void mouseEntered(MouseEvent e) {
				    	if(!clicked[0])
				    		bTabu1.setIcon(sim);
				    }
				 
				    @Override
				    public void mouseClicked(MouseEvent e) {
				    	if(!clicked[0]) {
				    		setButtonIcon(bTabu1, 0);
				    	    oClip.setMicrosecondPosition(0);

				    	    try {
								sleep(10);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
				    	    oClip.start();
				    	    
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
				    	if(!clicked[1]) {
				    		setButtonIcon(bTabu2, 1);
				    		oClip.setMicrosecondPosition(0);
				    		
				    		try {
								sleep(10);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
				    		
				    		oClip.start();
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
				    	if(!clicked[2]) {
				    		setButtonIcon(bTabu3, 2);
				    		oClip.setMicrosecondPosition(0);
				    		
				    		try {
								sleep(10);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
				    		
				    		oClip.start();
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
				    	if(!clicked[3]) {
				    		setButtonIcon(bTabu4, 3);
				    		oClip.setMicrosecondPosition(0);
				    		
				    		try {
								sleep(10);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
				    		
				    		oClip.start();
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
				    	if(!clicked[4]) {
				    		setButtonIcon(bTabu5, 4);
				    		oClip.setMicrosecondPosition(0);
				    		
				    		try {
								sleep(10);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
				    		
				    		oClip.start();
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
				    	if(!clicked[5]) {
				    		setButtonIcon(bTabu6, 5);
				    		oClip.setMicrosecondPosition(0);
				    		
				    		try {
								sleep(10);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
				    		
				    		oClip.start();
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
				    	if(!clicked[6]) {
				    		setButtonIcon(bTabu7, 6);
				    		oClip.setMicrosecondPosition(0);
				    		
				    		try {
								sleep(10);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
				    		
				    		oClip.start();
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
				    	if(!clicked[7]) {
				    		setButtonIcon(bTabu8, 7);
				    		oClip.setMicrosecondPosition(0);
				    		
				    		try {
								sleep(10);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
				    		
				    		oClip.start();
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
				    	if(!clicked[8]) {
				    		setButtonIcon(bTabu9, 8);
				    		oClip.setMicrosecondPosition(0);
				    		
				    		try {
								sleep(10);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
				    		
				    		oClip.start();
				    	}
				    }
				 
				    @Override
				    public void mouseExited(MouseEvent e) {
				    	if(!clicked[8])
				    		bTabu9.setIcon(null);
				    }
				});
		
	}
	
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
	
	private void LoadSounds() throws UnsupportedAudioFileException, IOException {
		this.click = AudioSystem.getAudioInputStream(
				getClass().getClassLoader().getResource("sounds/awn.wav"));
		
		try {
			this.oClip = AudioSystem.getClip();
			oClip.open(click);
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void setButtonIcon(JButton botao, int n) {
		botao.setIcon(sim);
		clicked[n] = true;
	}

}
