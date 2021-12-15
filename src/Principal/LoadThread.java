package Principal;

import java.awt.Cursor;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
public class LoadThread extends Thread{
	private int escLoad = 0;
	private JOptionPane Load = new JOptionPane();
	private JDialog show;
	private boolean close = true;
	
	public void run(){
		escLoad = 0;
		//configuração da caixa de dialogo 
		Load.setMessageType(1);
		Load.setMessage("Esperando outro jogador...");
		Load.setOptionType(JOptionPane.OK_CANCEL_OPTION);
		
		//recebe o o dialog do painel 
		show = Load.createDialog("Esperando");
		show.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		
		while(close) {
			//mostra a caixa de dialog de "esperando jogador"
			show.setVisible(true);
			
			//se estiver visivel ele recebe o valor ao ser selecionado
			if(show.isVisible() || close) {
				escLoad = ((Integer)Load.getValue()).intValue();
			}
			
			//se o usuario resolver cancelar a conexao
			if(escLoad == JOptionPane.CANCEL_OPTION){
				show.setVisible(false);
				close = false;
			}
		}
	}
	
	public void close() {
		show.setVisible(false);
		escLoad = JOptionPane.CANCEL_OPTION;
		this.close = false;
	}
	
}
