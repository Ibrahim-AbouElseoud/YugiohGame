package eg.edu.guc.yugioh.tests;

import static org.junit.Assert.*;
import eg.edu.guc.yugioh.board.*;
import eg.edu.guc.yugioh.board.player.*;
import eg.edu.guc.yugioh.cards.*;
import eg.edu.guc.yugioh.cards.spells.*;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;

import org.junit.Test;

@SuppressWarnings("unused")
public class PublicTest_17329 {

	@Test(timeout = 1000)
	public void testStartGame() throws Exception {

		Board board = new Board();
		Player p1 = new Player("Yugi");
		Player p2 = new Player("Kaiba");
		board.startGame(p1, p2);

		assertEquals("Active player should hold 6 cards.", 6, board
				.getActivePlayer().getField().getHand().size());
		assertEquals("Opponent player should hold 5 cards.", 5, board
				.getOpponentPlayer().getField().getHand().size());
		p1.getField().getDeck();
		System.out.println(Deck.getSpells().size());
		
	
		assertEquals("Active player's cards should be drawn from the deck.",
				14, board.getActivePlayer().getField().getDeck().getDeck()
						.size());
		assertEquals("Opponent player's cards should be drawn from the deck.",
				15, board.getOpponentPlayer().getField().getDeck().getDeck()
						.size());

	}

	@Test(timeout = 1000)
	public void testNextPlayer() throws Exception {

		Board board = new Board();
		Player p1 = new Player("Yugi");
		Player p2 = new Player("Kaiba");
		board.startGame(p1, p2);

		Player Before = board.getActivePlayer();
		int BeforOpponentHandSize = board.getOpponentPlayer().getField()
				.getHand().size();

		board.nextPlayer();

		Player After = board.getActivePlayer();

		assertNotEquals("Players' roles should be switched.", Before, After);
		assertEquals("Opponent should draw one more card.",
				BeforOpponentHandSize + 1, board.getActivePlayer().getField()
						.getHand().size());

	}

	@Test(timeout = 1000)
	public void testAddMonsterToField() throws Exception {

		Board board = new Board();
		Player p1 = new Player("Yugi");
		Player p2 = new Player("Kaiba");
		board.startGame(p1, p2);

		MonsterCard m1 = new MonsterCard("Vorse Raider", "A warrior beast", 4,
				1900, 1200);
		MonsterCard m2 = new MonsterCard("Vorse Raider", "A warrior beast", 4,
				1900, 1200);
		MonsterCard m3 = new MonsterCard("Vorse Raider", "A warrior beast", 4,
				1900, 1200);
		MonsterCard m4 = new MonsterCard("Vorse Raider", "A warrior beast", 4,
				1900, 1200);
		MonsterCard m5 = new MonsterCard("Vorse Raider", "A warrior beast", 4,
				1900, 1200);
		MonsterCard m6 = new MonsterCard("Vorse Raider", "A warrior beast", 4,
				1900, 1200);

		board.getActivePlayer().getField()
				.addMonsterToField(m1, Mode.ATTACK, false);

		assertEquals(
				"Active player's monster area should have only one monster.",
				1, board.getActivePlayer().getField().getMonstersArea().size());
		assertEquals(
				"addMonsterToField should add the monsterCard given in the passed parameters, Not a clone of it.",
				m1, board.getActivePlayer().getField().getMonstersArea().get(0));
		assertEquals("The added monster's location should be updated.",
				Location.FIELD, m1.getLocation());
		assertEquals("The added monster's mode should match passed parameter.",
				Mode.ATTACK, m1.getMode());
		assertEquals(
				"The added monster's isHidden should match passed parameter.",
				false, m1.isHidden());

		board.getActivePlayer().getField()
				.addMonsterToField(m2, Mode.ATTACK, false);
		board.getActivePlayer().getField()
				.addMonsterToField(m3, Mode.ATTACK, false);
		board.getActivePlayer().getField()
				.addMonsterToField(m4, Mode.ATTACK, false);
		board.getActivePlayer().getField()
				.addMonsterToField(m5, Mode.ATTACK, false);
		board.getActivePlayer().getField()
				.addMonsterToField(m6, Mode.ATTACK, false);

		assertEquals(
				"Player's monster area should not contain more than five cards.",
				5, board.getActivePlayer().getField().getMonstersArea().size());

	}

	@Test(timeout = 1000)
	public void testRemoveMonsterToGraveyard() throws Exception {

		Board board = new Board();
		Player p1 = new Player("Yugi");
		Player p2 = new Player("Kaiba");
		board.startGame(p1, p2);

		MonsterCard m = new MonsterCard("Vorse Raider", "A warrior beast", 4,
				1900, 1200);

		board.getActivePlayer().getField()
				.addMonsterToField(m, Mode.ATTACK, false);

		board.getActivePlayer().getField().removeMonsterToGraveyard(m);

		assertEquals(
				"Monster area should be empty after removing the monster.", 0,
				board.getActivePlayer().getField().getMonstersArea().size());
		assertEquals("Graveyard should have only one monster.", 1, board
				.getActivePlayer().getField().getGraveyard().size());
		assertEquals("Graveyard should have the removed monster.", m, board
				.getActivePlayer().getField().getGraveyard().get(0));
		assertEquals(
				"The location of the removed monster should be the graveyard.",
				Location.GRAVEYARD, m.getLocation());

	}

	@Test(timeout = 1000)
	public void testRemoveSpellsToGraveyard() throws Exception {

		Board board = new Board();
		Player p1 = new Player("Yugi");
		Player p2 = new Player("Kaiba");
		board.startGame(p1, p2);

		PotOfGreed pog = new PotOfGreed("Pot Of Greed", "Draw 2 cards");
		PotOfGreed pog2 = new PotOfGreed("Pot Of Greed", "Draw 2 cards");

		board.getActivePlayer().getField().addSpellToField(pog, null, true);
		board.getActivePlayer().getField().addSpellToField(pog2, null, true);

		ArrayList<SpellCard> spells = new ArrayList<SpellCard>();
		spells.add(pog);
		spells.add(pog2);

		board.getActivePlayer().getField().removeSpellToGraveyard(spells);

		assertEquals(
				"Spell Area should be empty after removing all the spells.", 0,
				board.getActivePlayer().getField().getSpellArea().size());
		assertEquals("Graveyard should have the removed spells.", 2, board
				.getActivePlayer().getField().getGraveyard().size());

		assertTrue("The removed spells should be sent to the graveyard.",
				Card.getBoard().getActivePlayer().getField().getGraveyard()
						.get(0) == pog
						|| Card.getBoard().getActivePlayer().getField()
								.getGraveyard().get(1) == pog);
		assertTrue("The removed spells should be sent to the graveyard.",
				Card.getBoard().getActivePlayer().getField().getGraveyard()
						.get(0) == pog2
						|| Card.getBoard().getActivePlayer().getField()
								.getGraveyard().get(1) == pog2);

		assertEquals(
				"The location of the removed spells should be the graveyard.",
				Location.GRAVEYARD, pog.getLocation());
		assertEquals(
				"The location of the removed spells should be the graveyard.",
				Location.GRAVEYARD, pog2.getLocation());

	}

	@Test(timeout = 1000)
	public void testAddCardToHand() throws Exception {

		Player p1 = new Player("Yugi");

		int handNum = p1.getField().getHand().size();
		int deckNum = p1.getField().getDeck().getDeck().size();

		p1.getField().addCardToHand();

		assertEquals("Number of cards in hand should increase by 1.",
				handNum + 1, p1.getField().getHand().size());
		assertEquals("Number of cards in deck should decrease by 1.",
				deckNum - 1, p1.getField().getDeck().getDeck().size());
		ArrayList<Card> hand = p1.getField().getHand();
		for (int i = 0; i < hand.size(); i++)
			assertEquals("The location of the drawn cards should be the hand.",
					Location.HAND, hand.get(i).getLocation());

	}

	@Test(timeout = 1000)
	public void testAddSpellToFieldHiddenThenActivate() throws Exception {

		Player p1 = new Player("Yugi");
		Player p2 = new Player("Kaiba");
		Board board = new Board();
		board.startGame(p1, p2);

		PotOfGreed pog = new PotOfGreed("Pot Of Greed", "Draw");
		Raigeki r = new Raigeki("Raigeki", "destroy");

		int activeHand = board.getActivePlayer().getField().getHand().size();

		board.getActivePlayer().getField().addSpellToField(pog, null, true);
		board.getActivePlayer().getField().addSpellToField(r, null, true);

		assertTrue("The added hidden spells should not be activated.",
				activeHand == board.getActivePlayer().getField().getHand()
						.size());
		assertEquals("Spell area should contain the added spell cards.", 2,
				board.getActivePlayer().getField().getSpellArea().size());
		assertEquals("Graveyard should be empty.", 0, board.getActivePlayer()
				.getField().getGraveyard().size());
		assertEquals("The location of the added spell should be the field.",
				Location.FIELD, pog.getLocation());
		assertEquals("The location of the added spell should be the field.",
				Location.FIELD, r.getLocation());

		MonsterCard BlueEyes = new MonsterCard("Blue Eyes", "legendary", 8,
				3000, 2500);

		board.getOpponentPlayer().getField()
				.addMonsterToField(BlueEyes, Mode.ATTACK, false);

		board.getActivePlayer().getField().activateSetSpell(r, null);

		assertEquals("Spell card Raigeki should be activated.", 0, board
				.getOpponentPlayer().getField().getMonstersArea().size());

		assertEquals("Spell card Raigeki should be activated.", 1, board
				.getOpponentPlayer().getField().getGraveyard().size());

		assertEquals("Activated spell cards only should go to Graveyard.", 1,
				board.getActivePlayer().getField().getGraveyard().size());
		assertEquals(
				"The location of the activated spell should be the graveyard.",
				Location.GRAVEYARD, r.getLocation());
		assertEquals(
				"Spell cards that were not activated shouldn't be remove from the spell area.",
				1, board.getActivePlayer().getField().getSpellArea().size());
		assertEquals(
				"The location of the unactivated spell should not change.",
				Location.FIELD, pog.getLocation());

	}

	@Test(timeout = 1000)
	public void testAddSpellToFieldVisible() throws Exception {

		Player p1 = new Player("Yugi");
		Player p2 = new Player("Kaiba");
		Board board = new Board();
		board.startGame(p1, p2);

		MonsterCard BlueEyes = new MonsterCard("Blue Eyes", "legendary", 8,
				3000, 2500);

		board.getOpponentPlayer().getField()
				.addMonsterToField(BlueEyes, Mode.ATTACK, false);

		Raigeki r = new Raigeki("Raigeki", "destroy");

		board.getActivePlayer().getField().addSpellToField(r, null, false);

		assertEquals(
				"The added Raigeki card is visible. It should be activated.",
				0, board.getOpponentPlayer().getField().getMonstersArea()
						.size());

		assertEquals(
				"The added Raigeki card is visible. It should be activated.",
				1, board.getOpponentPlayer().getField().getGraveyard().size());

		assertEquals("Activated spell card should go to Graveyard.", 1, board
				.getActivePlayer().getField().getGraveyard().size());
		assertEquals(
				"Activated spell card should be removed from the spell area.",
				0, board.getActivePlayer().getField().getSpellArea().size());
		assertEquals(
				"The location of the added spell should be the graveyard.",
				Location.GRAVEYARD, r.getLocation());

	}

	@Test(timeout = 1000)
	public void testDrawNCards() throws Exception {

		Player p1 = new Player("Yugi");
		int d1 = p1.getField().getDeck().getDeck().size();

		ArrayList<Card> d = p1.getField().getDeck().drawNCards(7);

		assertEquals("Should return 7 cards from deck.", 7, d.size());
		assertEquals("Should remove 7 cards from deck.", d1 - 7, p1.getField()
				.getDeck().getDeck().size());
		for (int i = 0; i < d.size(); i++)
			assertEquals(
					"The location of the cards in the deck should be DECK.",
					Location.DECK, d.get(i).getLocation());

	}

	@Test(timeout = 1000)
	public void testFieldConstructor() throws Exception {

		Board board = new Board();
		Player p1 = new Player("Yugi");
		Player p2 = new Player("Kaiba");
		board.startGame(p1, p2);

		int player1Field = p1.getField().getMonstersArea().size()
				+ p1.getField().getSpellArea().size()
				+ p1.getField().getGraveyard().size();

		int player2Field = p2.getField().getMonstersArea().size()
				+ p2.getField().getSpellArea().size()
				+ p2.getField().getGraveyard().size();

		assertEquals("The field should have no cards initially.", 0,
				player1Field + player2Field);
		assertNotNull("Field should initiallize the deck variable.", p1
				.getField().getDeck());
		assertNotNull("Field should initiallize the deck variable.", p2
				.getField().getDeck());

	}

	/*@Test(timeout = 1000)
	public void testSpellCardConstructor() throws Exception {

		SpellCard spell = new SpellCard("Dark Hole",
				"Destroy Monsters on field...");

		assertEquals("Card name should be as the passed parameter.",
				"Dark Hole", spell.getName());
		assertEquals("Card description should be as the passed parameter.",
				"Destroy Monsters on field...", spell.getDescription());
		assertEquals("Cards are hidden by default.", true, spell.isHidden());

	}*/

	@org.junit.Test(timeout = 1000)
	public void testMonsterCardConstructor() throws Exception {

		MonsterCard monster = new MonsterCard("Ryuk", "Bored Shinigami", 1,
				2000, 3000);

		assertEquals("Monster name should match passed parameter.", "Ryuk",
				monster.getName());
		assertEquals("Monster description should match passed parameter.",
				"Bored Shinigami", monster.getDescription());
		assertEquals("Cards are hidden by default.", true, monster.isHidden());
		assertEquals("Monster level should match passed parameter.", 1,
				monster.getLevel());
		assertEquals("Monster attackPoints should match passed parameter.",
				2000, monster.getAttackPoints());
		assertEquals("Monster defensePoints should match passed parameter.",
				3000, monster.getDefensePoints());
		assertEquals("Monsters are initially in defense mode.", Mode.DEFENSE,
				monster.getMode());

	}

	@org.junit.Test(timeout = 1000)
	public void testPlayerConstructor() throws Exception {

		Player player = new Player("Yugi");

		assertEquals("Player name should match passed parameter.", "Yugi",
				player.getName());
		assertEquals(
				"lifepoint should match the default life points of a player.",
				8000, player.getLifePoints());
		assertTrue("Should initialize the field attribute.",
				player.getField() != null);

	}

	@Test(timeout = 1000)
	public void testEnumMode() throws Exception {

		assertTrue("Mode should be an enum.", Mode.class.isEnum());
		assertNotNull("Mode can be ATTACK.", Mode.valueOf("ATTACK"));
		assertNotNull("Mode can be DEFENSE.", Mode.valueOf("DEFENSE"));

	}

	@org.junit.Test(timeout = 1000)
	public void testMonsterCardInheritance() throws Exception {

		assertEquals("MonsterCard should be a subclass of Card.", Card.class,
				MonsterCard.class.getSuperclass());

	}

	@org.junit.Test(timeout = 1000)
	public void testSpellCardInheritance() throws Exception {

		assertEquals("SpellCard should be a subclass of Card.", Card.class,
				SpellCard.class.getSuperclass());

	}

	@Test(timeout = 1000)
	public void testHSInheritance() throws Exception {

		assertEquals(
				"All spell cards should be subclasses of the \"appropriate\" superclass (the SpellCard Class or any other spell).",
				HarpieFeatherDuster.class, HeavyStorm.class.getSuperclass());

	}

	@Test(timeout = 1000)
	public void testCDInheritance() throws Exception {

		assertEquals(
				"All spell cards should be subclasses of the \"appropriate\" superclass (the SpellCard Class or any other spell).",
				SpellCard.class, CardDestruction.class.getSuperclass());

	}

	@Test(timeout = 1000)
	public void testCOHInheritance() throws Exception {

		assertEquals(
				"All spell cards should be subclasses of the \"appropriate\" superclass (the SpellCard Class or any other spell).",
				SpellCard.class, ChangeOfHeart.class.getSuperclass());

	}

	@Test(timeout = 1000)
	public void testGDInheritance() throws Exception {

		assertEquals(
				"All spell cards should be subclasses of the \"appropriate\" superclass (the SpellCard Class or any other spell).",
				SpellCard.class, GracefulDice.class.getSuperclass());

	}

	@Test(timeout = 1000)
	public void testHSAction() throws Exception {

		Player p1 = new Player("Yugi");
		Player p2 = new Player("Kaiba");
		Board b = new Board();
		b.startGame(p1, p2);

		CardDestruction c1 = new CardDestruction("CardDestruction", "destruct");
		ChangeOfHeart c2 = new ChangeOfHeart("ChangeOfHeart", "coh");
		DarkHole c3 = new DarkHole("DarkHole", "DH");
		Raigeki c4 = new Raigeki("Raigeki", "R");
		MagePower c5 = new MagePower("Mage Power", "MP");

		Card.getBoard().getOpponentPlayer().getField()
				.addSpellToField(c1, null, true);
		Card.getBoard().getOpponentPlayer().getField()
				.addSpellToField(c2, null, true);

		Card.getBoard().getActivePlayer().getField()
				.addSpellToField(c3, null, true);
		Card.getBoard().getActivePlayer().getField()
				.addSpellToField(c4, null, true);
		Card.getBoard().getActivePlayer().getField()
				.addSpellToField(c5, null, true);

		HeavyStorm c6 = new HeavyStorm("HeavyStorm", "destroy spells");
		c6.action(null);

		assertEquals("Graveyard should have 2 cards.", 2, Card.getBoard()
				.getOpponentPlayer().getField().getGraveyard().size());
		assertEquals("Graveyard should have 3 card.", 3, Card.getBoard()
				.getActivePlayer().getField().getGraveyard().size());

		assertEquals("Spell Area should have no cards.", 0, Card.getBoard()
				.getOpponentPlayer().getField().getSpellArea().size());
		assertEquals("Spell Area should have no cards.", 0, Card.getBoard()
				.getActivePlayer().getField().getSpellArea().size());

		assertTrue("Graveyard should have the destroyed cards.", Card
				.getBoard().getOpponentPlayer().getField().getGraveyard()
				.get(0) == c1
				|| Card.getBoard().getOpponentPlayer().getField()
						.getGraveyard().get(1) == c1);
		assertTrue("Graveyard should have the destroyed cards.", Card
				.getBoard().getOpponentPlayer().getField().getGraveyard()
				.get(0) == c2
				|| Card.getBoard().getOpponentPlayer().getField()
						.getGraveyard().get(1) == c2);

		assertTrue(
				"Graveyard should have the destroyed cards.",
				(Card.getBoard().getActivePlayer().getField().getGraveyard()
						.get(0) == c3
						|| Card.getBoard().getActivePlayer().getField()
								.getGraveyard().get(1) == c3 || Card.getBoard()
						.getActivePlayer().getField().getGraveyard().get(2) == c3));

		assertTrue(
				"Graveyard should have the destroyed cards.",
				(Card.getBoard().getActivePlayer().getField().getGraveyard()
						.get(0) == c4
						|| Card.getBoard().getActivePlayer().getField()
								.getGraveyard().get(1) == c4 || Card.getBoard()
						.getActivePlayer().getField().getGraveyard().get(2) == c4));

		assertTrue(
				"Graveyard should have the destroyed cards.",
				(Card.getBoard().getActivePlayer().getField().getGraveyard()
						.get(0) == c5
						|| Card.getBoard().getActivePlayer().getField()
								.getGraveyard().get(1) == c5 || Card.getBoard()
						.getActivePlayer().getField().getGraveyard().get(2) == c5));

		assertEquals(
				"The location of the destroyed cards should be the graveyard.",
				Location.GRAVEYARD, c1.getLocation());
		assertEquals(
				"The location of the destroyed cards should be the graveyard.",
				Location.GRAVEYARD, c2.getLocation());
		assertEquals(
				"The location of the destroyed cards should be the graveyard.",
				Location.GRAVEYARD, c3.getLocation());
		assertEquals(
				"The location of the destroyed cards should be the graveyard.",
				Location.GRAVEYARD, c4.getLocation());
		assertEquals(
				"The location of the destroyed cards should be the graveyard.",
				Location.GRAVEYARD, c5.getLocation());

	}

	@Test(timeout = 1000)
	public void testMPAction() throws Exception {

		Player p1 = new Player("Yugi");
		Player p2 = new Player("Kaiba");
		Board b = new Board();
		b.startGame(p1, p2);

		MonsterCard vorseRaider = new MonsterCard("Vorse Raider",
				"Beast Warrior", 4, 1900, 1200);
		MonsterCard blueEyes = new MonsterCard("Blue Eyes", "Legendary dragon",
				8, 3000, 2500);

		CardDestruction c1 = new CardDestruction("CardDestruction", "destruct");
		ChangeOfHeart c2 = new ChangeOfHeart("ChangeOfHeart", "coh");
		MagePower c3 = new MagePower("MagePower", "MP");

		Card.getBoard().getActivePlayer().getField()
				.addMonsterToField(blueEyes, Mode.ATTACK, false);

		Card.getBoard().getActivePlayer().getField()
				.addSpellToField(c1, null, true);
		Card.getBoard().getActivePlayer().getField()
				.addSpellToField(c3, null, true);

		Card.getBoard().getOpponentPlayer().getField()
				.addMonsterToField(vorseRaider, Mode.ATTACK, false);

		Card.getBoard().getOpponentPlayer().getField()
				.addSpellToField(c2, null, true);

		c3.action(blueEyes);

		assertEquals(
				"Monster attack should increase by number of spell cards on field.",
				4000, blueEyes.getAttackPoints());
		assertEquals(
				"Monster defense should increase by number of spell cards on field.",
				3500, blueEyes.getDefensePoints());

		assertEquals("Opponent's monsters should not be affected.", 1200,
				vorseRaider.getDefensePoints());
		assertEquals("Opponent's monsters should not be affected.", 1900,
				vorseRaider.getAttackPoints());

	}

	@Test(timeout = 1000)
	public void testRAction() throws Exception {

		Player p1 = new Player("Yugi");
		Player p2 = new Player("Kaiba");
		Board b = new Board();
		b.startGame(p1, p2);

		MonsterCard blueEyes = new MonsterCard("Blue Eyes", "Legendary dragon",
				8, 3000, 2500);
		MonsterCard vorseRaider = new MonsterCard("Vorse Raider",
				"Beast Warrior", 4, 1900, 1200);
		MonsterCard gaia = new MonsterCard("Gaia", "Fierce Knoght", 4, 1900,
				1200);

		Card.getBoard().getActivePlayer().getField()
				.addMonsterToField(gaia, gaia.getMode(), gaia.isHidden());

		Card.getBoard()
				.getOpponentPlayer()
				.getField()
				.addMonsterToField(blueEyes, blueEyes.getMode(),
						blueEyes.isHidden());
		Card.getBoard()
				.getOpponentPlayer()
				.getField()
				.addMonsterToField(vorseRaider, vorseRaider.getMode(),
						vorseRaider.isHidden());

		Raigeki c = new Raigeki("Raigeki", "destroy");

		c.action(null);

		assertEquals("Graveyard should have all monster cards.", 2, Card
				.getBoard().getOpponentPlayer().getField().getGraveyard()
				.size());
		assertEquals("Monster area should have no cards.", 0, Card.getBoard()
				.getOpponentPlayer().getField().getMonstersArea().size());

		assertTrue("Graveyard should have the destroyed cards.", Card
				.getBoard().getOpponentPlayer().getField().getGraveyard()
				.get(0) == blueEyes
				|| Card.getBoard().getOpponentPlayer().getField()
						.getGraveyard().get(1) == blueEyes);
		assertTrue("Graveyard should have the destroyed cards.", Card
				.getBoard().getOpponentPlayer().getField().getGraveyard()
				.get(0) == vorseRaider
				|| Card.getBoard().getOpponentPlayer().getField()
						.getGraveyard().get(1) == vorseRaider);

		assertEquals(
				"The location of the destroyed cards should be the graveyard.",
				Location.GRAVEYARD, blueEyes.getLocation());
		assertEquals(
				"The location of the destroyed cards should be the graveyard.",
				Location.GRAVEYARD, vorseRaider.getLocation());

		assertEquals("Active player's monsters should not be affected.", 1,
				Card.getBoard().getActivePlayer().getField().getMonstersArea()
						.size());

	}

	@Test(timeout = 1000)
	public void testCOHAction() throws Exception {

		Player p1 = new Player("Yugi");
		Player p2 = new Player("Kaiba");
		Board b = new Board();
		b.startGame(p1, p2);

		ChangeOfHeart c = new ChangeOfHeart("ChangeOfHeart", "COH");
		MonsterCard BlueEyes = new MonsterCard("Blue Eyes White Dragon",
				"Legendary Dragon", 4, 3000, 2000);

		b.getOpponentPlayer().getField()
				.addMonsterToField(BlueEyes, Mode.ATTACK, false);

		c.action(BlueEyes);

		assertEquals("Opponent's monster area should have no monsters.", 0, b
				.getOpponentPlayer().getField().getMonstersArea().size());
		assertEquals("Opponent's graveyard should have no monsters.", 0, b
				.getOpponentPlayer().getField().getGraveyard().size());
		assertEquals("Active player's monster area should have one monster.",
				1, b.getActivePlayer().getField().getMonstersArea().size());
		assertTrue(
				"Active player's monster area should have the targeted Monster.",
				b.getActivePlayer().getField().getMonstersArea()
						.contains(BlueEyes));

	}

	@Test(timeout = 1000)
	public void testMRAction() throws Exception {

		Player p1 = new Player("Yugi");
		Player p2 = new Player("Kaiba");
		Board b = new Board();
		b.startGame(p1, p2);

		MonsterCard blueEyes = new MonsterCard("Blue Eyes", "Legendary dragon",
				8, 3000, 2500);
		MonsterCard vorseRaider = new MonsterCard("Vorse Raider",
				"Beast Warrior", 4, 1900, 1200);
		MonsterCard DarkMagician = new MonsterCard("Dark Magician",
				"spell caster", 7, 2500, 2200);
		MonsterCard Gaia = new MonsterCard("Gaia the fierce knight",
				"a knight on his horse", 7, 2300, 2100);

		Card.getBoard().getActivePlayer().getField().getGraveyard()
				.add(DarkMagician);
		Card.getBoard().getActivePlayer().getField().getGraveyard().add(Gaia);

		Card.getBoard().getOpponentPlayer().getField().getGraveyard()
				.add(blueEyes);
		Card.getBoard().getOpponentPlayer().getField().getGraveyard()
				.add(vorseRaider);

		MonsterReborn c = new MonsterReborn("MonsterReborn", "revive");
		c.action(null);

		assertEquals(
				"The graveyard from which the monster was revived should decrease in size.",
				1, Card.getBoard().getOpponentPlayer().getField()
						.getGraveyard().size());
		assertEquals("The other graveyard size should stay the same.", 2, Card
				.getBoard().getActivePlayer().getField().getGraveyard().size());

		assertEquals(
				"Active player's monster area should have the revived monster.",
				1, Card.getBoard().getActivePlayer().getField()
						.getMonstersArea().size());
		assertEquals(
				"Should revive the strongest monster in both graveyards on the field.",
				blueEyes, Card.getBoard().getActivePlayer().getField()
						.getMonstersArea().get(0));

		assertEquals("The location of the revived card should be the field.",
				Location.FIELD, blueEyes.getLocation());

	}

	@Test(timeout = 1000)
	public void testPOGAction() throws Exception {

		Player p1 = new Player("Yugi");
		Player p2 = new Player("Kaiba");
		Board b = new Board();
		b.startGame(p1, p2);

		int handSize = Card.getBoard().getActivePlayer().getField().getHand()
				.size();
		int DeckSize = Card.getBoard().getActivePlayer().getField().getDeck()
				.getDeck().size();

		PotOfGreed c = new PotOfGreed("PotOfGreed", "draw");
		c.action(null);

		assertEquals("Active player's hand should increase by 2.",
				handSize + 2, Card.getBoard().getActivePlayer().getField()
						.getHand().size());

		assertEquals("Active player's deck should increase by 2.",
				DeckSize - 2, Card.getBoard().getActivePlayer().getField()
						.getDeck().getDeck().size());

	}

	@Test(timeout = 1000)
	public void testBoardEncapsulation() throws Exception {

		Board board = new Board();
		Player p1 = new Player("Yugi");
		Player p2 = new Player("Kaiba");
		board.startGame(p1, p2);

		Field field = board.getClass().getDeclaredField("activePlayer");
		Field field2 = board.getClass().getDeclaredField("opponentPlayer");
		Field field3 = board.getClass().getDeclaredField("winner");

		assertEquals("Instance variables should be private.", 2,
				field.getModifiers());
		assertEquals("Instance variables should be private.", 2,
				field2.getModifiers());
		assertTrue("Instance variables should be private.",
				(2 == field3.getModifiers()) || (18 == field3.getModifiers()));

	}

	/*@Test(timeout = 1000)
	public void testCardEncapsulation() throws Exception {

		Card card = new Card("Blue eyes dragon", "Monster Card");
		Field field = card.getClass().getDeclaredField("name");
		Field field2 = card.getClass().getDeclaredField("description");
		Field field3 = card.getClass().getDeclaredField("isHidden");
		Field field4 = card.getClass().getDeclaredField("board");
		Field field5 = card.getClass().getDeclaredField("location");

		assertTrue("Instance variables should be private.",
				((field.getModifiers() == 2) || (field.getModifiers() == 18)));
		assertTrue("Instance variables should be private.",
				((field2.getModifiers() == 2) || (field2.getModifiers() == 18)));

		assertEquals("Instance variables should be private.", 2,
				field3.getModifiers());
		assertEquals("board should be private and static.", 10,
				field4.getModifiers());
		assertEquals("Instance variables should be private.", 2,
				field5.getModifiers());

	}*/

	@Test(timeout = 1000)
	public void testFieldEncapsulation() throws Exception {

		eg.edu.guc.yugioh.board.player.Field f = new eg.edu.guc.yugioh.board.player.Field();
		Field field = f.getClass().getDeclaredField("deck");
		Field field2 = f.getClass().getDeclaredField("hand");
		Field field3 = f.getClass().getDeclaredField("graveyard");
		Field field4 = f.getClass().getDeclaredField("monstersArea");
		Field field5 = f.getClass().getDeclaredField("spellArea");
		Field field6 = f.getClass().getDeclaredField("phase");

		assertTrue("Instance variables should be private.",
				((field.getModifiers() == 2) || (field.getModifiers() == 18)));
		assertTrue("Instance variables should be private.",
				((field2.getModifiers() == 2) || (field2.getModifiers() == 18)));
		assertTrue("Instance variables should be private.",
				((field3.getModifiers() == 2) || (field3.getModifiers() == 18)));
		assertTrue("Instance variables should be private.",
				((field4.getModifiers() == 2) || (field4.getModifiers() == 18)));
		assertTrue("Instance variables should be private.",
				((field5.getModifiers() == 2) || (field5.getModifiers() == 18)));

		assertEquals("Instance variables should be private.", 2,
				field6.getModifiers());

	}

	@Test(timeout = 1000)
	public void testMonsterEncapsulation() throws Exception {

		MonsterCard monster = new MonsterCard("Rem", "Monster Card", 1, 2000,
				3000);
		Field field = monster.getClass().getDeclaredField("level");
		Field field2 = monster.getClass().getDeclaredField("attackPoints");
		Field field3 = monster.getClass().getDeclaredField("defensePoints");
		Field field4 = monster.getClass().getDeclaredField("mode");

		assertEquals("Instance variables should be private.", 2,
				field.getModifiers());
		assertEquals("Instance variables should be private.", 2,
				field2.getModifiers());
		assertEquals("Instance variables should be private.", 2,
				field3.getModifiers());
		assertEquals("Instance variables should be private.", 2,
				field4.getModifiers());

	}

	@Test(timeout = 1000)
	public void testPlayerEncapsulation() throws Exception {

		Player player = new Player("Yugi");
		Field field = player.getClass().getDeclaredField("name");
		Field field2 = player.getClass().getDeclaredField("lifePoints");
		Field field3 = player.getClass().getDeclaredField("field");

		assertTrue("Instance variables should be private.",
				((field.getModifiers() == 2) || (field.getModifiers() == 18)));
		assertTrue("Instance variables should be private.",
				((field3.getModifiers() == 2) || (field3.getModifiers() == 18)));
		assertEquals("Instance variables should be private.", 2,
				field2.getModifiers());

	}

	/*@org.junit.Test(timeout = 1000)
	public void testIsHidden() throws Exception {

		Card card = new Card("Ruyk", "Shinigami");
		assertEquals("All cards are hidden by default.", true, card.isHidden());

	}*/

	//@org.junit.Test(timeout = 1000)
	/*public void testCardSetHidden() throws Exception {

		Card card = new Card("Gaia", "Monster card");
		card.setHidden(false);
		assertEquals("Card visibility should match the passed parameter.",
				false, card.isHidden());

	}

	@org.junit.Test(timeout = 1000)
	public void testCardGetName() throws Exception {

		Card card = new Card("Gaia", "Monster card");
		assertEquals("Card name should match the passed parameter.", "Gaia",
				card.getName());

	}

	@org.junit.Test(timeout = 1000)
	public void testCardGetDescription() throws Exception {

		Card card = new Card("Gaia", "Monster Card");
		assertEquals("Card description should match the passed parameter.",
				"Monster Card", card.getDescription());

	}*/

	/*@org.junit.Test(timeout = 1000)
	public void testCardSetBoard() throws Exception {

		Board b = new Board();
		Card.setBoard(b);
		assertEquals("Board should be set according to the setting value.", b,
				Card.getBoard());

	}*/

	@org.junit.Test(timeout = 1000)
	public void testFieldGetPhase() throws Exception {

		eg.edu.guc.yugioh.board.player.Field f = new eg.edu.guc.yugioh.board.player.Field();
		assertTrue("The initial value of phase should be MAIN1.",
				f.getPhase() == Phase.MAIN1);

	}

	@org.junit.Test(timeout = 1000)
	public void testFieldSetPhase() throws Exception {

		eg.edu.guc.yugioh.board.player.Field f = new eg.edu.guc.yugioh.board.player.Field();

		f.setPhase(Phase.BATTLE);
		assertEquals("Phase should be set according to the setting value.",
				Phase.BATTLE, f.getPhase());

	}

	@org.junit.Test(timeout = 1000)
	public void testFieldGetDeck() throws Exception {

		eg.edu.guc.yugioh.board.player.Field f = new eg.edu.guc.yugioh.board.player.Field();
		assertTrue("Field Constructor should initialize deck.",
				f.getDeck() != null);

	}

	@org.junit.Test(timeout = 1000)
	public void testFieldGetHand() throws Exception {

		eg.edu.guc.yugioh.board.player.Field f = new eg.edu.guc.yugioh.board.player.Field();
		assertTrue("Field Constructor should initialize hand.",
				f.getHand() != null);

	}

	@org.junit.Test(timeout = 1000)
	public void testFieldGetGraveyard() throws Exception {

		eg.edu.guc.yugioh.board.player.Field f = new eg.edu.guc.yugioh.board.player.Field();
		assertTrue("Field Constructor should initialize graveyard.",
				f.getGraveyard() != null);

	}

	@org.junit.Test(timeout = 1000)
	public void testMonsterGetLevel() throws Exception {

		MonsterCard monster = new MonsterCard("Gaia", "Monster Card", 1, 2000,
				3000);
		assertEquals("The monster's level should match the passed parameter.",
				1, monster.getLevel());

	}

	@org.junit.Test(timeout = 1000)
	public void testMonsterGetAttackPoints() throws Exception {

		MonsterCard monster = new MonsterCard("Gaia", "Monster Card", 1, 2000,
				3000);
		assertEquals(
				"The monster's attack points should match the passed parameter.",
				2000, monster.getAttackPoints());

	}

	@org.junit.Test(timeout = 1000)
	public void testMonsterSetAttackPoints() throws Exception {

		MonsterCard monster = new MonsterCard("Gaia", "Monster Card", 1, 2000,
				3000);

		monster.setAttackPoints(2500);

		assertEquals(
				"The monster's attack points should match the passed parameter.",
				2500, monster.getAttackPoints());

	}

	@org.junit.Test(timeout = 1000)
	public void testMonsterGetDefensePoints() throws Exception {

		MonsterCard monster = new MonsterCard("Gaia", "Monster Card", 1, 2000,
				3000);
		assertEquals(
				"The monster's defense points should match the passed parameter.",
				3000, monster.getDefensePoints());

	}

	@org.junit.Test(timeout = 1000)
	public void testMonsterSetDefensePoints() throws Exception {

		MonsterCard monster = new MonsterCard("Gaia", "Monster Card", 1, 2000,
				3000);

		monster.setDefensePoints(2500);

		assertEquals(
				"The monster's defense points should match the passed parameter.",
				2500, monster.getDefensePoints());

	}

	@org.junit.Test(timeout = 1000)
	public void testMonsterGetMode() throws Exception {

		MonsterCard monster = new MonsterCard("Gaia", "Monster Card", 1, 2000,
				3000);
		assertEquals("All monsters are initially in DEFENSE mode.",
				Mode.DEFENSE, monster.getMode());

	}

	@org.junit.Test(timeout = 1000)
	public void testMonsterSetMode() throws Exception {

		MonsterCard monster = new MonsterCard("Gaia", "Monster Card", 1, 2000,
				3000);
		monster.setMode(Mode.ATTACK);
		assertEquals(
				"Monster mode should be set according to the setting value.",
				Mode.ATTACK, monster.getMode());

	}

	@org.junit.Test(timeout = 1000)
	public void testPlayerGetName() throws Exception {

		Player player = new Player("Yugi");
		assertEquals("Player name should match the passed parameter.", "Yugi",
				player.getName());

	}

	@org.junit.Test(timeout = 1000)
	public void testPlayerGetField() throws Exception {

		Player player = new Player("Yugi");
		assertTrue("Player constructor should initiallize field.",
				player.getField() != null);

	}

}