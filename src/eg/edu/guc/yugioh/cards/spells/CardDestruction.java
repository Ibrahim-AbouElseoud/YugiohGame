package eg.edu.guc.yugioh.cards.spells;

import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.MonsterCard;

public class CardDestruction extends SpellCard {

	public CardDestruction(String name, String desc) {

		super(name, desc);

	}

	public void action(MonsterCard m) {

		int discardedCardsCount = Card.getBoard().getActivePlayer().getField()
				.discardHand();
		Card.getBoard().getActivePlayer().getField()
				.addNCardsToHand(discardedCardsCount);

		discardedCardsCount = Card.getBoard().getOpponentPlayer().getField()
				.discardHand();
		Card.getBoard().getOpponentPlayer().getField()
				.addNCardsToHand(discardedCardsCount);

	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		
	}

}
