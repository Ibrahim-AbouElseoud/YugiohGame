package eg.edu.guc.yugioh.cards.spells;

import java.util.ArrayList;

import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.MonsterCard;

public class Raigeki extends SpellCard {

	public Raigeki(String name, String desc) {

		super(name, desc);

	}

	public void action(MonsterCard monster) {

		ArrayList<MonsterCard> monsters = new ArrayList<MonsterCard>(Card
				.getBoard().getOpponentPlayer().getField().getMonstersArea());

		Card.getBoard().getOpponentPlayer().getField()
				.removeMonsterToGraveyard(monsters);

	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		
	}

}
