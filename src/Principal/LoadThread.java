package Principal;

import java.awt.Cursor;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
public class LoadThread extends Thread{
	private JDialog Load;
	private boolean close = true;
	
	public void run(){
		while(close) {
			Load = StartLoad();
			Load.setVisible(true);
		}
		
	}
	
	public void close() {
		this.close = false;
	}
	
	private JDialog StartLoad() {
		JOptionPane LoadP = new JOptionPane("Esperando outro jogador...");
		LoadP.setMessageType(1);
		LoadP.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		LoadP.setOptionType(0);
		return LoadP.createDialog("Loading");
	}
	
}
