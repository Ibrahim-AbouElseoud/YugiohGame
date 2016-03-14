package eg.edu.guc.yugioh.board;

import java.util.Random;

import javax.swing.JOptionPane;

import eg.edu.guc.yugioh.board.player.Phase;
import eg.edu.guc.yugioh.board.player.Player;
import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.gui.Main;
import eg.edu.guc.yugioh.gui.board.WinnerBox;

public class Board {

	private Player activePlayer;
	private Player opponentPlayer;
	private Player winner;
	private boolean ended = false;
	public Board() {
		ended = false;
		Card.setBoard(this);

	}

	public void whoStarts(Player p1, Player p2) {

		Random r = new Random();

		if (r.nextInt(2) == 0) {
			setActivePlayer(p1);
			setOpponentPlayer(p2);
		} else {
			setActivePlayer(p2);
			setOpponentPlayer(p1);
		}

	}

	public void startGame(Player p1, Player p2) {
		ended = false;
		p1.getField().addNCardsToHand(5);
		p2.getField().addNCardsToHand(5);

		whoStarts(p1, p2);

		//activePlayer.getField().addCardToHand();

	}
	
	public void end(){
	
		ended = true;
		new WinnerBox();
		//JOptionPane.showMessageDialog(Main.getGameBoard(), "The ultimate street champion is "+this.getWinner().getName()+" !");//TODO: replace messagebox with box giving possibility of saying play again yes or no
	}
	
	public void nextPlayer() {

		Player temp = activePlayer;
		setActivePlayer(opponentPlayer);
		setOpponentPlayer(temp);
		//activePlayer.getField().addCardToHand();
		activePlayer.getField().setPhase(Phase.MAIN1);
	}
	public Player getActivePlayer() {
		return activePlayer;
	}

	public void setActivePlayer(Player activePlayer) {
		this.activePlayer = activePlayer;
	}

	public Player getOpponentPlayer() {
		return opponentPlayer;
	}

	public void setOpponentPlayer(Player opponentPlayer) {
		this.opponentPlayer = opponentPlayer;
	}

	public Player getWinner() {
		return winner;
	}

	public void setWinner(Player winner) {
		this.winner = winner;
	}

	public boolean isEnded() {
		return ended;
	}

	public void setEnded(boolean ended) {
		this.ended = ended;
	}

}
