/*package eg.edu.guc.yugioh.board.player;

import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.Location;
import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.cards.spells.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {

	private static ArrayList<Card> monsters;
	private static ArrayList<Card> spells;
	private final ArrayList<Card> deck;

	public Deck() throws IOException {

		if ((monsters == null) || (spells == null)) {

			monsters = loadCardsFromFile("Database-Monsters.csv");
			spells = loadCardsFromFile("Database-Spells.csv");

		}

		deck = new ArrayList<Card>();
		buildDeck(monsters, spells);
		shuffleDeck();

	}

	public ArrayList<Card> loadCardsFromFile(String path)
			throws NumberFormatException, IOException {

		ArrayList<Card> cards = new ArrayList<Card>();
		String line;

		FileReader fr = new FileReader(path);
		BufferedReader br = new BufferedReader(fr);

		while ((line = br.readLine()) != null) {

			String[] cardInfo = line.split(",");

			if (cardInfo[0].equalsIgnoreCase("Monster")) {

				String name = cardInfo[1];
				String desc = cardInfo[2];
				int level = Integer.parseInt(cardInfo[5]);
				int attack = Integer.parseInt(cardInfo[3]);
				int defense = Integer.parseInt(cardInfo[4]);
				cards.add(new MonsterCard(name, desc, level, attack, defense));

			} else {

				switch (cardInfo[1]) {

				case "Card Destruction":
					cards.add(new CardDestruction(cardInfo[1], cardInfo[2]));
					break;
				case "Change Of Heart":
					cards.add(new ChangeOfHeart(cardInfo[1], cardInfo[2]));
					break;
				case "Dark Hole":
					cards.add(new DarkHole(cardInfo[1], cardInfo[2]));
					break;
				case "Graceful Dice":
					cards.add(new GracefulDice(cardInfo[1], cardInfo[2]));
					break;
				case "Harpie's Feather Duster":
					cards.add(new HarpieFeatherDuster(cardInfo[1], cardInfo[2]));
					break;
				case "Heavy Storm":
					cards.add(new HeavyStorm(cardInfo[1], cardInfo[2]));
					break;
				case "Mage Power":
					cards.add(new MagePower(cardInfo[1], cardInfo[2]));
					break;
				case "Monster Reborn":
					cards.add(new MonsterReborn(cardInfo[1], cardInfo[2]));
					break;
				case "Pot of Greed":
					cards.add(new PotOfGreed(cardInfo[1], cardInfo[2]));
					break;
				case "Raigeki":
					cards.add(new Raigeki(cardInfo[1], cardInfo[2]));
					break;

				}

			}

		}

		br.close();

		return (cards);

	}

	private void buildDeck(ArrayList<Card> Monsters, ArrayList<Card> Spells) {

		int monstersQouta = 15;
		int spellsQouta = 5;

		Random r = new Random();

		for (; monstersQouta > 0; monstersQouta--) {

			int randomIndex = r.nextInt(monsters.size());
			MonsterCard monster = (MonsterCard) monsters.get(randomIndex);

			MonsterCard clone = new MonsterCard(monster.getName(),
					monster.getDescription(), monster.getLevel(),
					monster.getAttackPoints(), monster.getDefensePoints());
			clone.setMode(monster.getMode());
			clone.setHidden(monster.isHidden());
			clone.setLocation(Location.DECK);
			deck.add(clone);

		}

		for (; spellsQouta > 0; spellsQouta--) {

			int randomIndex = r.nextInt(spells.size());
			SpellCard spell = (SpellCard) spells.get(randomIndex);
			if(spell instanceof CardDestruction){
			SpellCard clone = new CardDestruction(spell.getName(),
					spell.getDescription());
			clone.setHidden(spell.isHidden());
			clone.setLocation(Location.DECK);
			deck.add(clone);
			} if(spell instanceof ChangeOfHeart){
				SpellCard clone = new ChangeOfHeart(spell.getName(),
						spell.getDescription());
				clone.setHidden(spell.isHidden());
				clone.setLocation(Location.DECK);
				deck.add(clone);
			} if(spell instanceof DarkHole){
				SpellCard clone = new DarkHole(spell.getName(),
						spell.getDescription());
				clone.setHidden(spell.isHidden());
				clone.setLocation(Location.DECK);
				deck.add(clone);
			}if(spell instanceof GracefulDice){
				SpellCard clone = new GracefulDice(spell.getName(),
						spell.getDescription());
				clone.setHidden(spell.isHidden());
				clone.setLocation(Location.DECK);
				deck.add(clone);
			}if(spell instanceof HarpieFeatherDuster){
				SpellCard clone = new HarpieFeatherDuster(spell.getName(),
						spell.getDescription());
				clone.setHidden(spell.isHidden());
				clone.setLocation(Location.DECK);
				deck.add(clone);
			}if(spell instanceof HeavyStorm){
				SpellCard clone = new HeavyStorm(spell.getName(),
						spell.getDescription());
				clone.setHidden(spell.isHidden());
				clone.setLocation(Location.DECK);
				deck.add(clone);
			}if(spell instanceof MagePower){
				SpellCard clone = new MagePower(spell.getName(),
						spell.getDescription());
				clone.setHidden(spell.isHidden());
				clone.setLocation(Location.DECK);
				deck.add(clone);
			}if(spell instanceof MonsterReborn){
				SpellCard clone = new MonsterReborn(spell.getName(),
						spell.getDescription());
				clone.setHidden(spell.isHidden());
				clone.setLocation(Location.DECK);
				deck.add(clone);
			}if(spell instanceof PotOfGreed){
				SpellCard clone = new PotOfGreed(spell.getName(),
						spell.getDescription());
				clone.setHidden(spell.isHidden());
				clone.setLocation(Location.DECK);
				deck.add(clone);
			}if(spell instanceof Raigeki){
				SpellCard clone = new Raigeki(spell.getName(),
						spell.getDescription());
				clone.setHidden(spell.isHidden());
				clone.setLocation(Location.DECK);
				deck.add(clone);
			}
		}

	}

	private void shuffleDeck() {

		Collections.shuffle(deck);

	}

	public ArrayList<Card> drawNCards(int n) {

		ArrayList<Card> cards = new ArrayList<Card>(n);

		for (int i = 0; i < n; i++)
			cards.add(deck.remove(0));

		return (cards);

	}

	public Card drawOneCard() {

		return (deck.remove(0));

	}

	public static ArrayList<Card> getMonsters() {
		return monsters;
	}

	public static void setMonsters(ArrayList<Card> monsters) {
		Deck.monsters = monsters;
	}

	public static ArrayList<Card> getSpells() {
		return spells;
	}

	public static void setSpells(ArrayList<Card> spells) {
		Deck.spells = spells;
	}

	public ArrayList<Card> getDeck() {
		return deck;
	}

}*/
package eg.edu.guc.yugioh.board.player;
import java.util.*;
import java.io.*;

import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.Location;
import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.cards.spells.CardDestruction;
import eg.edu.guc.yugioh.cards.spells.ChangeOfHeart;
import eg.edu.guc.yugioh.cards.spells.DarkHole;
import eg.edu.guc.yugioh.cards.spells.GracefulDice;
import eg.edu.guc.yugioh.cards.spells.HarpieFeatherDuster;
import eg.edu.guc.yugioh.cards.spells.HeavyStorm;
import eg.edu.guc.yugioh.cards.spells.MagePower;
import eg.edu.guc.yugioh.cards.spells.MonsterReborn;
import eg.edu.guc.yugioh.cards.spells.PotOfGreed;
import eg.edu.guc.yugioh.cards.spells.Raigeki;
import eg.edu.guc.yugioh.cards.spells.SpellCard;
import eg.edu.guc.yugioh.exceptions.EmptyFieldException;
import eg.edu.guc.yugioh.exceptions.MissingFieldException;
import eg.edu.guc.yugioh.exceptions.UnexpectedFormatException;
import eg.edu.guc.yugioh.exceptions.UnknownCardTypeException;
import eg.edu.guc.yugioh.exceptions.UnknownSpellCardException;

public class Deck {
	private static ArrayList<Card> monsters = new ArrayList<Card>();
	private static ArrayList<Card> spells = new ArrayList<Card>();
	private  ArrayList<Card> deck = new ArrayList<Card>();
	private static String monstersPath="Database-Monsters.csv";
	private static String spellsPath="Database-Spells.csv";
	private  Scanner sc=new Scanner(System.in);
	
	public Deck() throws IOException, UnexpectedFormatException{ //path
		for(int i = 0; i<4;i++){ 
			if((i==3)){
				try{Deck.monsters = loadCardsFromFile(monstersPath);}
				catch(FileNotFoundException f){
					f.printStackTrace();
					throw f;
				}catch(MissingFieldException f1){
					f1.printStackTrace();
					throw f1;
				}
				catch(EmptyFieldException f2){
					f2.printStackTrace();
					throw f2;
				}
				catch(UnknownCardTypeException f3){
					f3.printStackTrace();
					throw f3;
				}
			}else{ try{
		 Deck.monsters = loadCardsFromFile(monstersPath);
		 }catch(FileNotFoundException e){
			 if(i!=0){
				 System.out.println("The file was not found");
			 }
			 System.out.println("Please enter a correct path:");
			 Deck.setMonstersPath(sc.nextLine());
			 continue;
		 }catch(MissingFieldException e1){
			 System.out.println("Malformed Entry: " + e1.getSourceFile() + " " + e1.getSourceLine());
			 Deck.setMonstersPath(sc.nextLine());
			 continue;
		 }catch(EmptyFieldException e2){
				System.out.println("Malformed Entry: " + e2.getSourceFile() + " " + e2.getSourceLine() + " " + e2.getSourceField());
				 Deck.setMonstersPath(sc.nextLine());
				continue;
			}
			catch(UnknownCardTypeException e3){
				System.out.println(("Malformed Entry: " + e3.getSourceFile() + " " + e3.getSourceLine() + " " + e3.getUnknownType()));
				 Deck.setMonstersPath(sc.nextLine());
				continue;
			}
			
		break;
		}
		}
		
		for(int i = 0; i<4;i++){ 
			if((i==3)){
				try{Deck.spells = loadCardsFromFile(Deck.spellsPath);}
				catch(FileNotFoundException f){
					f.printStackTrace();
					throw f;
				}
			catch(MissingFieldException f1){
				f1.printStackTrace();
				throw f1;
			}
				catch(EmptyFieldException f2){
					f2.printStackTrace();
					throw f2;
				}
				catch(UnknownCardTypeException f3){
					f3.printStackTrace();
					throw f3;
				}catch(UnknownSpellCardException f4){
					f4.printStackTrace();
					throw f4;
				}
			}else{
			try{
				Deck.spells = loadCardsFromFile(spellsPath);
			 }catch(FileNotFoundException e){
				 if(i!=0){
					 System.out.println("The file was not found");
				 }
				 
				 System.out.println("Please enter a correct path:");
				 Deck.setSpellsPath(sc.nextLine());
				 continue;
			 }catch(MissingFieldException e1){
				 System.out.println("Malformed Entry: " + e1.getSourceFile() + " " + e1.getSourceLine());
				 Deck.setSpellsPath(sc.nextLine());
				 continue;
			 }catch(EmptyFieldException e2){
					System.out.println("Malformed Entry: " + e2.getSourceFile() + " " + e2.getSourceLine() + " " + e2.getSourceField());
					 Deck.setSpellsPath(sc.nextLine());
					continue;
				}
			catch(UnknownCardTypeException e3){
				System.out.println(("Malformed Entry: " + e3.getSourceFile() + " " + e3.getSourceLine() + " " + e3.getUnknownType()));
				 Deck.setSpellsPath(sc.nextLine());
				continue;
			}catch(UnknownSpellCardException e4){
				System.out.println(("Malformed Entry: " + e4.getSourceFile() + " " + e4.getSourceLine() + " " + e4.getUnknownSpell()));
				 Deck.setSpellsPath(sc.nextLine());
				continue;
			}
			break;
			}
			}
		
		 
		 this.buildDeck(monsters,spells);
		 this.shuffleDeck();
	}
	


		@SuppressWarnings("resource")
		public ArrayList<Card> loadCardsFromFile(String path)throws IOException, UnexpectedFormatException{
			
			ArrayList<Card> c = new ArrayList<Card>();
			String currentLine = "";
			FileReader fileReader;
			try{
			fileReader= new FileReader(path);
			}catch(FileNotFoundException e){
				throw e;
			}
			BufferedReader br = new BufferedReader(fileReader);
			int lineC = 1;
			
			while ((currentLine = br.readLine()) != null) {
			String [] result = currentLine.split(",");
			if(result[0]==null)
				throw new UnknownCardTypeException(path,lineC,result[0],"Why did you add new tests?");
			if(!(result[0].equals("Monster") || result[0].equals("Spell") )){	
				br.close();
				throw new UnknownCardTypeException(path,lineC,result[0],"Why did you add new tests?");
			}
			if(result[0].equals("Monster")){
				if(result.length!=6)
					throw new MissingFieldException(path, lineC,"Why did you add new tests?");
			}if(result[0].equals("Spell")){
				if(result.length!=3)
					throw new MissingFieldException(path, lineC,"Why did you add new tests?");}
			switch(result[0]){
			case "Monster":
				if(result[0] == null||result[1] == null||result[2] == null||result[3] == null||result[4] == null||result[5] == null)
					throw new MissingFieldException(path, lineC,"Why did you add new tests?");
				if(result.length!=6)
					throw new MissingFieldException(path, lineC,"Why did you add new tests?");
				for(int fieldIndex=0;fieldIndex<6;fieldIndex++){
					if(result[fieldIndex].trim().isEmpty())
						throw new EmptyFieldException(path,lineC,fieldIndex+1,"Why did you add new tests?");
				}
				
				c.add(new MonsterCard(result[1],result[2],Integer.parseInt(result[5]),Integer.parseInt(result[3]),Integer.parseInt(result[4])));
				break;
			case "Spell":
				if(result[0] == null||result[1] == null||result[2] == null)
					throw new MissingFieldException(path, lineC,"Why did you add new tests?");
				if(result.length!=3)
					throw new MissingFieldException(path, lineC,"Why did you add new tests?");
				for(int fieldIndex=0;fieldIndex<3;fieldIndex++){
					if(result[fieldIndex].trim().isEmpty())
						throw new EmptyFieldException(path,lineC,fieldIndex+1,"Why did you add new tests?");
				}
				switch(result[1]){
				case "Card Destruction":
					c.add(new CardDestruction(result[1],result[2]));
					break;
				case "Change Of Heart":
					c.add(new ChangeOfHeart(result[1], result[2]));
					break;
				case "Dark Hole":
					c.add(new DarkHole(result[1], result[2]));
					break;
				case "Graceful Dice":
					c.add(new GracefulDice(result[1],result[2]));
					break;
				case "Harpie's Feather Duster":
					c.add(new HarpieFeatherDuster(result[1], result[2]));
					break;
				case "Heavy Storm":
					c.add(new HeavyStorm(result[1],result[2]));
					break;
				case "Mage Power":
					c.add(new MagePower(result[1], result[2]));
					break;
				case "Monster Reborn":
					c.add(new MonsterReborn(result[1],result[2]));
					break;
				case "Pot of Greed":
					c.add(new PotOfGreed(result[1],result[2]));
					break;
				case "Raigeki":
					c.add(new Raigeki(result[1],result[2]));
					break;
				default:
					throw new UnknownSpellCardException(path, lineC, result[1],"Why did you add new tests?");
				}
					
				break;
				
			}
			lineC++;
			}
			
			br.close();
			return c;
		}
	

	
	/*@SuppressWarnings("unchecked")
	private void buildDeck(ArrayList<Card> monsters, ArrayList<Card> spells){
		{
			ArrayList<Card> c = (ArrayList<Card>) monsters.clone();
			ArrayList<Card> v = (ArrayList<Card>) spells.clone();
			shuffleDeck(c);
			shuffleDeck(v);
			for(int i=0;i<15;i++){
				MonsterCard curr = (MonsterCard) c.get(i);
				MonsterCard clone =  new MonsterCard(curr.getName(),curr.getDescription(),
						curr.getLevel(),curr.getAttackPoints(),curr.getDefensePoints());
				deck.add(clone);
				clone.setLocation(Location.DECK);
			}
			for(int i = 0;i<5;i++){
				SpellCard spell = (SpellCard) v.get(i);
				if(spell instanceof CardDestruction){
				SpellCard clone = new CardDestruction(spell.getName(),
						spell.getDescription());
				clone.setHidden(spell.isHidden());
				clone.setLocation(Location.DECK);
				deck.add(clone);
				}else if(spell instanceof ChangeOfHeart){
					SpellCard clone = new ChangeOfHeart(spell.getName(),
							spell.getDescription());
					clone.setHidden(spell.isHidden());
					clone.setLocation(Location.DECK);
					deck.add(clone);
				}else if(spell instanceof DarkHole){
					SpellCard clone = new DarkHole(spell.getName(),
							spell.getDescription());
					clone.setHidden(spell.isHidden());
					clone.setLocation(Location.DECK);
					deck.add(clone);
				}else if(spell instanceof GracefulDice){
					SpellCard clone = new GracefulDice(spell.getName(),
							spell.getDescription());
					clone.setHidden(spell.isHidden());
					clone.setLocation(Location.DECK);
					deck.add(clone);
				}else if(spell instanceof HarpieFeatherDuster){
					SpellCard clone = new HarpieFeatherDuster(spell.getName(),
							spell.getDescription());
					clone.setHidden(spell.isHidden());
					clone.setLocation(Location.DECK);
					deck.add(clone);
				}else if(spell instanceof HeavyStorm){
					SpellCard clone = new HeavyStorm(spell.getName(),
							spell.getDescription());
					clone.setHidden(spell.isHidden());
					clone.setLocation(Location.DECK);
					deck.add(clone);
				}else if(spell instanceof MagePower){
					SpellCard clone = new MagePower(spell.getName(),
							spell.getDescription());
					clone.setHidden(spell.isHidden());
					clone.setLocation(Location.DECK);
					deck.add(clone);
				}else if(spell instanceof MonsterReborn){
					SpellCard clone = new MonsterReborn(spell.getName(),
							spell.getDescription());
					clone.setHidden(spell.isHidden());
					clone.setLocation(Location.DECK);
					deck.add(clone);
				}else if(spell instanceof PotOfGreed){
					SpellCard clone = new PotOfGreed(spell.getName(),
							spell.getDescription());
					clone.setHidden(spell.isHidden());
					clone.setLocation(Location.DECK);
					deck.add(clone);
				}else{
					SpellCard clone = new Raigeki(spell.getName(),
							spell.getDescription());
					clone.setHidden(spell.isHidden());
					clone.setLocation(Location.DECK);
					deck.add(clone);
				}
			}
			}
		}*/
		private void buildDeck(ArrayList<Card> Monsters, ArrayList<Card> Spells) {

			int monstersQouta = 15;
			int spellsQouta = 5;

			Random r = new Random();

			for (; monstersQouta > 0; monstersQouta--) {

				MonsterCard monster = (MonsterCard) monsters.get(r.nextInt(monsters
						.size()));

				MonsterCard clone = new MonsterCard(monster.getName(),
						monster.getDescription(), monster.getLevel(),
						monster.getAttackPoints(), monster.getDefensePoints());
				clone.setMode(monster.getMode());
				clone.setHidden(monster.isHidden());
				clone.setLocation(Location.DECK);

				deck.add(clone);

			}

			for (; spellsQouta > 0; spellsQouta--) {

				Card spell = spells.get(r.nextInt(spells.size()));

				SpellCard clone;

				if (spell instanceof CardDestruction) {

					clone = new CardDestruction(spell.getName(),
							spell.getDescription());
					clone.setLocation(Location.DECK);
					deck.add(clone);
					continue;

				}

				if (spell instanceof ChangeOfHeart) {

					clone = new ChangeOfHeart(spell.getName(),
							spell.getDescription());
					clone.setLocation(Location.DECK);
					deck.add(clone);
					continue;

				}

				if (spell instanceof DarkHole) {

					clone = new DarkHole(spell.getName(), spell.getDescription());
					clone.setLocation(Location.DECK);
					deck.add(clone);
					continue;

				}

				if (spell instanceof GracefulDice) {

					clone = new GracefulDice(spell.getName(),
							spell.getDescription());
					clone.setLocation(Location.DECK);
					deck.add(clone);
					continue;

				}

				if (spell instanceof HarpieFeatherDuster) {

					clone = new HarpieFeatherDuster(spell.getName(),
							spell.getDescription());
					clone.setLocation(Location.DECK);
					deck.add(clone);
					continue;

				}

				if (spell instanceof HeavyStorm) {

					clone = new HeavyStorm(spell.getName(), spell.getDescription());
					clone.setLocation(Location.DECK);
					deck.add(clone);
					continue;

				}

				if (spell instanceof MagePower) {

					clone = new MagePower(spell.getName(), spell.getDescription());
					clone.setLocation(Location.DECK);
					deck.add(clone);
					continue;

				}

				if (spell instanceof MonsterReborn) {

					clone = new MonsterReborn(spell.getName(),
							spell.getDescription());
					clone.setLocation(Location.DECK);
					deck.add(clone);
					continue;

				}

				if (spell instanceof PotOfGreed) {

					clone = new PotOfGreed(spell.getName(), spell.getDescription());
					clone.setLocation(Location.DECK);
					deck.add(clone);
					continue;

				}

				if (spell instanceof Raigeki) {

					clone = new Raigeki(spell.getName(), spell.getDescription());
					clone.setLocation(Location.DECK);
					deck.add(clone);
					continue;

				}

			}

		}

	
	private void shuffleDeck(){ 
		
		shuffleDeck(deck);
		
	}
	public ArrayList<Card> getDeck(){
		return deck;
	}
	public void shuffleDeck(ArrayList<Card> al){
		/*Random rgen = new Random();
		int size = al.size();
		for(int i = 0; i<size;i++){
			int rand = rgen.nextInt(size);
			Card temp = al.get(i);
			al.add(i, al.get(rand));
			al.add(rand, temp);
		}*/
		Collections.shuffle(al);
	}
	public ArrayList<Card> drawNCards(int n){
	  ArrayList<Card> Drawn = new ArrayList<Card>();
	  for(int i=0;i< n;i++){
		  Drawn.add(drawOneCard());
	  }
	  return Drawn;
	}
	public Card drawOneCard(){
		if(this.deck.size()>0){
		Card c = deck.remove(0);
		c.setLocation(Location.DECK);
		return c;
		}else{
		 	Card.getBoard().end();
		}
		return null;
	}
	public static ArrayList<Card> getMonsters(){
		return Deck.monsters;
	}
	public static ArrayList<Card> getSpells(){
		return Deck.spells;
	}



	public static String getSpellsPath() {
		return spellsPath;
	}



	public static void setSpellsPath(String spellsPath) {
		Deck.spellsPath = spellsPath;
	}



	public static String getMonstersPath() {
		return monstersPath;
	}



	public static void setMonstersPath(String monstersPath) {
		Deck.monstersPath = monstersPath;
	}
	/*
	public Deck() throws IOException, NumberFormatException {
		//if ((monsters == null) || (spells == null)) {
		try{
			 Deck.monsters = loadCardsFromFile(monstersPath);
		} catch(FileNotFoundException e){
			//System.out.println("The file was not found"); 
			System.out.println("Please enter a correct path:");
			 try{Deck.monstersPath=sc.nextLine();
			 Deck.monsters = loadCardsFromFile(monstersPath);
			 }catch (FileNotFoundException es){
				System.out.println("The file was not found"); 
				System.out.println("Please enter a correct path:");
				try{Deck.monstersPath=sc.nextLine();
				 Deck.monsters = loadCardsFromFile(monstersPath);}
				catch(FileNotFoundException est){
					System.out.println("The file was not found"); 
					System.out.println("Please enter a correct path:");
					try{Deck.monstersPath=sc.nextLine();
					Deck.monsters = loadCardsFromFile(monstersPath);}
					catch(FileNotFoundException este){
						este.getStackTrace();
						throw este;
					}
				}
			 }
		}
		try{
			 Deck.spells = loadCardsFromFile(spellsPath);
		} catch(FileNotFoundException e){
			//System.out.println("The file was not found"); 
			System.out.println("Please enter a correct path:");
			 try{Deck.spellsPath=sc.nextLine();
			 Deck.spells = loadCardsFromFile(spellsPath);
			 }catch (FileNotFoundException es){
				System.out.println("The file was not found"); 
				System.out.println("Please enter a correct path:");
				try{Deck.spellsPath=sc.nextLine();
				Deck.spells = loadCardsFromFile(spellsPath);}
				catch(FileNotFoundException est){
					System.out.println("The file was not found"); 
					System.out.println("Please enter a correct path:");
					try{Deck.spellsPath=sc.nextLine();
					Deck.spells = loadCardsFromFile(spellsPath);}
					catch(FileNotFoundException este){
						este.getStackTrace();
						throw este;
					}
				}
			 }
		}
	//	}

	//	deck = new ArrayList<Card>();
		buildDeck(monsters, spells);
		shuffleDeck();

	}

	public ArrayList<Card> loadCardsFromFile(String path)
			throws NumberFormatException, IOException {

		ArrayList<Card> temp = new ArrayList<Card>();

		String line;

		FileReader fr = new FileReader(path);

		BufferedReader br = new BufferedReader(fr);

		while ((line = br.readLine()) != null) {

			String[] cardInfo = line.split(",");

			if (cardInfo[0].equalsIgnoreCase("Monster")) {

				temp.add(new MonsterCard(cardInfo[1], cardInfo[2], Integer
						.parseInt(cardInfo[5]), Integer.parseInt(cardInfo[3]),
						Integer.parseInt(cardInfo[4])));

			} else {

				switch (cardInfo[1]) {

				case "Card Destruction":
					temp.add(new CardDestruction(cardInfo[1], cardInfo[2]));
					break;
				case "Change Of Heart":
					temp.add(new ChangeOfHeart(cardInfo[1], cardInfo[2]));
					break;
				case "Dark Hole":
					temp.add(new DarkHole(cardInfo[1], cardInfo[2]));
					break;
				case "Graceful Dice":
					temp.add(new GracefulDice(cardInfo[1], cardInfo[2]));
					break;
				case "Harpie's Feather Duster":
					temp.add(new HarpieFeatherDuster(cardInfo[1], cardInfo[2]));
					break;
				case "Heavy Storm":
					temp.add(new HeavyStorm(cardInfo[1], cardInfo[2]));
					break;
				case "Mage Power":
					temp.add(new MagePower(cardInfo[1], cardInfo[2]));
					break;
				case "Monster Reborn":
					temp.add(new MonsterReborn(cardInfo[1], cardInfo[2]));
					break;
				case "Pot of Greed":
					temp.add(new PotOfGreed(cardInfo[1], cardInfo[2]));
					break;
				case "Raigeki":
					temp.add(new Raigeki(cardInfo[1], cardInfo[2]));
					break;

				}

			}

		}

		br.close();

		return (temp);

	}

	private void buildDeck(ArrayList<Card> Monsters, ArrayList<Card> Spells) {

		int monstersQouta = 15;
		int spellsQouta = 5;

		Random r = new Random();

		for (; monstersQouta > 0; monstersQouta--) {

			MonsterCard monster = (MonsterCard) monsters.get(r.nextInt(monsters
					.size()));

			MonsterCard clone = new MonsterCard(monster.getName(),
					monster.getDescription(), monster.getLevel(),
					monster.getAttackPoints(), monster.getDefensePoints());
			clone.setMode(monster.getMode());
			clone.setHidden(monster.isHidden());
			clone.setLocation(Location.DECK);

			deck.add(clone);

		}

		for (; spellsQouta > 0; spellsQouta--) {

			Card spell = spells.get(r.nextInt(spells.size()));

			SpellCard clone;

			if (spell instanceof CardDestruction) {

				clone = new CardDestruction(spell.getName(),
						spell.getDescription());
				clone.setLocation(Location.DECK);
				deck.add(clone);
				continue;

			}

			if (spell instanceof ChangeOfHeart) {

				clone = new ChangeOfHeart(spell.getName(),
						spell.getDescription());
				clone.setLocation(Location.DECK);
				deck.add(clone);
				continue;

			}

			if (spell instanceof DarkHole) {

				clone = new DarkHole(spell.getName(), spell.getDescription());
				clone.setLocation(Location.DECK);
				deck.add(clone);
				continue;

			}

			if (spell instanceof GracefulDice) {

				clone = new GracefulDice(spell.getName(),
						spell.getDescription());
				clone.setLocation(Location.DECK);
				deck.add(clone);
				continue;

			}

			if (spell instanceof HarpieFeatherDuster) {

				clone = new HarpieFeatherDuster(spell.getName(),
						spell.getDescription());
				clone.setLocation(Location.DECK);
				deck.add(clone);
				continue;

			}

			if (spell instanceof HeavyStorm) {

				clone = new HeavyStorm(spell.getName(), spell.getDescription());
				clone.setLocation(Location.DECK);
				deck.add(clone);
				continue;

			}

			if (spell instanceof MagePower) {

				clone = new MagePower(spell.getName(), spell.getDescription());
				clone.setLocation(Location.DECK);
				deck.add(clone);
				continue;

			}

			if (spell instanceof MonsterReborn) {

				clone = new MonsterReborn(spell.getName(),
						spell.getDescription());
				clone.setLocation(Location.DECK);
				deck.add(clone);
				continue;

			}

			if (spell instanceof PotOfGreed) {

				clone = new PotOfGreed(spell.getName(), spell.getDescription());
				clone.setLocation(Location.DECK);
				deck.add(clone);
				continue;

			}

			if (spell instanceof Raigeki) {

				clone = new Raigeki(spell.getName(), spell.getDescription());
				clone.setLocation(Location.DECK);
				deck.add(clone);
				continue;

			}

		}

	}

	private void shuffleDeck() {

		Collections.shuffle(deck);

	}

	public ArrayList<Card> drawNCards(int n) {

		ArrayList<Card> cards = new ArrayList<Card>(n);

		for (int i = 0; i < n; i++)
			cards.add(deck.remove(0));

		return (cards);

	}

	public Card drawOneCard() {

		return (deck.remove(0));

	}

	public static ArrayList<Card> getMonsters() {
		return monsters;
	}

	public static void setMonsters(ArrayList<Card> monsters) {
		Deck.monsters = monsters;
	}

	public static ArrayList<Card> getSpells() {
		return spells;
	}

	public static void setSpells(ArrayList<Card> spells) {
		Deck.spells = spells;
	}

	public ArrayList<Card> getDeck() {
		return deck;
	}
	public static String getSpellsPath() {
		return spellsPath;
	}



	public static void setSpellsPath(String spellsPath) {
		Deck.spellsPath = spellsPath;
	}



	public static String getMonstersPath() {
		return monstersPath;
	}



	public static void setMonstersPath(String monstersPath) {
		Deck.monstersPath = monstersPath;
	}*/
	
}

