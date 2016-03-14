package eg.edu.guc.yugioh.gui.board;

import java.awt.GridLayout;

import javax.swing.JPanel;


import eg.edu.guc.yugioh.listeners.ActionTest;

public class SpellArea extends JPanel{
	public SpellArea(){
		this.setLayout(new GridLayout(1, 5, 10, 10));
		for(int i=0; i<5;i++){
			//SpellFieldButton temp=new SpellFieldButton();
			//temp.setPreferredSize(new Dimension(80,100));
		//	temp.addActionListener(new ActionTest());// remember to change action
			//this.add(temp);
			this.setOpaque(false);
		}
	}

}
