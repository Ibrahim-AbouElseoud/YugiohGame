package eg.edu.guc.yugioh.gui.board;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.gui.board.buttons.MonsterCardB;
import eg.edu.guc.yugioh.listeners.ActionTest;
import eg.edu.guc.yugioh.listeners.board.ActionToAttack;

public class MonsterArea extends JPanel{
//private ArrayList <MonsterCardB> monsters=new ArrayList<MonsterCardB>();

public MonsterArea(){ //new idea instead of adding a new type just remove from hand and insert to here the monsterCardButton
	//this.getComponent(n)//to acess components
	
	this.setLayout(new GridLayout(1, 5, 10, 10));
	/*
	for(int i=0; i<5;i++){
		MonsterFieldButton temp=new MonsterFieldButton();
		//temp.setPreferredSize(new Dimension(80,95));
		temp.addActionListener(new ActionTest());//replace this with an another action listener , maybe when a monster is added to field set the actionToAttack listener
		this.add(temp);
		
	}*/
	//JButton test=new JButton("show PLZ");
	//this.add(test);
	//this.setVisible(true);
	//test.requestFocusInWindow(); 
	this.setOpaque(false);
	//this.setBounds(x, y, width, height); do this in GUI Board itself
	
}
	public void addListeners(){
		for(int i=0; i<5;i++){
			if(this.getComponent(i)!=null){
		//	MonsterFieldButton monsterButton=(MonsterFieldButton) this.getComponent(i);
			//if(monsterButton.isOccupied()){
			//if(monsterButton.set)
				//monsterButton.removeActionListener();
		//		monsterButton.addActionListener(new ActionToAttack()); //attack listener
			//}
		}
		}
	}
	public void removeListeners(){
		
	}


}
