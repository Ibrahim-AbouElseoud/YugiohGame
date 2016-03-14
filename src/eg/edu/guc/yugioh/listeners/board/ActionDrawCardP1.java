package eg.edu.guc.yugioh.listeners.board;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.cards.spells.SpellCard;
import eg.edu.guc.yugioh.gui.Main;
import eg.edu.guc.yugioh.gui.board.InvisibleButton;
import eg.edu.guc.yugioh.gui.board.buttons.MonsterCardB;
import eg.edu.guc.yugioh.gui.board.buttons.SpellCardB;

public class ActionDrawCardP1 implements ActionListener { //listener to draw one card
//Note must create one for P1 & another different for P2 then remove the listener
	//when it is other's turn
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		int index=Main.getController().getBoard().getActivePlayer().getField().getHand().size();
		Main.getController().getBoard().getActivePlayer().getField().addCardToHand();
		Card p1Card =Main.getController().getBoard().getActivePlayer().getField().getHand().get(index);//since the .size is already +1
		if(p1Card instanceof SpellCard){ //possible failure for type of spellCard which is abstract
			SpellCard spell=(SpellCard) p1Card;
			SpellCardB a= new SpellCardB(spell);
			Main.getGameBoard().getP1Hand().add(a);
		}else {
			MonsterCard monster=(MonsterCard) p1Card;
			MonsterCardB a=new MonsterCardB(monster);
			Main.getGameBoard().getP1Hand().add(a);
		}
		Main.getGameBoard().getDeckBP1().setText(""+Main.getController().getBoard().getActivePlayer().getField().getDeck().getDeck().size());
		InvisibleButton a=(InvisibleButton) e.getSource();
		a.removeActionListener(this); //to remove listener to prevent from drawing again
		Main.getGameBoard().getEndPhase().setVisible(true);
		Main.getGameBoard().getEndTurn().setVisible(true);
	}

}
