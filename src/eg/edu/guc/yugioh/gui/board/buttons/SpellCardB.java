package eg.edu.guc.yugioh.gui.board.buttons;

import eg.edu.guc.yugioh.cards.spells.SpellCard;
import eg.edu.guc.yugioh.listeners.board.ActionMenuSpell;

public class SpellCardB extends CardB {
	private boolean isActive=false;
	private SpellCard spellCard;
	public SpellCardB(SpellCard spellCard) {
		super(spellCard);
		this.spellCard=spellCard;
		this.addMouseListener(new ActionMenuSpell());
		// TODO Auto-generated constructor stub
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public SpellCard getSpellCard() {
		return spellCard;
	}
	public void setSpellCard(SpellCard spellCard) {
		this.spellCard = spellCard;
	}

}
