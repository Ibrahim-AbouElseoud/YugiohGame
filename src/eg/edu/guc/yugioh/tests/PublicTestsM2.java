package eg.edu.guc.yugioh.tests;

import static org.junit.Assert.*;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import org.junit.Test;

import eg.edu.guc.yugioh.board.Board;
import eg.edu.guc.yugioh.board.player.Duelist;
import eg.edu.guc.yugioh.board.player.Phase;
import eg.edu.guc.yugioh.board.player.Player;
import eg.edu.guc.yugioh.cards.*;
import eg.edu.guc.yugioh.cards.spells.PotOfGreed;
import eg.edu.guc.yugioh.cards.spells.Raigeki;
import eg.edu.guc.yugioh.cards.spells.SpellCard;

public class PublicTestsM2 {

	@Test(timeout = 1000)
	public void testActivateSpell() throws Exception {

		Player p1 = new Player("Yugi");
		Player p2 = new Player("Kaiba");
		Board board = new Board();
		board.startGame(p1, p2);

		int handSize = Card.getBoard().getActivePlayer().getField().getHand()
				.size();
		int DeckSize = Card.getBoard().getActivePlayer().getField().getDeck()
				.getDeck().size();

		PotOfGreed pog = new PotOfGreed("Pot Of Greed", "Draw 2 extra cards.");

		Card.getBoard().getActivePlayer().activateSpell(pog, null);

		assertEquals(
				"This Spell card is not in Hand, it should not be activated.",
				handSize, Card.getBoard().getActivePlayer().getField()
						.getHand().size());

		assertEquals(
				"This Spell card is not set on the field, it should not be activated.",
				DeckSize, Card.getBoard().getActivePlayer().getField()
						.getDeck().getDeck().size());

		Card.getBoard().getActivePlayer().getField().getHand().add(pog);
		pog.setLocation(Location.HAND);

		board.getActivePlayer().setSpell(pog);

		board.getActivePlayer().endPhase();

		handSize = Card.getBoard().getActivePlayer().getField().getHand()
				.size();
		DeckSize = Card.getBoard().getActivePlayer().getField().getDeck()
				.getDeck().size();

		Card.getBoard().getActivePlayer().activateSpell(pog, null);

		assertEquals(
				"This Spell card should not be activated in battle phase. It shouldn't be sent to the graveyard.",
				0, Card.getBoard().getActivePlayer().getField().getGraveyard()
						.size());

		assertEquals(
				"This Spell card should not be activated in battle phase.",
				handSize, Card.getBoard().getActivePlayer().getField()
						.getHand().size());

		assertEquals(
				"This Spell card should not be activated in battle phase.",
				DeckSize, Card.getBoard().getActivePlayer().getField()
						.getDeck().getDeck().size());

	}

	@Test(timeout = 1000)
	public void testActivatingSpellNotInYourTurn() throws Exception {

		Board board = new Board();
		Player p1 = new Player("Yugi");
		Player p2 = new Player("Kaiba");
		board.startGame(p1, p2);

		Raigeki s = new Raigeki("Raigeki", "Destroy");

		board.getOpponentPlayer().getField().getSpellArea().add(s);

		board.getOpponentPlayer().activateSpell(s, null);

		assertEquals(
				"Opponent player should not be able to activate his/her spells.",
				0, board.getOpponentPlayer().getField().getGraveyard().size());

	}

	@Test(timeout = 1000)
	public void testAddingMonsterInBattlePhase() throws Exception {

		Board board = new Board();
		Player p1 = new Player("Yugi");
		Player p2 = new Player("Kaiba");
		board.startGame(p1, p2);

		MonsterCard m = new MonsterCard("Vorse Raider", "A warrior beast", 4,
				1900, 1200);

		board.getActivePlayer().endPhase();

		board.getActivePlayer().getField().getHand().add(m);
		m.setLocation(Location.HAND);
		int handSize = board.getActivePlayer().getField().getHand().size();

		board.getActivePlayer().summonMonster(m);

		assertEquals("Should not be able to add monster in battle phase.", 0,
				board.getActivePlayer().getField().getMonstersArea().size());
		assertEquals(
				"The monster was not added, it should not be removed from Hand.",
				handSize, board.getActivePlayer().getField().getHand().size());

	}

	@Test(timeout = 1000)
	public void testAddingMonsterToFieldNotInHand() throws Exception {

		Board board = new Board();
		Player p1 = new Player("Yugi");
		Player p2 = new Player("Kaiba");
		board.startGame(p1, p2);

		MonsterCard m = new MonsterCard("Vorse Raider", "A warrior beast", 4,
				1900, 1200);

		board.getActivePlayer().summonMonster(m);

		assertEquals(
				"Should not be able to add monster that is not in the players hand.",
				0, board.getActivePlayer().getField().getMonstersArea().size());

	}

	@Test(timeout = 1000)
	public void testAddingMonsterToFieldNotInYourTurn() throws Exception {

		Board board = new Board();
		Player p1 = new Player("Yugi");
		Player p2 = new Player("Kaiba");
		board.startGame(p1, p2);

		MonsterCard m = new MonsterCard("Vorse Raider", "A warrior beast", 4,
				1900, 1200);

		board.getOpponentPlayer().getField().getHand().add(m);
		m.setLocation(Location.HAND);

		board.getOpponentPlayer().summonMonster(m);

		assertEquals(
				"Opponent player should not be able to add monsters to his/her field.",
				0, board.getOpponentPlayer().getField().getMonstersArea()
						.size());

	}

	@Test(timeout = 1000)
	public void testAddingSpellInBattlePhase() throws Exception {

		Board board = new Board();
		Player p1 = new Player("Yugi");
		Player p2 = new Player("Kaiba");
		board.startGame(p1, p2);

		Raigeki s = new Raigeki("Raigeki", "Destroy");

		board.getActivePlayer().endPhase();

		board.getActivePlayer().getField().getHand().add(s);
		s.setLocation(Location.HAND);
		int handSize = board.getActivePlayer().getField().getHand().size();

		board.getActivePlayer().setSpell(s);

		assertEquals("Should not be able to add spell in battle phase.", 0,
				board.getActivePlayer().getField().getSpellArea().size());
		assertEquals(
				"The spell was not added, it should not be removed from Hand.",
				handSize, board.getActivePlayer().getField().getHand().size());

	}

	@Test(timeout = 1000)
	public void testAddingSpellToFieldNotInHand() throws Exception {

		Board board = new Board();
		Player p1 = new Player("Yugi");
		Player p2 = new Player("Kaiba");
		board.startGame(p1, p2);

		Raigeki s = new Raigeki("Raigeki", "Destroy");

		board.getActivePlayer().setSpell(s);

		assertEquals(
				"Should not be able to add spell that is not in the players hand.",
				0, board.getActivePlayer().getField().getSpellArea().size());

	}

	@Test(timeout = 1000)
	public void testAttackingOpponentDirectly() throws Exception {
		Board board = new Board();
		Player p1 = new Player("Yugi");
		Player p2 = new Player("Kaiba");
		board.startGame(p1, p2);

		MonsterCard BlueEyes = new MonsterCard("Blue Eyes White Dragon",
				"Legendary Dragon", 3, 3000, 2500);

		board.getActivePlayer().getField().getHand().add(BlueEyes);
		BlueEyes.setLocation(Location.HAND);

		board.getActivePlayer().summonMonster(BlueEyes);

		int opLP = board.getOpponentPlayer().getLifePoints();
		board.getActivePlayer().endPhase();

		BlueEyes.action();

		assertEquals(
				"Direct attack decreases montser attack points from opponent life points.",
				opLP - 3000, board.getOpponentPlayer().getLifePoints());

	}

	@Test(timeout = 1000)
	public void testAttackingSameDefenseMonster() throws Exception {

		Board board = new Board();
		Player p1 = new Player("Yugi");
		Player p2 = new Player("Kaiba");
		board.startGame(p1, p2);

		MonsterCard babyDragon = new MonsterCard("Baby Dragon",
				"Evolves to millinieum Dragon", 3, 1200, 700);
		MonsterCard vorseRaider = new MonsterCard("Vorse Raider",
				"Beast Warrior", 4, 1900, 1200);

		board.getActivePlayer().getField().getHand().add(babyDragon);
		babyDragon.setLocation(Location.HAND);
		board.getActivePlayer().summonMonster(babyDragon);
		board.getActivePlayer().endTurn();

		board.getActivePlayer().getField().getHand().add(vorseRaider);
		vorseRaider.setLocation(Location.HAND);
		board.getActivePlayer().setMonster(vorseRaider);
		board.getActivePlayer().endTurn();

		int opLP = board.getOpponentPlayer().getLifePoints();
		int lp = board.getActivePlayer().getLifePoints();

		board.getActivePlayer().endPhase();

		babyDragon.action(vorseRaider);

		assertEquals("Defending player lifepoint will not be affected.", opLP,
				board.getOpponentPlayer().getLifePoints());
		assertEquals("Attacking player lifepoint will not be affected.", lp,
				board.getActivePlayer().getLifePoints());

		assertEquals("Attacked monster shouldn't be destroyed.", vorseRaider,
				board.getOpponentPlayer().getField().getMonstersArea().get(0));
		assertEquals("Attacking monster shouldn't be destroyed.", babyDragon,
				board.getActivePlayer().getField().getMonstersArea().get(0));

	}

	@Test(timeout = 1000)
	public void testAttackingStrongerAttackMonster() throws Exception {

		Board board = new Board();
		Player p1 = new Player("Yugi");
		Player p2 = new Player("Kaiba");
		board.startGame(p1, p2);

		MonsterCard BlueEyes = new MonsterCard("Blue Eyes White Dragon",
				"Legendary Dragon", 3, 3000, 2500);
		MonsterCard vorseRaider = new MonsterCard("Vorse Raider",
				"Beast Warrior", 4, 1900, 1200);

		board.getActivePlayer().getField().getHand().add(vorseRaider);
		vorseRaider.setLocation(Location.HAND);
		board.getActivePlayer().summonMonster(vorseRaider);
		board.getActivePlayer().endTurn();

		board.getActivePlayer().getField().getHand().add(BlueEyes);
		BlueEyes.setLocation(Location.HAND);
		board.getActivePlayer().summonMonster(BlueEyes);
		board.getActivePlayer().endTurn();

		int opLP = board.getOpponentPlayer().getLifePoints();
		int lp = board.getActivePlayer().getLifePoints();

		board.getActivePlayer().endPhase();

		vorseRaider.action(BlueEyes);

		assertEquals(
				"Difference should be deducted from attacking player's life points.",
				lp - 1100, board.getActivePlayer().getLifePoints());
		assertEquals("Defending player life points should not be affected.",
				opLP, board.getOpponentPlayer().getLifePoints());

		assertEquals("Attacking monster should be moved to the graveyard.",
				vorseRaider, board.getActivePlayer().getField().getGraveyard()
						.get(0));
		assertEquals("Attacked monster shouldn't be destroyed.", BlueEyes,
				board.getOpponentPlayer().getField().getMonstersArea().get(0));

	}

	@Test(timeout = 1000)
	public void testAttackingWeakerAttackMonster() throws Exception {

		Board board = new Board();
		Player p1 = new Player("Yugi");
		Player p2 = new Player("Kaiba");
		board.startGame(p1, p2);

		MonsterCard BlueEyes = new MonsterCard("Blue Eyes White Dragon",
				"Legendary Dragon", 3, 3000, 2500);
		MonsterCard vorseRaider = new MonsterCard("Vorse Raider",
				"Beast Warrior", 4, 1900, 1200);

		board.getActivePlayer().getField().getHand().add(BlueEyes);
		BlueEyes.setLocation(Location.HAND);
		board.getActivePlayer().summonMonster(BlueEyes);
		board.getActivePlayer().endTurn();

		board.getActivePlayer().getField().getHand().add(vorseRaider);
		vorseRaider.setLocation(Location.HAND);
		board.getActivePlayer().summonMonster(vorseRaider);
		board.getActivePlayer().endTurn();

		int opLP = board.getOpponentPlayer().getLifePoints();
		int lp = board.getActivePlayer().getLifePoints();

		board.getActivePlayer().endPhase();

		BlueEyes.action(vorseRaider);

		assertEquals(
				"Difference should be deducted from opponent's life points.",
				opLP - 1100, board.getOpponentPlayer().getLifePoints());
		assertEquals("Attacking player's life points should not be affected.",
				lp, board.getActivePlayer().getLifePoints());

		assertEquals("Attacked monster should be moved to the graveyard.",
				vorseRaider, board.getOpponentPlayer().getField()
						.getGraveyard().get(0));
		assertEquals("Attacking monster shouldn't be destroyed.", BlueEyes,
				board.getActivePlayer().getField().getMonstersArea().get(0));

	}

	@Test(timeout = 1000)
	public void testAttackingWeakerDefensekMonster() throws Exception {

		Board board = new Board();
		Player p1 = new Player("Yugi");
		Player p2 = new Player("Kaiba");
		board.startGame(p1, p2);

		MonsterCard BlueEyes = new MonsterCard("Blue Eyes White Dragon",
				"Legendary Dragon", 3, 3000, 2500);
		MonsterCard vorseRaider = new MonsterCard("Vorse Raider",
				"Beast Warrior", 4, 1900, 1200);

		board.getActivePlayer().getField().getHand().add(BlueEyes);
		BlueEyes.setLocation(Location.HAND);
		board.getActivePlayer().summonMonster(BlueEyes);
		board.getActivePlayer().endTurn();

		board.getActivePlayer().getField().getHand().add(vorseRaider);
		vorseRaider.setLocation(Location.HAND);
		board.getActivePlayer().setMonster(vorseRaider);
		board.getActivePlayer().endTurn();

		int opLP = board.getOpponentPlayer().getLifePoints();
		int lp = board.getActivePlayer().getLifePoints();

		board.getActivePlayer().endPhase();

		BlueEyes.action(vorseRaider);

		assertEquals("Defending player's life points should not be affected.",
				opLP, board.getOpponentPlayer().getLifePoints());
		assertEquals("Attacking player's life points should not be affected.",
				lp, board.getActivePlayer().getLifePoints());

		assertEquals("Attacked monster should be moved to the graveyard.",
				vorseRaider, board.getOpponentPlayer().getField()
						.getGraveyard().get(0));
		assertEquals("Attacking monster shouldn't be destroyed.", BlueEyes,
				board.getActivePlayer().getField().getMonstersArea().get(0));

	}

	@Test(timeout = 1000)
	public void testAttackMethods() throws Exception {

		Class<?>[] args = new Class[1];
		args[0] = MonsterCard.class;
		Method m = Duelist.class.getDeclaredMethod("declareAttack", args);

		assertTrue("Must assign correct return type to interface methods.", m
				.getReturnType().equals(Boolean.TYPE));

		args = new Class[2];
		args[0] = MonsterCard.class;
		args[1] = MonsterCard.class;
		m = Duelist.class.getDeclaredMethod("declareAttack", args);

		assertTrue("Must assign correct return type to interface methods.", m
				.getReturnType().equals(Boolean.TYPE));

	}

	@Test(timeout = 1000)
	public void testAttackOneSelf() throws Exception {

		Board board = new Board();
		Player p1 = new Player("Yugi");
		Player p2 = new Player("Kaiba");
		board.startGame(p1, p2);

		MonsterCard babyDragon = new MonsterCard("Baby Dragon",
				"Evolves to millinieum Dragon", 3, 1200, 700);
		MonsterCard BlueEyes = new MonsterCard("Blue Eyes White Dragon",
				"Legendary Dragon", 3, 3000, 2500);

		board.getActivePlayer().getField().getHand().add(babyDragon);
		babyDragon.setLocation(Location.HAND);
		board.getActivePlayer().summonMonster(babyDragon);
		board.getActivePlayer().endTurn();

		board.getActivePlayer().getField().getHand().add(BlueEyes);
		BlueEyes.setLocation(Location.HAND);
		board.getActivePlayer().setMonster(BlueEyes);
		board.getActivePlayer().endTurn();

		board.getActivePlayer().declareAttack(babyDragon, babyDragon);

		assertEquals("A monster should not be able to attack itself.", 0, Card
				.getBoard().getActivePlayer().getField().getGraveyard().size());

	}

	@Test(timeout = 1000)
	public void testAttackOutsideBattle() throws Exception {

		Board board = new Board();
		Player p1 = new Player("Yugi");
		Player p2 = new Player("Kaiba");
		board.startGame(p1, p2);

		MonsterCard BlueEyes = new MonsterCard("Blue Eyes White Dragon",
				"Legendary Dragon", 3, 3000, 2500);

		board.getActivePlayer().getField().getHand().add(BlueEyes);
		BlueEyes.setLocation(Location.HAND);
		board.getActivePlayer().summonMonster(BlueEyes);
		board.getActivePlayer().endTurn();

		board.getActivePlayer().endTurn();

		int lp = board.getActivePlayer().getLifePoints();
		int oppLP = board.getOpponentPlayer().getLifePoints();

		board.getActivePlayer().getField().setPhase(Phase.MAIN2);
		board.getActivePlayer().declareAttack(BlueEyes);

		board.getActivePlayer().getField().setPhase(Phase.MAIN1);
		board.getActivePlayer().declareAttack(BlueEyes);

		assertEquals("A monster Cannot attack outside the battle phase.",
				oppLP, board.getOpponentPlayer().getLifePoints());
		assertEquals("A monster Cannot attack outside the battle phase.", lp,
				board.getActivePlayer().getLifePoints());

		board.getActivePlayer().getField().setPhase(Phase.BATTLE);

		board.getActivePlayer().declareAttack(BlueEyes);

		assertTrue(
				"Trying to attack in Main phase shouldn't count as the monster's attack limit in the turn.",
				(board.getOpponentPlayer().getLifePoints() != oppLP));

	}

	@Test(timeout = 1000)
	public void testAttackTwice() throws Exception {

		Board board = new Board();
		Player p1 = new Player("Yugi");
		Player p2 = new Player("Kaiba");
		board.startGame(p1, p2);

		MonsterCard BlueEyes = new MonsterCard("Blue Eyes White Dragon",
				"Legendary Dragon", 3, 3000, 2500);

		board.getActivePlayer().getField().getHand().add(BlueEyes);
		BlueEyes.setLocation(Location.HAND);
		board.getActivePlayer().summonMonster(BlueEyes);
		board.getActivePlayer().endTurn();

		board.getActivePlayer().endTurn();
		board.getActivePlayer().endPhase();

		int oppLP = board.getOpponentPlayer().getLifePoints();

		board.getActivePlayer().declareAttack(BlueEyes);

		int oppLPAfter = board.getOpponentPlayer().getLifePoints();

		assertTrue("Player should be able to directly attack the opponent.",
				oppLPAfter != oppLP);

		board.getActivePlayer().declareAttack(BlueEyes);

		assertTrue(
				"Player should not be able to attack with same monster twice.",
				board.getOpponentPlayer().getLifePoints() == oppLPAfter);

	}

	@Test(timeout = 1000)
	public void testCardAbstraction() {

		assertTrue("Card class should be abstract.",
				Modifier.isAbstract(Card.class.getModifiers()));

	}

	@Test(timeout = 1000)
	public void testConinuePlayingAfterGameOver() throws Exception {

		Board board = new Board();
		Player p1 = new Player("Yugi");
		Player p2 = new Player("Kaiba");
		board.startGame(p1, p2);

		MonsterCard vorseRaider = new MonsterCard("Vorse Raider",
				"A warrior beast", 4, 1900, 1200);

		MonsterCard vorseRaider1 = new MonsterCard("Vorse Raider",
				"A warrior beast", 4, 1900, 1200);

		MonsterCard vorseRaider2 = new MonsterCard("Vorse Raider",
				"A warrior beast", 4, 1900, 1200);

		Raigeki raigeki1 = new Raigeki("Raigeki", "destroy");

		Raigeki raigeki2 = new Raigeki("Raigeki", "destroy");

		board.getActivePlayer().getField().getMonstersArea().add(vorseRaider);
		vorseRaider.setLocation(Location.FIELD);
		vorseRaider.setMode(Mode.ATTACK);

		board.getActivePlayer().getField().getMonstersArea().add(vorseRaider1);
		vorseRaider1.setLocation(Location.FIELD);
		vorseRaider1.setMode(Mode.ATTACK);

		board.getActivePlayer().getField().getHand().add(vorseRaider2);
		vorseRaider2.setLocation(Location.HAND);

		board.getActivePlayer().getField().getSpellArea().add(raigeki1);
		raigeki1.setHidden(true);
		raigeki1.setLocation(Location.FIELD);

		board.getActivePlayer().getField().getHand().add(raigeki2);
		raigeki2.setLocation(Location.HAND);

		board.getOpponentPlayer().setLifePoints(100);
		board.getActivePlayer().endPhase();

		board.getActivePlayer().declareAttack(vorseRaider);

		Player active = board.getActivePlayer();
		board.getActivePlayer().endTurn();

		assertEquals(
				"You should not be able to switch turns after the game is over.",
				active, board.getActivePlayer());

		Phase phase = board.getActivePlayer().getField().getPhase();
		board.getActivePlayer().endPhase();

		assertEquals(
				"You should not be able to switch phases after the game is over.",
				phase, board.getActivePlayer().getField().getPhase());

		Mode mode = vorseRaider.getMode();
		board.getActivePlayer().switchMonsterMode(vorseRaider1);

		assertEquals(
				"You should not be able to switch monster mode after the game is over.",
				mode, vorseRaider.getMode());

		int size = board.getActivePlayer().getField().getMonstersArea().size();
		board.getActivePlayer().summonMonster(vorseRaider2);

		assertEquals(
				"You should not be able to summon a monster after the game is over.",
				size, board.getActivePlayer().getField().getMonstersArea()
						.size());

		size = board.getActivePlayer().getField().getMonstersArea().size();
		board.getActivePlayer().setMonster(vorseRaider2);

		assertEquals(
				"You should not be able to set a monster after the game is over.",
				size, board.getActivePlayer().getField().getMonstersArea()
						.size());

		size = board.getActivePlayer().getField().getSpellArea().size();
		board.getActivePlayer().setSpell(raigeki2);

		assertEquals(
				"You should not be able to set a spell after the game is over.",
				size, board.getActivePlayer().getField().getSpellArea().size());

		size = board.getActivePlayer().getField().getGraveyard().size();
		board.getActivePlayer().activateSpell(raigeki1, null);
		board.getActivePlayer().activateSpell(raigeki2, null);

		assertEquals(
				"You should not be able to activate a spell after the game is over.",
				size, board.getActivePlayer().getField().getGraveyard().size());

	}

	@Test(timeout = 1000)
	public void testDeclareAttackNotInYourTurn() throws Exception {

		Board board = new Board();
		Player p1 = new Player("Yugi");
		Player p2 = new Player("Kaiba");
		board.startGame(p1, p2);

		MonsterCard vorseRaider = new MonsterCard("Vorse Raider",
				"A warrior beast", 4, 1900, 1200);
		MonsterCard babyDragon = new MonsterCard("Baby Dragon",
				"Evolves to millinieum Dragon", 4, 1200, 700);

		vorseRaider.setMode(Mode.ATTACK);
		board.getOpponentPlayer().getField().getMonstersArea().add(vorseRaider);

		board.getActivePlayer().getField().getHand().add(babyDragon);
		babyDragon.setLocation(Location.HAND);

		board.getActivePlayer().summonMonster(babyDragon);

		board.getActivePlayer().getField().setPhase(Phase.BATTLE);

		board.getOpponentPlayer().declareAttack(vorseRaider, babyDragon);

		assertEquals(
				"Opponent player should not be able to attack. Lifepoints should remain the same.",
				8000, board.getActivePlayer().getLifePoints());
		assertEquals(
				"Opponent player should not be able to attack. Graveyard should not increase.",
				0, board.getActivePlayer().getField().getGraveyard().size());

	}

	@Test(timeout = 1000)
	public void testDrawNCards() throws Exception {

		Player p1 = new Player("Yugi");
		Player p2 = new Player("Kaiba");
		Board board = new Board();
		board.startGame(p1, p2);

		p1 = Card.getBoard().getActivePlayer();

		int d1 = p1.getField().getDeck().getDeck().size();

		ArrayList<Card> d = p1.getField().getDeck().drawNCards(7);

		assertEquals("Should return 7 cards from deck.", 7, d.size());
		assertEquals("Should remove 7 cards from deck.", d1 - 7, p1.getField()
				.getDeck().getDeck().size());
		for (int i = 0; i < d.size(); i++)
			assertEquals(
					"The location of the cards in the deck should be DECK.",
					Location.DECK, d.get(i).getLocation());

		d1 = p1.getField().getDeck().getDeck().size();

		p1.addNCardsToHand(d1 + 2);

		assertEquals("Opponent player should be declared as winner.",
				board.getOpponentPlayer(), board.getWinner());

	}

	@Test(timeout = 1000)
	public void testEndPhase() throws Exception {

		Board board = new Board();
		Player p1 = new Player("Yugi");
		Player p2 = new Player("Kaiba");
		board.startGame(p1, p2);

		Player player1 = board.getActivePlayer();

		player1.endPhase();
		assertEquals("Phase sequence should be main1 -> battle -> main 2.",
				Phase.BATTLE, player1.getField().getPhase());

		player1.endPhase();
		assertEquals("Phase sequence should be main1 -> battle -> main 2.",
				Phase.MAIN2, player1.getField().getPhase());

		int player1OpponentHandSize = board.getOpponentPlayer().getField()
				.getHand().size();

		player1.endPhase();
		assertTrue(
				"Phase sequence should be main1 -> battle -> main 2 and repeat with changing players.",
				player1 != board.getActivePlayer());
		assertEquals(
				"Phase sequence should be main1 -> battle -> main 2 and repeat with changing players.",
				Phase.MAIN1, board.getActivePlayer().getField().getPhase());

		assertTrue("Should not create new players while switching turns.",
				board.getActivePlayer() == p1 || board.getActivePlayer() == p2);
		assertTrue("Should not create new players while switching turns.",
				board.getOpponentPlayer() == p1
						|| board.getOpponentPlayer() == p2);

		assertEquals("Next Player draws one more card.",
				player1OpponentHandSize + 1, board.getActivePlayer().getField()
						.getHand().size());

	}

	@Test(timeout = 1000)
	public void testEndPhaseTurnMethods() throws Exception {

		Method m = Duelist.class.getDeclaredMethod("endPhase");
		assertTrue("Must assign correct return type to interface methods.", m
				.getReturnType().equals(Void.TYPE));

		m = Duelist.class.getDeclaredMethod("endTurn");
		assertTrue("Must assign correct return type to interface methods.", m
				.getReturnType().equals(Boolean.TYPE));

	}

	@Test(timeout = 1000)
	public void testEndTurnInBattle() throws Exception {

		Board board = new Board();
		Player p1 = new Player("Yugi");
		Player p2 = new Player("Kaiba");
		board.startGame(p1, p2);

		Player Before = board.getActivePlayer();

		Before.getField().setPhase(Phase.BATTLE);

		int BeforeOpponentHandSize = board.getOpponentPlayer().getField()
				.getHand().size();

		Before.endTurn();

		Player After = board.getActivePlayer();

		assertTrue("Players should not be the same after ending the turn.",
				Before != After);
		assertTrue("Should not create new players when switching turns.",
				board.getActivePlayer() == p1 || board.getActivePlayer() == p2);
		assertTrue("Should not create new players when switching turns.",
				board.getOpponentPlayer() == p1
						|| board.getOpponentPlayer() == p2);
		assertEquals("New active player should start with Main Phase 1.", board
				.getActivePlayer().getField().getPhase(), Phase.MAIN1);
		assertEquals("New active Player draws one more card.",
				BeforeOpponentHandSize + 1, board.getActivePlayer().getField()
						.getHand().size());

	}

	@Test(timeout = 1000)
	public void testEndTurnInMain2() throws Exception {

		Board board = new Board();
		Player p1 = new Player("Yugi");
		Player p2 = new Player("Kaiba");
		board.startGame(p1, p2);

		Player Before = board.getActivePlayer();

		Before.getField().setPhase(Phase.MAIN2);

		int BeforeOpponentHandSize = board.getOpponentPlayer().getField()
				.getHand().size();

		Before.endTurn();

		Player After = board.getActivePlayer();

		assertTrue("Players should not be the same after ending the turn.",
				Before != After);
		assertTrue("Should not create new players when switching turns.",
				board.getActivePlayer() == p1 || board.getActivePlayer() == p2);
		assertTrue("Should not create new players when switching turns.",
				board.getOpponentPlayer() == p1
						|| board.getOpponentPlayer() == p2);
		assertEquals("New active player should start with Main Phase 1.", board
				.getActivePlayer().getField().getPhase(), Phase.MAIN1);
		assertEquals("New active Player draws one more card.",
				BeforeOpponentHandSize + 1, board.getActivePlayer().getField()
						.getHand().size());

	}

	@Test(timeout = 1000)
	public void testExactNumberOfSacrificesLevel6() throws Exception {

		Board board = new Board();
		Player p1 = new Player("Yugi");
		Player p2 = new Player("Kaiba");
		board.startGame(p1, p2);

		MonsterCard blueEyes = new MonsterCard("Blue Eyes", "Legendary dragon",
				6, 3000, 2500);

		MonsterCard gaia = new MonsterCard("Gaia the fierce knight",
				"a knight on his horse", 7, 2300, 2100);
		MonsterCard vorseRaider = new MonsterCard("Vorse Raider",
				"Beast Warrior", 4, 1900, 3200);

		board.getActivePlayer().getField().getHand().add(blueEyes);
		blueEyes.setLocation(Location.HAND);

		board.getActivePlayer().getField().getMonstersArea().add(vorseRaider);
		vorseRaider.setLocation(Location.FIELD);

		board.getActivePlayer().getField().getMonstersArea().add(gaia);
		gaia.setLocation(Location.FIELD);

		ArrayList<MonsterCard> monsters = new ArrayList<MonsterCard>();

		monsters.add(vorseRaider);
		monsters.add(gaia);

		board.getActivePlayer().summonMonster(blueEyes, monsters);

		assertEquals("Level 6 monsters require only 1 monster as a sacrifice.",
				0, board.getActivePlayer().getField().getGraveyard().size());

	}

	@Test(timeout = 1000)
	public void testExactNumberOfSacrificesLevel7() throws Exception {

		Board board = new Board();
		Player p1 = new Player("Yugi");
		Player p2 = new Player("Kaiba");
		board.startGame(p1, p2);

		MonsterCard blueEyes = new MonsterCard("Blue Eyes", "Legendary dragon",
				7, 3000, 2500);

		MonsterCard darkMagician = new MonsterCard("Dark Magician",
				"spell caster", 7, 2500, 2200);
		MonsterCard gaia = new MonsterCard("Gaia the fierce knight",
				"a knight on his horse", 7, 2300, 2100);
		MonsterCard vorseRaider = new MonsterCard("Vorse Raider",
				"Beast Warrior", 4, 1900, 3200);

		board.getActivePlayer().getField().getHand().add(blueEyes);
		blueEyes.setLocation(Location.HAND);

		board.getActivePlayer().getField().getMonstersArea().add(vorseRaider);
		vorseRaider.setLocation(Location.FIELD);

		board.getActivePlayer().getField().getMonstersArea().add(gaia);
		gaia.setLocation(Location.FIELD);

		board.getActivePlayer().getField().getMonstersArea().add(darkMagician);
		darkMagician.setLocation(Location.FIELD);

		ArrayList<MonsterCard> monsters = new ArrayList<MonsterCard>();
		monsters.add(vorseRaider);
		monsters.add(gaia);
		monsters.add(darkMagician);

		board.getActivePlayer().setMonster(blueEyes, monsters);

		assertEquals(
				"Level 7 monsters require only 2 monsters as a sacrifice.", 0,
				board.getActivePlayer().getField().getGraveyard().size());

	}

	@Test(timeout = 1000)
	public void testInvalidDirectAttack() throws Exception {

		Board board = new Board();
		Player p1 = new Player("Yugi");
		Player p2 = new Player("Kaiba");
		board.startGame(p1, p2);

		MonsterCard BlueEyes = new MonsterCard("Blue Eyes White Dragon",
				"Legendary Dragon", 3, 3000, 2500);
		MonsterCard vorseRaider = new MonsterCard("Vorse Raider",
				"Beast Warrior", 4, 1900, 1200);

		board.getActivePlayer().getField().getHand().add(vorseRaider);
		vorseRaider.setLocation(Location.HAND);
		board.getActivePlayer().summonMonster(vorseRaider);
		board.getActivePlayer().endTurn();

		board.getActivePlayer().getField().getHand().add(BlueEyes);
		BlueEyes.setLocation(Location.HAND);
		board.getActivePlayer().summonMonster(BlueEyes);
		board.getActivePlayer().endTurn();

		int opLP = board.getOpponentPlayer().getLifePoints();
		int lp = board.getActivePlayer().getLifePoints();

		board.getActivePlayer().endPhase();

		board.getActivePlayer().declareAttack(vorseRaider);

		assertEquals(
				"Cannot directly attack a player while he/she has monsters on the field.",
				lp, board.getActivePlayer().getLifePoints());
		assertEquals(
				"Cannot directly attack a player while he/she has monsters on the field.",
				opLP, board.getOpponentPlayer().getLifePoints());

	}

	@Test(timeout = 1000)
	public void testMonsterMethods() throws Exception {

		Class<?>[] args = new Class[1];
		args[0] = MonsterCard.class;
		Method m = Duelist.class.getDeclaredMethod("summonMonster", args);
		assertTrue("Must assign correct return type to interface methods.", m
				.getReturnType().equals(Boolean.TYPE));

		args = new Class[2];
		args[0] = MonsterCard.class;
		args[1] = ArrayList.class;
		m = Duelist.class.getDeclaredMethod("summonMonster", args);
		assertTrue("Must assign correct return type to interface methods.", m
				.getReturnType().equals(Boolean.TYPE));

		args = new Class[1];
		args[0] = MonsterCard.class;
		m = Duelist.class.getDeclaredMethod("setMonster", args);
		assertTrue("Must assign correct return type to interface methods.", m
				.getReturnType().equals(Boolean.TYPE));

		args = new Class[2];
		args[0] = MonsterCard.class;
		args[1] = ArrayList.class;
		m = Duelist.class.getDeclaredMethod("setMonster", args);
		assertTrue("Must assign correct return type to interface methods.", m
				.getReturnType().equals(Boolean.TYPE));

		args = new Class[1];
		args[0] = MonsterCard.class;
		m = Duelist.class.getDeclaredMethod("switchMonsterMode", args);
		assertTrue("Must assign correct return type to interface methods.", m
				.getReturnType().equals(Boolean.TYPE));

	}

	@Test(timeout = 1000)
	public void testMonsterSummonInWrongPhase() throws Exception {

		Board board = new Board();
		Player p1 = new Player("Yugi");
		Player p2 = new Player("Kaiba");
		board.startGame(p1, p2);

		board.getActivePlayer().endPhase();

		MonsterCard BlueEyes = new MonsterCard("Blue Eyes White Dragon",
				"Best Dragon Ever", 4, 3000, 2000);

		BlueEyes.setLocation(Location.HAND);
		board.getActivePlayer().getField().getHand().add(BlueEyes);

		board.getActivePlayer().summonMonster(BlueEyes);

		assertEquals(
				"Adding monsters in Battle phase is not allowed. The monster should not be added.",
				0, board.getActivePlayer().getField().getMonstersArea().size());

	}

	@Test(timeout = 1000)
	public void testPlayerAbstraction() throws Exception {

		assertFalse("Player class shouldn't be abstract.",
				Modifier.isAbstract(Player.class.getModifiers()));
		assertTrue("Player should implement Duelist interface.",
				Duelist.class.isAssignableFrom(Player.class));

	}

	@Test(timeout = 1000)
	public void testPlayingMonsterToFieldWithSacrificesLev4() throws Exception {

		Board board = new Board();
		Player p1 = new Player("Yugi");
		Player p2 = new Player("Kaiba");
		board.startGame(p1, p2);

		MonsterCard vorseRaider = new MonsterCard("Vorse Raider",
				"A warrior beast", 4, 1900, 1200);

		vorseRaider.setLocation(Location.FIELD);
		board.getActivePlayer().getField().getMonstersArea().add(vorseRaider);

		MonsterCard geminiElf = new MonsterCard("Gemini Elf", "The twins", 4,
				1900, 900);

		board.getActivePlayer().getField().getHand().add(geminiElf);
		geminiElf.setLocation(Location.HAND);

		ArrayList<MonsterCard> l = new ArrayList<MonsterCard>();
		l.add(vorseRaider);

		board.getActivePlayer().summonMonster(geminiElf, l);

		assertEquals("Field Should only have one monster card.", 1, board
				.getActivePlayer().getField().getMonstersArea().size());
		assertEquals("Field Should only have Vorse Raider.", vorseRaider, board
				.getActivePlayer().getField().getMonstersArea().get(0));
		assertEquals("Graveyard should have no monster.", 0, board
				.getActivePlayer().getField().getGraveyard().size());
		assertTrue(
				"The monster was not summoned, It should not be removed from Hand.",
				board.getActivePlayer().getField().getHand()
						.contains(geminiElf));

	}

	@Test(timeout = 1000)
	public void testPlayingMonsterToFieldWithSacrificesLev5() throws Exception {

		Board board = new Board();
		Player p1 = new Player("Yugi");
		Player p2 = new Player("Kaiba");
		board.startGame(p1, p2);

		MonsterCard vorseRaider = new MonsterCard("Vorse Raider",
				"A warrior beast", 4, 1900, 1200);

		vorseRaider.setLocation(Location.FIELD);
		board.getActivePlayer().getField().getMonstersArea().add(vorseRaider);

		MonsterCard curseOfDragon = new MonsterCard("Curse of dragon",
				"uses fire", 5, 2000, 1600);

		board.getActivePlayer().getField().getHand().add(curseOfDragon);
		curseOfDragon.setLocation(Location.HAND);

		ArrayList<MonsterCard> l = new ArrayList<MonsterCard>();
		l.add(vorseRaider);

		board.getActivePlayer().summonMonster(curseOfDragon, l);

		assertEquals("Field Should only have one monster card.", 1, board
				.getActivePlayer().getField().getMonstersArea().size());
		assertEquals("Field Should only have Curse Of Dragon.", curseOfDragon,
				board.getActivePlayer().getField().getMonstersArea().get(0));
		assertEquals("Graveyard should have one monster.", 1, board
				.getActivePlayer().getField().getGraveyard().size());

	}

	@Test(timeout = 1000)
	public void testPlayingMonsterToFieldWithSacrificesLev8() throws Exception {

		Board board = new Board();
		Player p1 = new Player("Yugi");
		Player p2 = new Player("Kaiba");
		board.startGame(p1, p2);
		MonsterCard vorseRaider = new MonsterCard("Vorse Raider",
				"A warrior beast", 4, 1900, 1200);
		MonsterCard geminiElf = new MonsterCard("Gemini Elf", "The Twins", 4,
				1900, 900);

		board.getActivePlayer().getField().getMonstersArea().add(geminiElf);
		geminiElf.setLocation(Location.FIELD);

		board.getActivePlayer().getField().getMonstersArea().add(vorseRaider);
		vorseRaider.setLocation(Location.FIELD);

		MonsterCard darkMagician = new MonsterCard("Dark Magician",
				"uses dark magic", 8, 2500, 2100);

		board.getActivePlayer().getField().getHand().add(darkMagician);
		darkMagician.setLocation(Location.HAND);

		ArrayList<MonsterCard> l = new ArrayList<MonsterCard>();
		l.add(vorseRaider);
		l.add(geminiElf);

		board.getActivePlayer().summonMonster(darkMagician, l);

		assertEquals("Field Should only have one monster card.", 1, board
				.getActivePlayer().getField().getMonstersArea().size());
		assertEquals("Field Should only have Dark Magician.", darkMagician,
				board.getActivePlayer().getField().getMonstersArea().get(0));
		assertEquals("Graveyard should have two monsters.", 2, board
				.getActivePlayer().getField().getGraveyard().size());

	}

	@Test(timeout = 1000)
	public void testPOGAction() throws Exception {

		Player p1 = new Player("Yugi");
		Player p2 = new Player("Kaiba");
		Board b = new Board();
		b.startGame(p1, p2);

		PotOfGreed c = new PotOfGreed("PotOfGreed", "draw");

		Card.getBoard().getActivePlayer().getField().getHand().add(c);
		c.setLocation(Location.HAND);

		int handSize = Card.getBoard().getActivePlayer().getField().getHand()
				.size();
		int DeckSize = Card.getBoard().getActivePlayer().getField().getDeck()
				.getDeck().size();

		Card.getBoard().getActivePlayer().activateSpell(c, null);

		assertEquals("Active player's hand should increase by 2.",
				handSize + 1, Card.getBoard().getActivePlayer().getField()
						.getHand().size());

		assertEquals("Active player's deck should decrease by 2.",
				DeckSize - 2, Card.getBoard().getActivePlayer().getField()
						.getDeck().getDeck().size());

		DeckSize = b.getActivePlayer().getField().getDeck().getDeck().size();

		b.getActivePlayer().addNCardsToHand(DeckSize);

		PotOfGreed pog = new PotOfGreed("PotOfGreed", "draw");

		Card.getBoard().getActivePlayer().getField().getHand().add(pog);
		pog.setLocation(Location.HAND);

		Card.getBoard().getActivePlayer().activateSpell(pog, null);

		assertEquals("Opponent player should be declared as winner.",
				b.getWinner(), b.getOpponentPlayer());

	}

	@Test(timeout = 1000)
	public void testRemoveMonsterNotInMonstersArea() throws Exception {

		Board board = new Board();
		Player p1 = new Player("Yugi");
		Player p2 = new Player("Kaiba");
		board.startGame(p1, p2);

		MonsterCard m = new MonsterCard("Vorse Raider", "A warrior beast", 4,
				1900, 1200);

		board.getActivePlayer().getField().removeMonsterToGraveyard(m);

		assertEquals(
				"Should not be able to remove a monster to the graveyard if it is not in the monsters area.",
				0, board.getActivePlayer().getField().getGraveyard().size());

		board.getActivePlayer().getField().getHand().add(m);
		m.setLocation(Location.HAND);

		board.getActivePlayer().getField().removeMonsterToGraveyard(m);

		assertEquals(
				"Should not be able to remove a monster to the graveyard if it is not in the monsters area.",
				0, board.getActivePlayer().getField().getGraveyard().size());

		board.getActivePlayer().setMonster(m);

		assertEquals("Monsters Area should have the added monster card.", 1,
				board.getActivePlayer().getField().getMonstersArea().size());

		board.getActivePlayer().getField().removeMonsterToGraveyard(m);

		assertEquals(
				"Monsters Area should be empty after removing the Monster.", 0,
				board.getActivePlayer().getField().getMonstersArea().size());

		assertEquals("Graveyard should have only one Monster.", 1, board
				.getActivePlayer().getField().getGraveyard().size());

		assertEquals("Graveyard should have only the removed Monster.", m,
				board.getActivePlayer().getField().getGraveyard().get(0));

	}

	@Test(timeout = 1000)
	public void testRemoveSpellToGraveyard() throws Exception {

		Board board = new Board();
		Player p1 = new Player("Yugi");
		Player p2 = new Player("Kaiba");
		board.startGame(p1, p2);

		PotOfGreed pog = new PotOfGreed("Pot Of Greed", "Draw 2 cards");

		board.getActivePlayer().getField().removeSpellToGraveyard(pog);

		assertEquals(
				"The spell should be in the spell area to be sent to the graveyard.",
				0, board.getActivePlayer().getField().getGraveyard().size());

		board.getActivePlayer().getField().getHand().add(pog);
		pog.setLocation(Location.HAND);

		board.getActivePlayer().getField().removeSpellToGraveyard(pog);

		assertEquals(
				"The spell should be in the spell area to be sent to the graveyard.",
				0, board.getActivePlayer().getField().getGraveyard().size());

		board.getActivePlayer().setSpell(pog);

		assertEquals("Spell Area should have the added spell card.", 1, board
				.getActivePlayer().getField().getSpellArea().size());

		board.getActivePlayer().getField().removeSpellToGraveyard(pog);

		assertEquals("Spell Area should be empty after removing the spell.", 0,
				board.getActivePlayer().getField().getSpellArea().size());

		assertEquals("Graveyard should have only one spell.", 1, board
				.getActivePlayer().getField().getGraveyard().size());

		assertEquals("Graveyard should have only the removed spell.", pog,
				board.getActivePlayer().getField().getGraveyard().get(0));

	}

	@Test(timeout = 1000)
	public void testSetMode() throws Exception {

		Board board = new Board();
		Player p1 = new Player("Yugi");
		Player p2 = new Player("Kaiba");
		board.startGame(p1, p2);

		MonsterCard vorseRaider = new MonsterCard("Vorse Raider",
				"A warrior beast", 4, 1900, 1200);

		vorseRaider.setLocation(Location.HAND);
		board.getActivePlayer().getField().getHand().add(vorseRaider);

		board.getActivePlayer().setMonster(vorseRaider);

		assertTrue(
				"Active player should be able to set his/her Monsters.",
				board.getActivePlayer().getField().getMonstersArea().size() != 0);

		MonsterCard m = board.getActivePlayer().getField().getMonstersArea()
				.get(0);
		assertEquals("Field should contain only one monster.", board
				.getActivePlayer().getField().getMonstersArea().size(), 1);
		assertEquals("Field should contain only the played monster.", m,
				vorseRaider);
		assertTrue("Monster should be hidden if in defense mode.", m.isHidden());
		assertEquals("Monster should be in defense mode.", Mode.DEFENSE,
				m.getMode());
		assertEquals("Monster should be in attack mode.", Location.FIELD,
				m.getLocation());

	}

	@Test(timeout = 1000)
	public void testSpellCardActionAbstraction() throws Exception {

		Class<?>[] args = new Class[1];
		args[0] = MonsterCard.class;
		Method m = SpellCard.class.getMethod("action", args);

		assertTrue("action method should be abstract.",
				Modifier.isAbstract(m.getModifiers()));

	}

	@Test(timeout = 1000)
	public void testSummoningInFullField() throws Exception {

		Board board = new Board();
		Player p1 = new Player("Yugi");
		Player p2 = new Player("Kaiba");
		board.startGame(p1, p2);

		MonsterCard vorseRaider1 = new MonsterCard("Vorse Raider",
				"A warrior beast", 4, 1900, 1200);
		MonsterCard vorseRaider2 = new MonsterCard("Vorse Raider",
				"A warrior beast", 4, 1900, 1200);
		MonsterCard vorseRaider3 = new MonsterCard("Vorse Raider",
				"A warrior beast", 4, 1900, 1200);
		MonsterCard vorseRaider4 = new MonsterCard("Vorse Raider",
				"A warrior beast", 4, 1900, 1200);
		MonsterCard vorseRaider5 = new MonsterCard("Vorse Raider",
				"A warrior beast", 4, 1900, 1200);
		MonsterCard geminiElf = new MonsterCard("Gemini Elf", "the twins", 4,
				1900, 900);

		board.getActivePlayer().getField().getMonstersArea().add(vorseRaider1);
		board.getActivePlayer().getField().getMonstersArea().add(vorseRaider2);
		board.getActivePlayer().getField().getMonstersArea().add(vorseRaider3);
		board.getActivePlayer().getField().getMonstersArea().add(vorseRaider4);
		board.getActivePlayer().getField().getMonstersArea().add(vorseRaider5);

		board.getActivePlayer().getField().getHand().add(geminiElf);
		geminiElf.setLocation(Location.HAND);

		board.getActivePlayer().summonMonster(geminiElf);

		assertEquals("Monsters Area should contain 5 monsters maximum.", 5,
				board.getActivePlayer().getField().getMonstersArea().size());
		assertFalse(
				"Should NOT be able to add monster to the field now.",
				board.getActivePlayer().getField().getMonstersArea()
						.contains(geminiElf));

		board.getActivePlayer().getField().getMonstersArea()
				.remove(vorseRaider1);

		board.getActivePlayer().summonMonster(geminiElf);

		assertEquals("Monsters Area should contain 5 monsters.", 5, board
				.getActivePlayer().getField().getMonstersArea().size());
		assertTrue(
				"Should be able to add the monster to the field now.",
				board.getActivePlayer().getField().getMonstersArea()
						.contains(geminiElf));

	}

	@Test(timeout = 1000)
	public void testSummoningTwicePerTurn() throws Exception {

		Board board = new Board();
		Player p1 = new Player("Yugi");
		Player p2 = new Player("Kaiba");
		board.startGame(p1, p2);

		MonsterCard vorseRaider = new MonsterCard("Vorse Raider",
				"A warrior beast", 4, 1900, 1200);
		MonsterCard geminiElf = new MonsterCard("Gemini Elf", "the twins", 4,
				1900, 900);

		board.getActivePlayer().getField().getHand().add(vorseRaider);
		vorseRaider.setLocation(Location.HAND);

		board.getActivePlayer().getField().getHand().add(geminiElf);
		geminiElf.setLocation(Location.HAND);

		board.getActivePlayer().summonMonster(vorseRaider);

		assertEquals(
				"Active player should be able to summon his/her Monsters.", 1,
				board.getActivePlayer().getField().getMonstersArea().size());

		board.getActivePlayer().summonMonster(geminiElf);

		assertEquals(
				"Should not be able to add more than one monster per turn.", 1,
				board.getActivePlayer().getField().getMonstersArea().size());

	}

	@Test(timeout = 1000)
	public void testSwitchingModeInWrongPhase() throws Exception {

		Board board = new Board();
		Player p1 = new Player("Yugi");
		Player p2 = new Player("Kaiba");
		board.startGame(p1, p2);

		MonsterCard m = new MonsterCard("Vorse Raider", "A warrior beast", 4,
				1900, 1200);

		board.getActivePlayer().getField().getHand().add(m);
		m.setLocation(Location.HAND);

		board.getActivePlayer().summonMonster(m);

		board.getActivePlayer().endPhase();

		Mode before = m.getMode();
		board.getActivePlayer().switchMonsterMode(m);
		Mode after = m.getMode();

		assertEquals(
				"Should not be able to switch monster mode in battle phase.",
				before, after);

	}

	@Test(timeout = 1000)
	public void testSwitchingModeInWrongTurn() throws Exception {

		Board board = new Board();
		Player p1 = new Player("Yugi");
		Player p2 = new Player("Kaiba");
		board.startGame(p1, p2);

		MonsterCard m = new MonsterCard("Vorse Raider", "A warrior beast", 4,
				1900, 1200);

		board.getOpponentPlayer().getField().getMonstersArea().add(m);

		Mode before = m.getMode();
		board.getOpponentPlayer().switchMonsterMode(m);
		Mode after = m.getMode();

		assertEquals(
				"Opponent player should not be able to switch his monster's mode.",
				before, after);
	}

	@Test(timeout = 1000)
	public void testSwitchingModeTwice() throws Exception {

		Board board = new Board();
		Player p1 = new Player("Yugi");
		Player p2 = new Player("Kaiba");
		board.startGame(p1, p2);

		MonsterCard m = new MonsterCard("Vorse Raider", "A warrior beast", 4,
				1900, 1200);

		board.getActivePlayer().getField().getHand().add(m);
		m.setLocation(Location.HAND);

		Mode before = m.getMode();
		board.getActivePlayer().switchMonsterMode(m);
		Mode after = m.getMode();

		assertEquals(
				"Should not be able to switch monster mode while not in monster area.",
				before, after);

		board.getActivePlayer().setMonster(m);

		before = m.getMode();
		board.getActivePlayer().switchMonsterMode(m);
		after = m.getMode();

		assertTrue("Should be able to switch monster mode.", before != after);

		before = m.getMode();
		board.getActivePlayer().switchMonsterMode(m);
		after = m.getMode();

		assertEquals(
				"Should not be able to switch monster mode twice in the same turn.",
				before, after);

	}

}
