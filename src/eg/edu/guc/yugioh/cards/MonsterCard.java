package eg.edu.guc.yugioh.cards;

public class MonsterCard extends Card {

	private int level;
	private int defensePoints;
	private int attackPoints;
	private Mode mode;
	private boolean switched = false; // Ensures that the player can only switch a monster once per turn.
	private boolean attacked = false; // Ensures that a monster can only attack once per turn.
	
	public MonsterCard(String n, String desc, int l, int a, int d) {

		super(n, desc);
		this.level = l;
		this.attackPoints = a;
		this.defensePoints = d;
		this.mode = Mode.DEFENSE;

	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getDefensePoints() {
		return defensePoints;
	}

	public void setDefensePoints(int defensePoints) {
		this.defensePoints = defensePoints;
	}

	public int getAttackPoints() {
		return attackPoints;
	}

	public void setAttackPoints(int attackPoints) {
		this.attackPoints = attackPoints;
	}

	public Mode getMode() {
		return mode;
	}

	public void setMode(Mode mode) {
		this.mode = mode;
	}

	@Override
	public void action() {
		Card.getBoard().getOpponentPlayer().setLifePoints(Card.getBoard().getOpponentPlayer()
				.getLifePoints()-this.attackPoints);
		if(Card.getBoard().getOpponentPlayer().getLifePoints()<=0){
			Card.getBoard().setWinner(Card.getBoard().getActivePlayer());
			Card.getBoard().end();
		}
		this.setAttacked(true);
	}

	@Override
	public void action(MonsterCard monster) {
		Mode x = monster.getMode();
		int diffA = this.attackPoints - monster.getAttackPoints();
		int diffD = this.attackPoints - monster.getDefensePoints();
		int oppLife = Card.getBoard().getOpponentPlayer().getLifePoints();
		int actLife = Card.getBoard().getActivePlayer().getLifePoints();
		if(x == Mode.ATTACK){
			if(diffA>0){
				Card.getBoard().getOpponentPlayer().setLifePoints(oppLife - diffA);
				Card.getBoard().getOpponentPlayer().getField().removeMonsterToGraveyard(monster);
			}else if(diffA == 0){
				Card.getBoard().getOpponentPlayer().getField().removeMonsterToGraveyard(monster);
				Card.getBoard().getActivePlayer().getField().removeMonsterToGraveyard(this);
			}else{
				Card.getBoard().getActivePlayer().setLifePoints(actLife + diffA);
				Card.getBoard().getActivePlayer().getField().removeMonsterToGraveyard(this);
			}
		}else{
			if(diffD>0){
				Card.getBoard().getOpponentPlayer().getField().removeMonsterToGraveyard(monster);
				
			}
			else if(diffD<0)
				Card.getBoard().getActivePlayer().setLifePoints(actLife + diffD);
		}
		if(Card.getBoard().getActivePlayer().getLifePoints() <= 0 || Card.getBoard().getOpponentPlayer().getLifePoints()<=0){
			if(Card.getBoard().getActivePlayer().getLifePoints() <= 0)
				Card.getBoard().setWinner(Card.getBoard().getOpponentPlayer());
			else
				Card.getBoard().setWinner(Card.getBoard().getActivePlayer());
			Card.getBoard().end();
		}
		this.setAttacked(true);
		}

	public boolean isSwitched() {
		return switched;
	}

	public void setSwitched(boolean switched) {
		this.switched = switched;
	}

	public boolean isAttacked() {
		return attacked;
	}

	public void setAttacked(boolean attacked) {
		this.attacked = attacked;
	}
}
