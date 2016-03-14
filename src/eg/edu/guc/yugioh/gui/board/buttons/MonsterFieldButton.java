package eg.edu.guc.yugioh.gui.board.buttons;

import java.awt.Dimension;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.gui.board.InvisibleButton;
import eg.edu.guc.yugioh.listeners.ActionTest;

public class MonsterFieldButton extends InvisibleButton {
	String name;
	ImageIcon picture;
	MonsterCard monster;
	boolean isOccupied=false; //ad action listener that when pressed gives possibilty of ATTACKING
	public boolean isOccupied() {
		return isOccupied;
	}
	//public void setOccupied(boolean isOccupied) {
		//this.isOccupied = isOccupied;
	//}
	public MonsterFieldButton(){
		super();
		this.setPreferredSize(new Dimension(70,95));
		this.setVisible(true);
		//this.setIcon(new ImageIcon("backButton9570.jpg"));
		//this.addActionListener(new ActionTest());
	}
	public void setMonster(MonsterCardB monster){
		this.monster=monster.getMonster();
		this.name=monster.getName();
		isOccupied=true;
		picture=monster.getPicture();
		this.setIcon(picture);
		this.setVisible(true);//in battlephase put the attack action listeners to occupied
	}
	public void unAssignMonster(){
		this.monster=null;
		this.setVisible(false);
		isOccupied=false;
	}
	

}
