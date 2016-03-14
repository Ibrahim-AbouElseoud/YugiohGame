package eg.edu.guc.yugioh.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

import eg.edu.guc.yugioh.exceptions.UnexpectedFormatException;
import eg.edu.guc.yugioh.gui.GameBoard;
import eg.edu.guc.yugioh.gui.Main;
import eg.edu.guc.yugioh.gui.board.WinnerBox;

public class ActionNewGame implements ActionListener{
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
		 WinnerBox a=(WinnerBox) e.getSource();
		 a.dispose();
		 
	}

}
