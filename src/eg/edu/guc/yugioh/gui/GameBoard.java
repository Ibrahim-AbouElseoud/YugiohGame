package eg.edu.guc.yugioh.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import eg.edu.guc.yugioh.board.player.Player;
import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.Mode;
import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.cards.spells.SpellCard;
import eg.edu.guc.yugioh.exceptions.UnexpectedFormatException;
import eg.edu.guc.yugioh.gui.board.DEFUNCTCardInfoPanel;
import eg.edu.guc.yugioh.gui.board.HandPanel;
import eg.edu.guc.yugioh.gui.board.HandTab;
import eg.edu.guc.yugioh.gui.board.InvisibleButton;
import eg.edu.guc.yugioh.gui.board.MonsterArea;
import eg.edu.guc.yugioh.gui.board.PhaseIndicator;
import eg.edu.guc.yugioh.gui.board.SpellArea;
import eg.edu.guc.yugioh.gui.board.buttons.CardB;
import eg.edu.guc.yugioh.gui.board.buttons.MonsterCardB;
import eg.edu.guc.yugioh.gui.board.buttons.SpellCardB;
import eg.edu.guc.yugioh.listeners.ActionExitGame;
import eg.edu.guc.yugioh.listeners.Controller;
import eg.edu.guc.yugioh.listeners.board.ActionDrawCardP1;
import eg.edu.guc.yugioh.listeners.board.ActionDrawCardP2;
import eg.edu.guc.yugioh.listeners.board.ActionEndPhase;
import eg.edu.guc.yugioh.listeners.board.ActionEndTurn;
import eg.edu.guc.yugioh.listeners.board.ActionMenuMonster;
import eg.edu.guc.yugioh.listeners.board.ActionTestName;

@SuppressWarnings("serial")
public class GameBoard extends SharedWindowFunc {
	//private String nameP1="";
	//private String nameP2="";
	private MonsterCardB monsterToStartAttack;
	private MonsterCardB monsterToBeAttacked;
	private InvisibleButton endPhase= new InvisibleButton();
	private InvisibleButton endTurn= new InvisibleButton();
	private PhaseIndicator phaseIndicator=new PhaseIndicator();
	private Player P1 = Controller.getBoard().getActivePlayer();//P1
	private Player P2 = Controller.getBoard().getOpponentPlayer();//P2
	private HandPanel P1Hand=new HandPanel();
	private HandPanel P2Hand=new HandPanel();
	private HandTab tabArea = new HandTab(P1.getName(), P2.getName(), P1Hand, P2Hand); //the hand
	private MonsterArea p1MArea = new MonsterArea();//the monster area
	private MonsterArea p2MArea = new MonsterArea();
	private SpellArea p1SArea = new SpellArea();//the player spell area
	private SpellArea p2SArea = new SpellArea();
	private InvisibleButton deckBP1=new InvisibleButton(); //deck button
	private InvisibleButton deckBP2=new InvisibleButton();
	private InvisibleButton p1Grave=new InvisibleButton(); //GraveYard
	private InvisibleButton p2Grave=new InvisibleButton();
	
	
	//private CardInfoPanel cardInfoPanel= new CardInfoPanel(); //has bug doesn't display properly, other option is forget panel and implement all in here as JLabels
	private JLabel P1LabelName=new JLabel(P1.getName()); //posibility of putting name and LP in another panel called player LPDashBoard
	private JLabel P2LabelName=new JLabel(P2.getName());
	private JLabel P1LPLabel=new JLabel(""+P1.getLifePoints());
	private JLabel P2LPLabel=new JLabel(""+P2.getLifePoints());
	//Parts of cardInfoPanel
	private JTextArea textArea=new JTextArea("Card description");

	private JLabel cardInfoPicture=new JLabel(new ImageIcon("cardsL\\CardBack.jpg"));
	private JLabel attack=new JLabel("Attack: ");
	private JLabel defense=new JLabel("Defense: ");

	public GameBoard(String title) throws IOException  {
		super(title);
		Dimension screenSize =Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(screenSize.width/4, screenSize.height/4); //comment this if your screen is less than Full HD resolution
		this.setSize(1024, 768);
		//this.setExtendedState(Frame.MAXIMIZED_BOTH);// fullscreen
		this.setUndecorated(true); //fullscreen
		
		this.setLayout(new BorderLayout());
		this.setContentPane(new JLabel(new ImageIcon("GameBoard HDBET.jpg")));
		InvisibleButton exit = new InvisibleButton();
		exit.setBounds(830, 0, 140, 50);
		exit.addActionListener(new ActionExitGame());
		this.add(exit);
		this.setLayout(null);
		this.add(tabArea);
	
		endPhase.setBounds(535, 0, 145, 50);
		endPhase.addActionListener(new ActionEndPhase());//in later stage give listener that opens dialogue saying you must draw first to end phase
		endPhase.setVisible(false);
		this.add(phaseIndicator);
		this.add(endPhase);
		
		endTurn.setBounds(680, 0, 145, 50);
		endTurn.addActionListener(new ActionEndTurn());
		endTurn.setVisible(false);
		this.add(endTurn);
		//add5DrawnP1();
		//add5DrawnP2();
//look into JLayerdPanel to put components on to stop overwriting
		//look into how to properly use scroll panel
		
		//JLabels of name and LP
		P1LabelName.setFont(new Font("street soul", Font.BOLD, 30));//add placement
		P1LabelName.setForeground(Color.WHITE); //BEWARE I SWITCHED THE NAME LOCATIONS OF P1 and P2 look at setbound subscripts of this code
		P2LabelName.setBounds(718, 125, 100, 30);
		P1LPLabel.setFont(new Font("street soul", Font.BOLD, 30));
		P1LPLabel.setForeground(Color.YELLOW);
		P2LPLabel.setBounds(718, 170, 100, 30); //put these JLabels on top of JLayerPanel
		this.add(P1LPLabel);
		this.add(P1LabelName);
		P2LabelName.setFont(new Font("street soul", Font.BOLD, 30));
		P2LabelName.setForeground(Color.WHITE);
		P1LabelName.setBounds(718, 384, 100, 30);
		P2LPLabel.setFont(new Font("street soul", Font.BOLD, 30));
		P2LPLabel.setForeground(Color.YELLOW);
		P1LPLabel.setBounds(718, 440, 100, 30);
		this.add(P2LPLabel);
		this.add(P2LabelName);
		
		//Monster Area
		p1MArea.setBounds(224, 345, 466, 95);
		p2MArea.setBounds(224, 180, 466, 95);
		this.add(p1MArea);
		this.add(p2MArea);
		//Deck button
		deckBP1.setForeground(Color.WHITE);//deck P1
		deckBP1.setBounds(130, 300, 70, 95);
		deckBP1.setIcon(new ImageIcon("cards\\backButton9570.jpg"));
		deckBP1.setText(""+Controller.getBoard().getActivePlayer().getField().getDeck().getDeck().size());
		deckBP1.setHorizontalTextPosition(SwingConstants.CENTER);
		this.add(deckBP1);
		deckBP1.addActionListener(new ActionDrawCardP1());//note remove now and add at end phase
		
		deckBP2.setForeground(Color.WHITE);//deck button P2
		deckBP2.setBounds(130,175, 70, 95);
		deckBP2.setIcon(new ImageIcon("cards\\backButton9570.jpg"));
		this.add(deckBP2);
		deckBP2.setText(""+Controller.getBoard().getOpponentPlayer().getField().getDeck().getDeck().size());
		deckBP2.setHorizontalTextPosition(SwingConstants.CENTER);
		//deckBP2.addActionListener(new ActionDrawCardP2());//note remove now and add in end phase
		
		p1Grave.setForeground(Color.WHITE);//grave P1
		p1Grave.setBounds(130, 450, 70, 95);
//		p1Grave.setIcon(new ImageIcon("cards\\backButton9570.jpg"));
		p1Grave.setText("0");
		p1Grave.setHorizontalTextPosition(SwingConstants.CENTER);
		p2Grave.setForeground(Color.WHITE);//grave P2
		p2Grave.setBounds(130, 70, 70, 95);
//		p2Grave.setIcon(new ImageIcon("cards\\backButton9570.jpg"));
		p2Grave.setText("0");
		p2Grave.setHorizontalTextPosition(SwingConstants.CENTER);
		this.add(p1Grave);
		this.add(p2Grave);
		
		//InvisibleButton deckBP2=new InvisibleButton();
		//Controller.getBoard().getActivePlayer().getField().getDeck().getDeck().size();
		p1SArea.setBounds(224, 450, 466, 95);
		this.add(p1SArea);
		p2SArea.setBounds(224, 77, 466, 95);
		this.add(p2SArea);
		//CARDINFOPANEL start: this is the part of card Info panel
		cardInfoPicture.setBounds(844, 94, 156, 191);
		textArea=new JTextArea("Card description \n");//TODO: card description doesnt work with the scroll pane
		textArea.setEditable(false);
		textArea.setLineWrap(true);

		JScrollPane scroller = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);// Scroll panel for card description
		scroller.setBounds(830, 295, 180, 134);//placement of the text area
		textArea.setFont(new Font("Calibari", Font.PLAIN, 20));
		textArea.setOpaque(false);
		textArea.setBounds(833, 299, 170, 130);
		attack.setFont(new Font("street soul", Font.PLAIN, 24));
		attack.setForeground(Color.WHITE);
		attack.setBounds(825, 441, 120, 23);
		defense.setFont(new Font("street soul", Font.PLAIN, 24));
		defense.setBounds(825, 475, 120, 23);
		defense.setForeground(Color.YELLOW);
		this.add(cardInfoPicture);
		this.add(scroller);
		this.add(attack);
		this.add(defense);
		//CARDinfoPanel end
		this.updateAll();
		this.revalidate();
		this.repaint();
		pack();

	//	System.out.println(P1Hand.getComponents().length);


	}

	public PhaseIndicator getPhaseIndicator() {
		return phaseIndicator;
	}

	public HandTab getTabArea() {
		return tabArea;
	}

	public HandPanel getP1Hand() {
		return P1Hand;
	}

	public void setP1Hand(HandPanel p1Hand) {
		P1Hand = p1Hand;
	}

	public HandPanel getP2Hand() {
		return P2Hand;
	}

	public void setP2Hand(HandPanel p2Hand) {
		P2Hand = p2Hand;
	}

	public MonsterCardB getMonsterToBeAttacked() {
		return monsterToBeAttacked;
	}

	public void setMonsterToBeAttacked(MonsterCardB monsterToBeAttacked) {
		this.monsterToBeAttacked = monsterToBeAttacked;
	}

	public MonsterCardB getMonsterToStartAttack() {
		return monsterToStartAttack;
	}

	public void setMonsterToStartAttack(MonsterCardB monsterToStartAttack) {
		this.monsterToStartAttack = monsterToStartAttack;
	}
	public SpellArea getP1SArea() {
		return p1SArea;
	}

	public void setP1SArea(SpellArea p1sArea) {
		p1SArea = p1sArea;
	}

	public InvisibleButton getDeckBP1() {
		return deckBP1;
	}

	public void setDeckBP1(InvisibleButton deckBP1) {
		this.deckBP1 = deckBP1;
	}

	public InvisibleButton getDeckBP2() {
		return deckBP2;
	}

	public void setDeckBP2(InvisibleButton deckBP2) {
		this.deckBP2 = deckBP2;
	}

	public JLabel getP1LabelName() {
		return P1LabelName;
	}

	public void setP1LabelName(JLabel p1Name) {
		P1LabelName = p1Name;
	}

	public JLabel getP2LabelName() {
		return P2LabelName;
	}

	public void setP2LabelName(JLabel p2LabelName) {
		P2LabelName = p2LabelName;
	}

	public JLabel getP1LPLabel() {
		return P1LPLabel;
	}

	public void setP1LPLabel(JLabel p1lpLabel) {
		P1LPLabel = p1lpLabel;
	}

	public JLabel getP2LPLabel() {
		return P2LPLabel;
	}

	public void setP2LPLabel(JLabel p2lpLabel) {
		P2LPLabel = p2lpLabel;
	}
	public InvisibleButton getEndPhase() {
		return endPhase;
	}
	public InvisibleButton getEndTurn() {
		return endTurn;
	}
	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}

	public JLabel getCardInfoPicture() {
		return cardInfoPicture;
	}

	public void setCardInfoPicture(JLabel cardInfoPicture) {
		this.cardInfoPicture = cardInfoPicture;
	}

	public JLabel getAttack() {
		return attack;
	}

	public void setAttack(JLabel attack) {
		this.attack = attack;
	}

	public JLabel getDefense() {
		return defense;
	}

	public void setDefense(JLabel defense) {
		this.defense = defense;
	}
	public void updateLPLabels(){// use this do update LifePoints after any action
		if(this.getP1LabelName().getText().equals(Controller.getBoard().getActivePlayer().getName())) 
			this.getP1LPLabel().setText(""+Controller.getBoard().getActivePlayer().getLifePoints());
		else this.getP1LPLabel().setText(""+Controller.getBoard().getOpponentPlayer().getLifePoints());
		if(this.getP2LabelName().getText().equals(Controller.getBoard().getActivePlayer().getName()))
			this.getP2LPLabel().setText(""+Controller.getBoard().getActivePlayer().getLifePoints());
		else this.getP2LPLabel().setText(""+Controller.getBoard().getOpponentPlayer().getLifePoints());	
		/*
		if(this.getP1LabelName().getText().equals(P1.getName())) //previouse code
			this.getP1LPLabel().setText(""+P1.getLifePoints());
		else this.getP1LPLabel().setText(""+P2.getLifePoints());
		if(this.getP2LabelName().getText().equals(P1.getName()))
			this.getP2LPLabel().setText(""+P1.getLifePoints());
		else this.getP2LPLabel().setText(""+P2.getLifePoints());
*/
	}
	public Player getP1() {
		return P1;
	}

	public Player getP2() {
		return P2;
	}
	public void updateP1Grave(){
		p1Grave.setText(""+P1.getField().getGraveyard().size());
		if(P1.getField().getGraveyard().size()!=0)
		p1Grave.setIcon(new ImageIcon( "cards\\"+P1.getField().getGraveyard().get(P1.getField().getGraveyard().size()-1).getName()+".jpg"));
		
	}
	public void updateP2Grave(){
		p2Grave.setText(""+P2.getField().getGraveyard().size());
		if(P2.getField().getGraveyard().size()!=0)
		p2Grave.setIcon(new ImageIcon( "cards\\"+P2.getField().getGraveyard().get(P2.getField().getGraveyard().size()-1).getName()+".jpg"));
	}

	public void updateCardInfoPanel(CardB card){
//			 p1Card =Main.getController().getBoard().getActivePlayer().getField().getHand().get(i);
		//if(!card.getCard().isHidden()){
			if(card instanceof SpellCardB){ //possible failure for type of spellCard which is abstract
				SpellCardB spell=(SpellCardB) card;
				textArea.setText(card.getName()+", \n"+card.getDescription());
				cardInfoPicture.setIcon(new ImageIcon("cardsL\\"+spell.getName()+".jpg"));
				attack.setText("");
				defense.setText("");
				
			}else {
				MonsterCardB monsterB=(MonsterCardB) card;
				MonsterCard monster =monsterB.getMonster();
				if(monsterB.getName().equals(monster.getName())){
					//conidition for is hidden or not
					//if(Main.getController().getBoard().getActivePlayer())
				textArea.setText(card.getName()+", \n"+card.getDescription()+"\n Level: "+monster.getLevel()+"\n Attack: "+monster.getAttackPoints()+"\n Defense: "+monster.getDefensePoints());
				cardInfoPicture.setIcon(new ImageIcon("cardsL\\"+monster.getName()+".jpg"));
				attack.setText("Attack: "+monster.getAttackPoints());
				defense.setText("Defense: "+monster.getDefensePoints());
				}
				else {textArea.setText("Card is face down");
					cardInfoPicture.setIcon(new ImageIcon("cardsL\\Cardback.jpg"));
						attack.setText("");
						defense.setText("");			}
			}
		}
		//}
	
/*	public void updateActiveHandnMonsternGrave(){ //TODO: note complete idea was to remove the monster field array and make a new one
		
		MonsterCardB monsterButton;
		ArrayList<Card> p1Hand=Controller.getBoard().getActivePlayer().getField().getHand();
		MonsterArea p1MonsterA= new MonsterArea();
		MonsterArea p2MonsterA= new MonsterArea();
		p1MonsterA.setBounds(224, 345, 466, 95);
		p2MonsterA.setBounds(224, 180, 466, 95);
		
		
		
		if(this.getP1LabelName().getText().equals(Controller.getBoard().getActivePlayer().getName())){
			for(int i=0;i<Controller.getBoard().getActivePlayer().getField().getHand().size();i++){// loop for hand
				
			}
			for(int i=0;i<Controller.getBoard().getActivePlayer().getField().getMonstersArea().size();i++){//loop for monsterArea
				monsterButton=new MonsterCardB(Controller.getBoard().getActivePlayer().getField().getMonstersArea().get(i));
				p1MonsterA.add(monsterButton);
			}
			//Don't forget graveyard update last element by doing size
			
			
			p1MArea=p1MonsterA;
			/*
			p1MArea.add(monsterButton);
			P1Hand.remove(monsterButton); //TODO: add appropriate action listener
			monsterButton.removeMouseListener(new ActionMenuMonster());
		*/
	
//		}
//		if(this.getP2LabelName().getText().equals(Controller.getBoard().getActivePlayer().getName())){
		/*	p2MArea.add(monsterButton); //incomplete meant to update both create a new array and replace
			P2Hand.remove(monsterButton); //TODO: add appropriate action listener
			monsterButton.removeMouseListener(new ActionMenuMonster());*/ 
//		}
//	}/*
	public void updateHandnMonsterBySummon(MonsterCard monster){ //TODO NOTE: I didn't remove the card from hand in the engine thinking it's already implemented so check this
		MonsterCardB tmp;
		if(this.getP1LabelName().getText().equals(Controller.getBoard().getActivePlayer().getName())){
			for(int i=0;i<P1Hand.getComponentCount();i++){// loop for hand
				if(P1Hand.getComponent(i) instanceof MonsterCardB){
					tmp=(MonsterCardB) P1Hand.getComponent(i);// if error do instance of
					if(monster.getName().equals(tmp.getName())){
						P1Hand.remove(P1Hand.getComponent(i));
					tmp.setOnField(true);
						p1MArea.add(tmp);
						break;
					}
				}
			}
		}

		if(this.getP2LabelName().getText().equals(Controller.getBoard().getActivePlayer().getName())){
			for(int i=0;i<P2Hand.getComponentCount();i++){// loop for hand
				if(P2Hand.getComponent(i) instanceof MonsterCardB){
					tmp=(MonsterCardB) P2Hand.getComponent(i);// if error do instance of
					if(monster.getName().equals(tmp.getName())){
						P2Hand.remove(P2Hand.getComponent(i));
						tmp.setOnField(true);
						p2MArea.add(tmp);
						break;
					}
				}
			}
			this.repaint();
			this.revalidate();
			this.setVisible(false);
			this.setVisible(true);
		}

	}
	public void updateHandnMonsterBySummon(MonsterCard monster,ArrayList <MonsterCard>sacrifices){//just removes the sacrifices
		//NOTE should be engines job but it didn't add sacrifices to graveyard in engine so I did it
		MonsterCardB tmp;
		if(this.getP1LabelName().getText().equals(Controller.getBoard().getActivePlayer().getName())){
			for(int i=0;i<p1MArea.getComponentCount();i++){// might decrease and give index out of bounds
				for(int j=0;j<sacrifices.size();j++){
					tmp=(MonsterCardB) p1MArea.getComponent(i);
					if(tmp.getMonster().getName().equals(sacrifices.get(j).getName())){
						p1MArea.remove(p1MArea.getComponent(i));
					}
				}
			}
		}
		if(this.getP2LabelName().getText().equals(Controller.getBoard().getActivePlayer().getName())){
			for(int i=0;i<p2MArea.getComponentCount();i++){// might decrease and give index out of bounds
				for(int j=0;j<sacrifices.size();j++){
					tmp=(MonsterCardB) p2MArea.getComponent(i);
					if(tmp.getMonster().getName().equals(sacrifices.get(j).getName())){
						p2MArea.remove(p2MArea.getComponent(i));
					}
				}
			}

		}
		this.updateHandnMonsterBySummon(monster);
		this.updateP1Grave();
		this.updateP2Grave();
	}
	public void updateHandnSpellArea(SpellCard spell){
		SpellCardB tmp = null;
		if(this.getP1LabelName().getText().equals(Controller.getBoard().getActivePlayer().getName())){
			for(int i=0;i<P1Hand.getComponentCount();i++){// loop for hand
				if(P1Hand.getComponent(i) instanceof SpellCardB){
					tmp=(SpellCardB) P1Hand.getComponent(i);// if error do instance of
					if(spell.getName().equals(tmp.getName())){
						P1Hand.remove(P1Hand.getComponent(i));
						tmp.setOnField(true);
						p1SArea.add(tmp);
						this.repaint();
						this.revalidate();
						//toRemove=getComponent(i);
						break;
					}
				}
			}

			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();}

			p1SArea.remove(tmp);

		}

		if(this.getP2LabelName().getText().equals(Controller.getBoard().getActivePlayer().getName())){
			for(int i=0;i<P2Hand.getComponentCount();i++){// loop for hand
				if(P2Hand.getComponent(i) instanceof SpellCardB){
					tmp=(SpellCardB) P2Hand.getComponent(i);// if error do instance of
					if(spell.getName().equals(tmp.getName())){
						P2Hand.remove(P2Hand.getComponent(i));
						tmp.setOnField(true);
						p2SArea.add(tmp);
						this.repaint();
						this.revalidate();
						//	toRemove=getComponent(i);
						break;
					}
				}
			}


			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();}
			p2SArea.remove(tmp);
		}



		this.setVisible(false);
		this.setVisible(true);

		this.repaint();
		this.revalidate();
		this.updateP1Grave();
		this.updateP2Grave();
	}
	public void updateSpellAreaFromField(SpellCard spell){
		SpellCardB tmp = null;
		if(this.getP1LabelName().getText().equals(Controller.getBoard().getActivePlayer().getName())){
			for(int i=0;i<p1SArea.getComponentCount();i++){// loop for hand
				tmp=(SpellCardB) p1SArea.getComponent(i);// if error do instance of
				if(spell.getName().equals(tmp.getName())){
					tmp.makeVisible();
					this.repaint();
					this.revalidate();
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();}

					p1SArea.remove(tmp);

				}

				//toRemove=getComponent(i);
				break;

			}

		}



		if(this.getP2LabelName().getText().equals(Controller.getBoard().getActivePlayer().getName())){
			for(int i=0;i<p2SArea.getComponentCount();i++){// loop for hand
				tmp=(SpellCardB) p2SArea.getComponent(i);// if error do instance of
				if(spell.getName().equals(tmp.getName())){
					tmp.makeVisible();
					this.repaint();
					this.revalidate();
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();}

					p2SArea.remove(tmp);

				}

				//toRemove=getComponent(i);
				break;

			}

		}



		this.setVisible(false);
		this.setVisible(true);

		this.repaint();
		this.revalidate();
		this.updateP1Grave();
		this.updateP2Grave();
	}
	public void monsterAttackUpdate(){
		
	}
	public void updateMonsterArea1(){
		
		ArrayList<MonsterCard> engPoint = P1.getField().getMonstersArea();
		this.p1MArea.removeAll();
		MonsterCardB a;
		for(int i = 0; i<engPoint.size();i++){
			a=new MonsterCardB(engPoint.get(i));
			a.setOnField(true);
			this.p1MArea.add(a);
			if(engPoint.get(i).getMode()==Mode.DEFENSE)
				a.setDefense();
			if(engPoint.get(i).getMode()==Mode.ATTACK)
				a.setAttack();
			if(engPoint.get(i).isHidden())
				a.makeHidden();
			else
				a.makeVisible();
		}
	}
	public void updateSpellArea1(){
		ArrayList<SpellCard> engPoint = P1.getField().getSpellArea();
		this.p1SArea.removeAll();
		SpellCardB a;
		for(int i = 0; i<engPoint.size();i++){
			a=new SpellCardB(engPoint.get(i));
			a.setOnField(true);
			this.p1SArea.add(a);
			if(engPoint.get(i).isHidden())
				a.makeHidden();
			
		}
	}
	public void updateHand1(){
		ArrayList<Card> engPoint = P1.getField().getHand();
		this.P1Hand.removeAll();
		for(int i = 0; i<engPoint.size();i++){
			if(engPoint.get(i) instanceof SpellCard)
			this.P1Hand.add(new SpellCardB((SpellCard) engPoint.get(i)));
			else
				this.P1Hand.add(new MonsterCardB((MonsterCard)engPoint.get(i)));
		}
	}
public void updateMonsterArea2(){
		
		ArrayList<MonsterCard> engPoint = P2.getField().getMonstersArea();
		this.p2MArea.removeAll();
		MonsterCardB a;
		for(int i = 0; i<engPoint.size();i++){
			a=new MonsterCardB(engPoint.get(i));
			a.setOnField(true);
			this.p2MArea.add(a);
			if(engPoint.get(i).getMode()==Mode.ATTACK)
				a.setAttack();
			if(engPoint.get(i).getMode()==Mode.DEFENSE)
				a.setDefense();
			if(engPoint.get(i).isHidden())
				a.makeHidden();
			else
				a.makeVisible();
			//this.p2MArea.add(new MonsterCardB(engPoint.get(i)));
		}
	}
	public void updateSpellArea2(){
		ArrayList<SpellCard> engPoint = P2.getField().getSpellArea();
		this.p2SArea.removeAll();
		SpellCardB a;
		for(int i = 0; i<engPoint.size();i++){
			a=new SpellCardB(engPoint.get(i));
			a.setOnField(true);
			this.p2SArea.add(a);
			if(engPoint.get(i).isHidden())
				a.makeHidden();
		}
	}	//in engine does summoning monster remove from hand?
	public void updateHand2(){
		ArrayList<Card> engPoint = P2.getField().getHand();
		this.P2Hand.removeAll();
		for(int i = 0; i<engPoint.size();i++){
			if(engPoint.get(i) instanceof SpellCard)
			this.P2Hand.add(new SpellCardB((SpellCard) engPoint.get(i)));
			else
				this.P2Hand.add(new MonsterCardB((MonsterCard)engPoint.get(i)));
		}
	}
	public void updateDecks(){
		if(this.getP1LabelName().getText().equals(Controller.getBoard().getActivePlayer().getName())) 
			this.getDeckBP1().setText(""+Main.getController().getBoard().getActivePlayer().getField().getDeck().getDeck().size());
		else this.getDeckBP1().setText(""+Main.getController().getBoard().getOpponentPlayer().getField().getDeck().getDeck().size());
		if(this.getP2LabelName().getText().equals(Controller.getBoard().getActivePlayer().getName()))
			this.getDeckBP2().setText(""+Main.getController().getBoard().getActivePlayer().getField().getDeck().getDeck().size());
		else this.getDeckBP2().setText(""+Main.getController().getBoard().getOpponentPlayer().getField().getDeck().getDeck().size());
		
	}
	public void updateAll(){
		this.updateHand1();
		this.updateHand2();
		this.updateMonsterArea1();
		this.updateMonsterArea2();
		this.updateSpellArea1();
		this.updateSpellArea2();
		this.updateLPLabels();
		this.updateP1Grave();
		this.updateP2Grave();
		this.updateDecks();
		this.validate();
	}
}
