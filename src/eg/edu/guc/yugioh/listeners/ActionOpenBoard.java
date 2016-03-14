package eg.edu.guc.yugioh.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.cards.spells.SpellCard;
import eg.edu.guc.yugioh.exceptions.UnexpectedFormatException;
import eg.edu.guc.yugioh.gui.GameBoard;
import eg.edu.guc.yugioh.gui.Main;
import eg.edu.guc.yugioh.gui.board.buttons.MonsterCardB;
import eg.edu.guc.yugioh.gui.board.buttons.SpellCardB;
import eg.edu.guc.yugioh.listeners.board.ActionTestName;

public class ActionOpenBoard implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		 String nameP1="";
		 String nameP2="";
		do{
		 nameP1 = JOptionPane.showInputDialog(Main.getGameBoard(),"Name of Player 1", null);
		 if(nameP1==null)
			 System.exit(0);
	}while(nameP1.equals(""));
		 do{ 
			 nameP2 = JOptionPane.showInputDialog(Main.getGameBoard(),"Name of Player 2", null);
		if(nameP2==null)
			 System.exit(0); //TODO: how to terminate java program
		 }while(nameP2.equals(""));
		try {
			Main.setController(new Controller(nameP1,nameP2));
		} catch (IOException | UnexpectedFormatException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(Main.getMainMenu(),"Corrupted files!");
			e1.printStackTrace();
		}
		 Main.getMainMenu().setVisible(false);
		/* Main.getGameBoard().setNameP1(nameP1);
		 Main.getGameBoard().setNameP2(nameP2);*/
		 try {
			Main.setGameBoard(new GameBoard("Yu-Gi-Oh street"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(Main.getMainMenu(),"Corrupted files!");
			e1.printStackTrace();
		}
		 //add5DrawnP2();
		// add5DrawnP1();
		 //drawFirstCardForBoth();
		 
		 Main.getGameBoard().validate();
		 Main.getGameBoard().setVisible(true);
		 
	}
	public void drawFirstCardForBoth(){
		Card p1Card =Main.getController().getBoard().getActivePlayer().getField().getHand().get(0);
		Card p2Card=Main.getController().getBoard().getOpponentPlayer().getField().getHand().get(0);
		if(p1Card instanceof SpellCard){//possible failure for type of spellCard which is abstract , maybe solution is using instance of for specific spells
			SpellCard spell=(SpellCard) p1Card;
			SpellCardB a= new SpellCardB(spell);
			Main.getGameBoard().getP1Hand().add(a);
		}else {
			MonsterCard monster=(MonsterCard) p1Card;
			MonsterCardB a=new MonsterCardB(monster);
			Main.getGameBoard().getP1Hand().add(a);
		}
		if(p2Card instanceof SpellCard){
			SpellCard spell=(SpellCard) p2Card;
			SpellCardB b= new SpellCardB(spell);
			Main.getGameBoard().getP2Hand().add(b);
		}else {
			MonsterCard monster=(MonsterCard) p2Card;
			MonsterCardB b=new MonsterCardB(monster);
			Main.getGameBoard().getP2Hand().add(b);
		}
	}
	public void add5DrawnP2(){
		Card p2Card;
		for(int i=0;i<5;i++){
			 p2Card =Main.getController().getBoard().getOpponentPlayer().getField().getHand().get(i);
			if(p2Card instanceof SpellCard){ //possible failure for type of spellCard which is abstract
				SpellCard spell=(SpellCard) p2Card;
				SpellCardB a= new SpellCardB(spell);
				a.addActionListener(new ActionTestName());// replace with appropriate listener
				Main.getGameBoard().getP2Hand().add(a);
			}else {
				MonsterCard monster=(MonsterCard) p2Card;
				MonsterCardB a=new MonsterCardB(monster);
				a.addActionListener(new ActionTestName()); // replace with appropriate listener for get
				Main.getGameBoard().getP2Hand().add(a);
			}
		}
	}
	public void add5DrawnP1(){
		Card p1Card;
		for(int i=0;i<5;i++){
			 p1Card =Main.getController().getBoard().getActivePlayer().getField().getHand().get(i);
			if(p1Card instanceof SpellCard){ //possible failure for type of spellCard which is abstract
				SpellCard spell=(SpellCard) p1Card;
				SpellCardB a= new SpellCardB(spell);
				a.addActionListener(new ActionTestName()); // replace with appropriate listener for get
				Main.getGameBoard().getP1Hand().add(a);
			}else {
				MonsterCard monster=(MonsterCard) p1Card;
				MonsterCardB a=new MonsterCardB(monster);
				a.addActionListener(new ActionTestName()); // replace with appropriate listener for get
				Main.getGameBoard().getP1Hand().add(a);
			}
		}
	}


}
