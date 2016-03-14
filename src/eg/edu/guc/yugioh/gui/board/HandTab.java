package eg.edu.guc.yugioh.gui.board;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.cards.spells.SpellCard;
import eg.edu.guc.yugioh.gui.Main;
import eg.edu.guc.yugioh.gui.board.buttons.MonsterCardB;
import eg.edu.guc.yugioh.gui.board.buttons.SpellCardB;

public class HandTab extends JTabbedPane {
	//private HandPanel p1Hand;
	//private HandPanel p2Hand;
	public HandTab(String P1Name, String P2Name,HandPanel P1, HandPanel P2){
		super();
		this.addTab("P1: " +P1Name,P1 );
		this.addTab("P2: " +P2Name,P2);
		this.setBounds(110, 570,910, 185);
		this.setEnabledAt(1, false); //zero based index
	}
	
}
