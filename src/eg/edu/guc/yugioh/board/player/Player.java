package eg.edu.guc.yugioh.board.player;

import java.io.IOException;
import java.util.ArrayList;

import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.Location;
import eg.edu.guc.yugioh.cards.Mode;
import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.cards.spells.ChangeOfHeart;
import eg.edu.guc.yugioh.cards.spells.MagePower;
import eg.edu.guc.yugioh.cards.spells.MonsterReborn;
import eg.edu.guc.yugioh.cards.spells.SpellCard;
import eg.edu.guc.yugioh.exceptions.DefenseMonsterAttackException;
import eg.edu.guc.yugioh.exceptions.MonsterMultipleAttackException;
import eg.edu.guc.yugioh.exceptions.MultipleMonsterAdditionException;
import eg.edu.guc.yugioh.exceptions.NoMonsterSpaceException;
import eg.edu.guc.yugioh.exceptions.NoSpellSpaceException;
import eg.edu.guc.yugioh.exceptions.UnexpectedFormatException;
import eg.edu.guc.yugioh.exceptions.WrongPhaseException;

public class Player implements Duelist{

	private final String name;
	private int lifePoints;
	private Field field;

	public Player(String name) throws IOException, UnexpectedFormatException {

		this.name = name;
		this.lifePoints = 8000;
		this.field = new Field();

	}

	public int getLifePoints() {
		return lifePoints;
	}

	public void setLifePoints(int lifePoints) {
		this.lifePoints = lifePoints;
	}

	public String getName() {
		return name;
	}

	public Field getField() {
		return field;
	}

	@Override
	public boolean summonMonster(MonsterCard monster) throws NoMonsterSpaceException,WrongPhaseException,MultipleMonsterAdditionException{
		 if(this == Card.getBoard().getActivePlayer()&&(this.getField().getHand().contains(monster))&&(Card.getBoard().getActivePlayer().getField()
				 .getPhase() == Phase.MAIN1 || Card.getBoard().getActivePlayer().getField()
				 .getPhase() == Phase.MAIN2)&&(!this.getField().isMonsterAdded())&&(!Card.getBoard().isEnded())){
			 this.field.addMonsterToField(monster, Mode.ATTACK, false);
			 if(this.getField().getMonstersArea().contains(monster))
				 return true;
		 }
		 if(this.getField().isMonsterAdded())
			 throw new MultipleMonsterAdditionException();
		 if(Card.getBoard().getActivePlayer().getField().getPhase()==Phase.BATTLE){
			 throw new WrongPhaseException();
		 }
		 if(Card.getBoard().getActivePlayer().getField().getMonstersArea().size()>=5){
			 throw new NoMonsterSpaceException();
			 }
		 return false;
	}

	@Override
	public boolean summonMonster(MonsterCard monster,
			ArrayList<MonsterCard> sacrifices){
		if(this == Card.getBoard().getActivePlayer()&&(this.getField().getHand().contains(monster))&&(Card.getBoard().getActivePlayer().getField()
				 .getPhase() == Phase.MAIN1 || Card.getBoard().getActivePlayer().getField()
				 .getPhase() == Phase.MAIN2)&&(!Card.getBoard().getActivePlayer().getField().isMonsterAdded())&&(!this.getField().isMonsterAdded())&&(!Card.getBoard().isEnded())){
			this.field.addMonsterToField(monster, Mode.ATTACK, sacrifices);
			 if(this.getField().getMonstersArea().contains(monster))
				 return true;
		}
		if(this.getField().isMonsterAdded())
			 throw new MultipleMonsterAdditionException();
		 if(Card.getBoard().getActivePlayer().getField().getPhase()==Phase.BATTLE){
			 throw new WrongPhaseException();
		 }
		if(Card.getBoard().getActivePlayer().getField().getMonstersArea().size()>=5){
			 throw new NoMonsterSpaceException();
			 }
		return false;
	}

	@Override
	public boolean setMonster(MonsterCard monster) {
		if(this == Card.getBoard().getActivePlayer()&&(Card.getBoard().getActivePlayer().getField()
				 .getPhase() == Phase.MAIN1 || Card.getBoard().getActivePlayer().getField()
				 .getPhase() == Phase.MAIN2)&&(!this.getField().isMonsterAdded())&&(!Card.getBoard().isEnded())){
			 this.field.addMonsterToField(monster, Mode.DEFENSE, true);
			 if(this.getField().getMonstersArea().contains(monster))
				 return true;
		 }
		if(this.getField().isMonsterAdded())
			 throw new MultipleMonsterAdditionException();
		 if(Card.getBoard().getActivePlayer().getField().getPhase()==Phase.BATTLE){
			 throw new WrongPhaseException();
		 }
		if(Card.getBoard().getActivePlayer().getField().getMonstersArea().size()>=5){
			 throw new NoMonsterSpaceException();
			 }
		return false;
	}

	@Override
	public boolean setMonster(MonsterCard monster,
			ArrayList<MonsterCard> sacrifices) {
		if(this == Card.getBoard().getActivePlayer()&&(Card.getBoard().getActivePlayer().getField()
				 .getPhase() == Phase.MAIN1 || Card.getBoard().getActivePlayer().getField()
				 .getPhase() == Phase.MAIN2)&&(!this.getField().isMonsterAdded())&&(!Card.getBoard().isEnded())){
			this.field.addMonsterToField(monster, Mode.DEFENSE, sacrifices);
			 if(this.getField().getMonstersArea().contains(monster))
				 return true;
		}
		if(this.getField().isMonsterAdded())
			 throw new MultipleMonsterAdditionException();
		 if(Card.getBoard().getActivePlayer().getField().getPhase()==Phase.BATTLE){
			 throw new WrongPhaseException();
		 }
		if(Card.getBoard().getActivePlayer().getField().getMonstersArea().size()>=5){
			 throw new NoMonsterSpaceException();
			 }
		return false;
	}

	@Override
	public boolean setSpell(SpellCard spell) {
		if(this == Card.getBoard().getActivePlayer()&&(this.getField().getHand().contains(spell))&&(Card.getBoard().getActivePlayer().getField()
				 .getPhase() == Phase.MAIN1 || Card.getBoard().getActivePlayer().getField()
				 .getPhase() == Phase.MAIN2)&&(!Card.getBoard().isEnded())){
			this.field.addSpellToField(spell, null, true);
			if(this.getField().getMonstersArea().contains(spell))
				return true;
		}
		 if(Card.getBoard().getActivePlayer().getField().getPhase()==Phase.BATTLE){
			 throw new WrongPhaseException();
		 }
		if(Card.getBoard().getActivePlayer().getField().getSpellArea().size()>=5){
			 throw new NoSpellSpaceException();
			 }
		return false;
	}

	@Override
	public boolean activateSpell(SpellCard spell, MonsterCard monster) {
		if((this == Card.getBoard().getActivePlayer())&&(Card.getBoard().getActivePlayer().getField()
				 .getPhase() == Phase.MAIN1 || Card.getBoard().getActivePlayer().getField()
				 .getPhase() == Phase.MAIN2)&&(Card.getBoard().getActivePlayer().getField().getHand().contains(spell)||
						 Card.getBoard().getActivePlayer().getField().getSpellArea().contains(spell))&&(!Card.getBoard().isEnded())){
			
			if(spell.getLocation()==Location.FIELD){
				if(spell instanceof ChangeOfHeart || spell instanceof MagePower){
					if(spell instanceof ChangeOfHeart)
						if(Card.getBoard().getActivePlayer().getField().getMonstersArea().size()>=5)
							 throw new NoMonsterSpaceException();
					this.field.activateSetSpell(spell, monster);
				}else{
						if(spell instanceof MonsterReborn)
							if(Card.getBoard().getActivePlayer().getField().getMonstersArea().size()>=5)
								 throw new NoMonsterSpaceException();
						this.field.activateSetSpell(spell, null);
					}
					
					
			}else{
				if(spell instanceof ChangeOfHeart || spell instanceof MagePower){
					if(spell instanceof ChangeOfHeart)
						if(Card.getBoard().getActivePlayer().getField().getMonstersArea().size()>=5)
							 throw new NoMonsterSpaceException();
					if(Card.getBoard().getActivePlayer().getField().getSpellArea().size()>=5)	
							 throw new NoSpellSpaceException();
					this.field.addSpellToField(spell, monster, false);
					this.getField().getHand().remove(spell);
					}else{
						if(spell instanceof MonsterReborn)
							if(Card.getBoard().getActivePlayer().getField().getMonstersArea().size()>=5)
								 throw new NoMonsterSpaceException();
						if(Card.getBoard().getActivePlayer().getField().getSpellArea().size()>=5)	
						 throw new NoSpellSpaceException();
						this.field.addSpellToField(spell, monster, false);
					this.getField().getHand().remove(spell);
					}
				}
			if(this.getField().getGraveyard().contains(spell));
			return true;
				}
		if(Card.getBoard().getActivePlayer().getField().getPhase()==Phase.BATTLE)
			 throw new WrongPhaseException();
		return false;
	}

				
			
			
		

	@Override
	public boolean declareAttack(MonsterCard activeMonster,
			MonsterCard opponentMonster) {
		if(this == Card.getBoard().getActivePlayer() && Card.getBoard().getActivePlayer().getField()
				.getPhase()==Phase.BATTLE&&(!activeMonster.isAttacked())&&(activeMonster.getMode()==Mode.ATTACK)){
			activeMonster.action(opponentMonster);
			if(activeMonster.isAttacked())
				return true;
		}
		if(activeMonster.getMode()==Mode.DEFENSE)
			throw new DefenseMonsterAttackException();
		if(activeMonster.isAttacked())
			throw new MonsterMultipleAttackException();
		 if(!(Card.getBoard().getActivePlayer().getField().getPhase()==Phase.BATTLE)){
			 throw new WrongPhaseException();
		 }
		return false;
	}

	@Override
	public boolean declareAttack(MonsterCard activeMonster) {
		if(this == Card.getBoard().getActivePlayer() && Card.getBoard().getOpponentPlayer().getField().getMonstersArea().isEmpty() && Card.getBoard().getActivePlayer().getField()
				.getPhase()==Phase.BATTLE&&(!activeMonster.isAttacked())&&(activeMonster.getMode()==Mode.ATTACK)){
			activeMonster.action();
			if(activeMonster.isAttacked())
				return true;
		}
		if(activeMonster.getMode()==Mode.DEFENSE)
			throw new DefenseMonsterAttackException();
		if(activeMonster.isAttacked())
			throw new MonsterMultipleAttackException();
		 if(!(Card.getBoard().getActivePlayer().getField().getPhase()==Phase.BATTLE)){
			 throw new WrongPhaseException();
		 }
		return false;
	}

	@Override
	public void addCardToHand() {
		if(this == Card.getBoard().getActivePlayer())
			this.field.addCardToHand();
		
	}

	@Override
	public void addNCardsToHand(int n) {
		if(this == Card.getBoard().getActivePlayer())
		this.field.addNCardsToHand(n);
		
	}

	@Override
	public void endPhase() {
		if(this == Card.getBoard().getActivePlayer()&&(!Card.getBoard().isEnded())){
			if(this.field.getPhase() == Phase.MAIN1){
				this.field.setPhase(Phase.BATTLE);
				return;
			}
			else if(this.field.getPhase() == Phase.MAIN2){
				this.field.setPhase(Phase.MAIN1);
				this.endTurn();
				return;
			}else{
				this.field.setPhase(Phase.MAIN2);
				return;
			}
		}
		
	}

	@Override
	public boolean endTurn() {
		
		if(this == Card.getBoard().getActivePlayer()&&(!Card.getBoard().isEnded())){
			this.getField().setMonsterAdded(false);
			for(int i = 0; i<this.getField().getMonstersArea().size();i++){
				this.getField().getMonstersArea().get(i).setSwitched(false);
				this.getField().getMonstersArea().get(i).setAttacked(false);
			}
			Card.getBoard().nextPlayer();
			if(this == Card.getBoard().getOpponentPlayer())
				return true;
		}
		return false;
	}

	@Override
	public boolean switchMonsterMode(MonsterCard monster) {
		if(this == Card.getBoard().getActivePlayer()&&(Card.getBoard().getActivePlayer().getField()
				 .getPhase() == Phase.MAIN1 || Card.getBoard().getActivePlayer().getField()
				 .getPhase() == Phase.MAIN2)&&(!monster.isSwitched())&&this.getField().getMonstersArea()
				 .contains(monster)&&(!Card.getBoard().isEnded())){
			if(monster.getMode()==Mode.ATTACK){
				monster.setMode(Mode.DEFENSE);
				monster.setSwitched(true);
				monster.setHidden(false);
			}
			else{
				monster.setMode(Mode.ATTACK);
				monster.setSwitched(true);
				monster.setHidden(false);
			}
			return true;
		}
		 if((Card.getBoard().getActivePlayer().getField().getPhase()==Phase.BATTLE)){
			 throw new WrongPhaseException();
		 }
		return false;
	}

}
