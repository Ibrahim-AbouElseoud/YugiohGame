package eg.edu.guc.yugioh.cards.spells;

import java.util.ArrayList;

import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.MonsterCard;

public class HarpieFeatherDuster extends SpellCard {

	public HarpieFeatherDuster(String name, String desc) {

		super(name, desc);

	}

	public void action(MonsterCard monster) {

		ArrayList<SpellCard> spells = new ArrayList<SpellCard>(Card.getBoard()
				.getOpponentPlayer().getField().getSpellArea());
		
		Card.getBoard().getOpponentPlayer().getField()
				.removeSpellToGraveyard(spells);

	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		
	}

}
