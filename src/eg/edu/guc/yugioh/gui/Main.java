package eg.edu.guc.yugioh.gui;

import java.io.IOException;

import eg.edu.guc.yugioh.board.Board;
import eg.edu.guc.yugioh.exceptions.UnexpectedFormatException;
import eg.edu.guc.yugioh.listeners.Controller;

public class Main {
	private static MainMenu mainMenu;
	private static GameBoard gameBoard;
	//private static Board board=new Board();
	private static Controller controller;
	public static MainMenu getMainMenu() {
		return mainMenu;
	}
	public static void setMainMenu(MainMenu NmainMenu) {
		mainMenu = NmainMenu;
	}
	public static GameBoard getGameBoard() {
		return gameBoard;
	}
	public static void setGameBoard(GameBoard NgameBoard) {
		gameBoard = NgameBoard;
	}
public static void main(String[]args) throws IOException, UnexpectedFormatException{
	mainMenu=new MainMenu("YugiOOOooh~");
	mainMenu.setVisible(true);
	mainMenu.validate();
	//gameBoard= new GameBoard("YugiOOOooh~");
	//gameBoard.validate();
}
public static Controller getController() {
	return controller;
}
public static void setController(Controller controller) {
	Main.controller = controller;
}

}
