package eg.edu.guc.yugioh.listeners;

import java.io.IOException;

import javax.swing.JOptionPane;

import eg.edu.guc.yugioh.board.Board;
import eg.edu.guc.yugioh.board.player.Player;
import eg.edu.guc.yugioh.exceptions.UnexpectedFormatException;
import eg.edu.guc.yugioh.gui.Main;

public class Controller {
	private static Board board= new Board();
	
	public Controller(String nameP1,String nameP2) throws IOException, UnexpectedFormatException {
	Player p1=new Player(nameP1);
	Player p2=new Player(nameP2);
	Controller.getBoard().startGame(p1, p2);
	JOptionPane.showMessageDialog(Main.getGameBoard(), "The Random powers that be decided that "+board.getActivePlayer().getName()+" will start first");
	}

	public static Board getBoard() {
		return board;
	}

	public static void setBoard(Board board) {
		Controller.board = board;
	}
}
