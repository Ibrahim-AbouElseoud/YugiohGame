package eg.edu.guc.yugioh.gui.board.buttons;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import eg.edu.guc.yugioh.cards.Location;
import eg.edu.guc.yugioh.cards.Mode;
import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.listeners.board.ActionMenuMonster;

public class MonsterCardB extends CardB{
	//private int attack=0;
	//private int defense=0;
	private MonsterCard monster;
	private Boolean isDefense=false;
	//boolean occupied=false;

public MonsterCardB(MonsterCard monster){
		super(monster);
		this.setMonster(monster);
		this.setHorizontalTextPosition(SwingConstants.CENTER);
		this.setText(""+monster.getLevel());
		this.setForeground(Color.WHITE);
		this.addMouseListener(new ActionMenuMonster());
	}/*
	public MonsterCardB(String name,int attack,int defense,String imageAdress){//put JPG in the adress
		super(name, imageAdress);
		this.attack=attack;
		this.defense=defense;
		
		
		
	}*/
	public void setDefense (){// do one that changes mode for attack and defense
		this.setIcon(new ImageIcon("Dcards\\"+this.getName() +".jpg"));
	}
	public void setAttack (){// do one that changes mode for attack and defense
		this.setIcon(new ImageIcon("cards\\"+this.getName() +".jpg"));
	}

	public MonsterCard getMonster() {
		return monster;
	}

	public void setMonster(MonsterCard monster) {
		this.monster = monster;
	}
	public void makeHidden(){
		super.makeHidden();
		this.setIcon(new ImageIcon("Dcards\\Card Back.jpg"));
		this.setText("");

	}
	public void makeVisible(){
		super.makeVisible();
		this.setText(""+monster.getLevel());
	}

}
