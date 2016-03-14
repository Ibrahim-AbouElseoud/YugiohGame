package eg.edu.guc.yugioh.gui.board;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import eg.edu.guc.yugioh.board.Board;
import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.exceptions.UnexpectedFormatException;
import eg.edu.guc.yugioh.gui.GameBoard;
import eg.edu.guc.yugioh.gui.Main;
import eg.edu.guc.yugioh.listeners.ActionExitGame;
import eg.edu.guc.yugioh.listeners.ActionNewGame;
import eg.edu.guc.yugioh.listeners.ActionOpenBoard;
import eg.edu.guc.yugioh.listeners.Controller;

public class WinnerBox extends JDialog {
	JPanel boxPanel=new JPanel();
	//REMEMBER to make a boolean for this and monster that is called isCanceled
	Boolean isCanceled=false;
	JLabel comment= new JLabel("The Champion is "+Main.getController().getBoard().getWinner().getName()+" \n would you like to play again?");
	JPanel container=new JPanel();
	static WinnerBox a;
	public WinnerBox(){ //self true if pick monster from own field , false if pick monster from opponent field
//		if(Main.getController().getBoard().isEnded()){
			
//		}
		super(Main.getGameBoard(),"The Champion is "+Main.getController().getBoard().getWinner().getName()+" play again?",true); //which ever the effect pass the correct monstersOnField list whether active player or opponent
		container.add(comment);
		a=this;
		JButton activate=new JButton("Yes");
		JButton cancel=new JButton("No");
		container.add(activate);
		container.add(cancel);
		this.add(container);
		
		this.setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		Main.getGameBoard().setVisible(false);
		activate.addActionListener(new ActionListener(){
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
					Main.getGameBoard().setVisible(true);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(Main.getMainMenu(),"Corrupted files!");
					e1.printStackTrace();
				}
				
				 a.setVisible(false);
				 
			}
		});

		cancel.addActionListener(new ActionExitGame());
		pack(); 
		this.setVisible(true);
		
}
}
