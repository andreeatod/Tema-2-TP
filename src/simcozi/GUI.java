package simcozi;

import javax.swing.*;

import java.awt.event.*;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class GUI extends JFrame{
///////////////////////////////////////////////////////////////////// input text fields
	private JTextField min_arrival_timeTf  = new JTextField();
	private JTextField max_arrival_timeTf  = new JTextField();
	private JTextField min_service_timeTf  = new JTextField();
	private JTextField max_service_timeTf  = new JTextField();
	private JTextField nr_of_queuesTf      = new JTextField();
	private JTextField simulation_lengthTf = new JTextField();
	static private JTextField[] queues     = new JTextField[5];
	static private JTextField[] waits      = new JTextField[5];
	static private JTextField medWait      = new JTextField();
///////////////////////////////////////////////////////////////////// 
	private JButton    startBtn            = new JButton("Start");
/////////////////////////////////////////////////////////////////////////////////////////
	int minCT, maxCT, minST, maxST, nrQ, sl;
	int ok=0;
	int go=0;
////////////////////////////////////////////////////////////////////////////////constructor
	public GUI(){
		JPanel content = new JPanel();
		content.setLayout(new GridBagLayout());
		content.setBackground(Color.white);
		GridBagConstraints constraint = new GridBagConstraints();
		constraint.insets = new Insets(5,10,5,10);
		this.setVisible(true);
		this.setResizable(false);
/////////////////////////////////////////////////////////////////////
		constraint.fill = GridBagConstraints.BOTH;
		constraint.weightx = 0.1;
		constraint.weighty = 0.1;
		constraint.gridx = 0;
		constraint.gridy = 0;
		constraint.gridwidth = 1;
		constraint.gridheight =2;
		content.add(new JLabel("Timp min intre clienti"), constraint);
		
		constraint.fill = GridBagConstraints.BOTH;
		constraint.gridx = 0;
		constraint.gridy = 2;
		content.add(min_arrival_timeTf,constraint);
		
		constraint.fill = GridBagConstraints.BOTH;
		constraint.gridx = 1;
		constraint.gridy = 0;
		content.add(new JLabel("Timp max intre clienti"),constraint);
		
		constraint.fill = GridBagConstraints.BOTH;
		constraint.gridx = 1;
		constraint.gridy = 2;
		content.add(max_arrival_timeTf,constraint);
		
		constraint.fill = GridBagConstraints.BOTH;
		constraint.gridx = 2;
		constraint.gridy = 0;
		content.add(new JLabel("Timp min de servire"),constraint);
		
		constraint.fill = GridBagConstraints.BOTH;
		constraint.gridx = 2;
		constraint.gridy = 2;
		content.add(min_service_timeTf,constraint);
		
		constraint.fill = GridBagConstraints.BOTH;
		constraint.gridx = 3;
		constraint.gridy = 0;
		content.add(new JLabel("Timp max de servire"),constraint);
		
		constraint.fill = GridBagConstraints.BOTH;
		constraint.gridx = 3;
		constraint.gridy = 2;
		content.add(max_service_timeTf,constraint);
		
		
		constraint.fill = GridBagConstraints.BOTH;
		constraint.gridx = 4;
		constraint.gridy = 0;
		content.add(new JLabel("Nr. cozi"),constraint);
		
		constraint.fill = GridBagConstraints.BOTH;
		constraint.gridx = 4;
		constraint.gridy = 2;
		content.add(nr_of_queuesTf,constraint);
		
		constraint.fill = GridBagConstraints.BOTH;
		constraint.gridx = 5;
		constraint.gridy = 0;
		content.add(new JLabel("Durata simulare"),constraint);
		
		constraint.fill = GridBagConstraints.BOTH;
		constraint.gridx = 5;
		constraint.gridy = 2;
		content.add(simulation_lengthTf,constraint);
		
		constraint.fill = GridBagConstraints.BOTH;
		constraint.gridx = 0;
		constraint.gridy = 4;
		content.add(startBtn,constraint);

		constraint.fill = GridBagConstraints.BOTH;
		constraint.gridx = 0;
		constraint.gridy = 6;
		content.add(new JLabel("Clienti"),constraint);
		
		for(int i=0;i<5;i++){
			constraint.fill = GridBagConstraints.BOTH;
			constraint.gridy = 8+2*i;
			queues[i]=new JTextField();
			content.add(queues[i],constraint);
			queues[i].setEditable(false);
		}
		
		constraint.fill = GridBagConstraints.BOTH;
		constraint.gridx = 1;
		constraint.gridy = 6;
		content.add(new JLabel("Timp total asteptare"),constraint);
		
		for(int i=0;i<5;i++){
			constraint.fill = GridBagConstraints.BOTH;
			constraint.gridx = 1;
			constraint.gridy = 8+2*i;
			waits[i]=new JTextField();
			content.add(waits[i],constraint);
			waits[i].setEditable(false);
		}
		
		constraint.fill = GridBagConstraints.BOTH;
		constraint.gridx = 3;
		constraint.gridy = 6;
		content.add(new JLabel("Timp mediu asteptare"),constraint);
		
		constraint.fill = GridBagConstraints.BOTH;
		constraint.gridx = 3;
		constraint.gridy = 8;
		content.add(medWait,constraint);
		medWait.setEditable(false);
	
		
		this.setContentPane(content);
        this.pack();
        this.setTitle("Simulare case de marcat");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startBtn.addActionListener(new start());
	}  
//////////////////////////////////////////////////////////////////////// alte functii, si listeners
	void showError(String errMessage) {
         JOptionPane.showMessageDialog(this, errMessage);
    }
	
	class start implements ActionListener 
	{
		public void actionPerformed(ActionEvent e)
		{
			
			ok=1;
			/////////////////////////////////////////citeste data intrare
			try {
				minCT=Integer.parseInt(getinput(min_arrival_timeTf));
				if(minCT<0)
					throw new NumberFormatException();
			}
			catch (NumberFormatException nfex) {
				showError("Date input gresite");
				ok=0;
			}
			
			try {
				maxCT=Integer.parseInt(getinput(max_arrival_timeTf));
				if(maxCT<minCT)
					throw new NumberFormatException();
			}
			catch (NumberFormatException nfex) {
				showError("Date input gresite");
				ok=0;
			}
			
			try {
				minST=Integer.parseInt(getinput(min_service_timeTf));
				if(minST<0)
					throw new NumberFormatException();
			}
			catch (NumberFormatException nfex) {
				showError("Date input gresite");
				ok=0;
			}
			
			try {
				maxST=Integer.parseInt(getinput(max_service_timeTf));
				if(maxST<minST)
					throw new NumberFormatException();
			}
			catch (NumberFormatException nfex) {
				showError("Date input gresite");
				ok=0;
			}
			
			try {
				nrQ=Integer.parseInt(getinput(nr_of_queuesTf));
				if(nrQ<1 | nrQ>5)
					throw new NumberFormatException();
			}
			catch (NumberFormatException nfex) {
				showError("Date input gresite");
				ok=0;
			}
			
			try {
				sl=Integer.parseInt(getinput(simulation_lengthTf));
				if(sl<1)
					throw new NumberFormatException();
			}
			catch (NumberFormatException nfex) {
				showError("Date input gresite");
				ok=0;
			}
			
			////////////////////////////////////
			if(ok==1 && go==0){
				go=1;
				final Simulare simul= new Simulare(minCT,maxCT,minST,maxST,nrQ,sl);
				Thread thread = new Thread(new Runnable () 
		    	{
					@Override
					public void run() 
					{
					    simul.run();
					}
				});
	    		thread.start();
			}
			
			
		}
	}
///////////////////////////////////////////////	print functions
	static void setq(String a,int i) {
        queues[i].setText(a);
    }
	
	static void setw(String a,int i) {
        waits[i].setText(a);
    }
	
	static void setmw(String a){
		medWait.setText(a);
	}
///////////////////////////////////////////////	input functions
	String getinput(JTextField tf){
		return tf.getText();
	}
	
}
