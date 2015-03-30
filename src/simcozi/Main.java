package simcozi;

import javax.swing.SwingUtilities;

public class Main {
	protected static GUI gui;
	public static void main(String args[]){
		SwingUtilities.invokeLater(new Runnable() 
		{
			public void run() 
			{
				gui = new GUI();
				gui.setVisible(true);
			}
		});
	}
}
