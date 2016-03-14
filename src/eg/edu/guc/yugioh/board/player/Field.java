package eg.edu.guc.yugioh.board.player;

import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.Location;
import eg.edu.guc.yugioh.cards.Mode;
import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.cards.spells.SpellCard;
import eg.edu.guc.yugioh.exceptions.UnexpectedFormatException;
import eg.edu.guc.yugioh.gui.Main;
import eg.edu.guc.yugioh.gui.board.buttons.MonsterCardB;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Field {

	private final Deck deck;
	private ArrayList<MonsterCard> monstersArea;
	private ArrayList<SpellCard> spellArea;
	private ArrayList<Card> hand;
	private ArrayList<Card> graveyard;
	private Phase phase;
	private boolean monsterAdded = false; // Ensures that the player would only add one monster per turn. Duelist
	public Field() throws IOException,UnexpectedFormatException {

		monstersArea = new ArrayList<MonsterCard>();
		spellArea = new ArrayList<SpellCard>();
		hand = new ArrayList<Card>();
		graveyard = new ArrayList<Card>();
		deck = new Deck();
		phase = Phase.MAIN1;

	}

	public void addMonsterToField(MonsterCard monster, Mode m, boolean isHidden) {
		if (monstersArea.size() < 5) { //normal summon implemented in here

			monster.setHidden(isHidden);
			monster.setMode(m);
			monster.setLocation(Location.FIELD);
			monstersArea.add(monster);
		    monsterAdded = true;
		    hand.remove(monster);
		    
		    
		}
		//else JOptionPane.showMessageDialog(Main.getGameBoard(), "No room to insert or activate!");

	}

	public void addMonsterToField(MonsterCard monster, Mode m,
			ArrayList<MonsterCard> sacrifices) {//sacrifices do in GUI
		 if(monstersArea.size() < 5){

		monster.setHidden((m == Mode.ATTACK) ? false : true);
		monster.setMode(m);
		monster.setLocation(Location.FIELD);
		if(monster.getLevel()<5&&sacrifices.size()==0){
			this.addMonsterToField(monster, m, m == Mode.ATTACK ? false : true);
		}
		else if(monster.getLevel()>=5 && monster.getLevel()<7){
			if(sacrifices.size()==1){
				monstersArea.add(monster);
				hand.remove(monster);
				removeMonsterToGraveyard(sacrifices);
				monsterAdded = true;
			}
		}else{
			if(sacrifices.size()==2){
				monstersArea.add(monster);
				hand.remove(monster);
				removeMonsterToGraveyard(sacrifices);
				monsterAdded = true;
			}
		}
		 }	
		}
		
	
	

	public void removeMonsterToGraveyard(MonsterCard monster) {
		if(monstersArea.contains(monster)){
		monstersArea.remove(monster);
		graveyard.add(monster);
		monster.setLocation(Location.GRAVEYARD);
		}
	}

	public void removeMonsterToGraveyard(ArrayList<MonsterCard> monsters) {
		if(monstersArea.containsAll(monsters)){
		for (int i = 0; i < monsters.size(); i++)
			removeMonsterToGraveyard(monsters.get(i));
		}
	}

	public void addSpellToField(SpellCard spell, MonsterCard monster,
			boolean hidden) {
		spellArea.add(spell);
		hand.remove(spell);
		spell.setLocation(Location.FIELD);
		if (!hidden)
			activateSetSpell(spell, monster);
		
	}

	public void activateSetSpell(SpellCard spell, MonsterCard monster) {
		if(Card.getBoard().getActivePlayer().getField()
				 .getPhase() == Phase.MAIN1 || Card.getBoard().getActivePlayer().getField()
				 .getPhase() == Phase.MAIN2){
		if (getSpellArea().contains(spell)) {
			
			spell.action(monster);
			removeSpellToGraveyard(spell);
		}
		}
	}

	public void removeSpellToGraveyard(SpellCard spell) {
		if(spellArea.contains(spell)){
		spellArea.remove(spell);
		graveyard.add(spell);
		spell.setLocation(Location.GRAVEYARD);
		}
		
	}

	public void removeSpellToGraveyard(ArrayList<SpellCard> spells) {
		
		for (int i = 0; i < spells.size(); i++)
			removeSpellToGraveyard(spells.get(i));
		
	}

	public void addCardToHand() {
		

			if(!this.getDeck().getDeck().isEmpty()){
		Card temp = deck.drawOneCard();
		hand.add(temp);
		temp.setLocation(Location.HAND);
		}else{
			Card.getBoard().setWinner(Card.getBoard().getOpponentPlayer());
			Card.getBoard().end();
		

	}
	}
	public void addNCardsToHand(int n) {
		
		if(!(this.getDeck().getDeck().size()<n))
		hand.addAll(deck.drawNCards(n));
		else{
			Card.getBoard().setWinner(Card.getBoard().getOpponentPlayer());
			Card.getBoard().end();
		}
		
	}

	public Phase getPhase() {
		return phase;
	}

	public void setPhase(Phase phase) {
		this.phase = phase;
	}

	public Deck getDeck() {
		return deck;
	}

	public ArrayList<MonsterCard> getMonstersArea() {
		return monstersArea;
	}

	public ArrayList<SpellCard> getSpellArea() {
		return spellArea;
	}

	public ArrayList<Card> getHand() {
		return hand;
	}

	public ArrayList<Card> getGraveyard() {
		return graveyard;
	}

	public int discardHand() {

		int discardedCards = hand.size();
		for (int i = 0; i < hand.size();) {
			graveyard.add(hand.get(i));
			hand.remove(i).setLocation(Location.GRAVEYARD);
		}
		return (discardedCards);

	}

	public MonsterCard strongestMonsterInGraveyard() {

		MonsterCard strongest = new MonsterCard("", "", 0, 0, 0);
		int strongestValue = 0;
		for (int i = 0; i < graveyard.size(); i++) {

			Card currentCard = graveyard.get(i);
			if (currentCard instanceof MonsterCard) {

				if (((MonsterCard) currentCard).getAttackPoints() > strongestValue) {

					strongest = (MonsterCard) currentCard;
					strongestValue = ((MonsterCard) currentCard)
							.getAttackPoints();

				}

			}

		}

		return (strongest);

	}

	public boolean isMonsterAdded() {
		return monsterAdded;
	}

	public void setMonsterAdded(boolean monsterAdded) {
		this.monsterAdded = monsterAdded;
	}

}
