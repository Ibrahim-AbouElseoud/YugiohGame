package eg.edu.guc.yugioh.cards.spells;

import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.MonsterCard;

public class PotOfGreed extends SpellCard {

	public PotOfGreed(String name, String desc) {
		
		super(name, desc);
		
	}
	
	public void action(MonsterCard monster){
		
		Card.getBoard().getActivePlayer().getField().addNCardsToHand(2);
		//Card.getBoard().getActivePlayer().getField().getDeck().drawOneCard();
		
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		
	}

}
