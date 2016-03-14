package eg.edu.guc.yugioh.gui.board.buttons;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import eg.edu.guc.yugioh.cards.spells.SpellCard;
import eg.edu.guc.yugioh.gui.board.InvisibleButton;

public class DEFUNCTSpellFieldButton extends InvisibleButton {
	String name;
	SpellCard spell;
	ImageIcon picture;
	boolean isOccupied=false; //ad action listener that when pressed gives possibilty of ATTACKING
	public DEFUNCTSpellFieldButton(){
		super();
		this.setPreferredSize(new Dimension(70,95));
	}
	public void setSpell(SpellCardB spell){
		this.name=spell.getName();
		this.spell=spell.getSpellCard();
		isOccupied=true;
		picture=spell.getPicture();
		this.setIcon(picture);
		this.setVisible(true);
	}
	public void unAssignSpell(){
		this.spell=null;
		this.setVisible(false);
		isOccupied=false;
	}
}
