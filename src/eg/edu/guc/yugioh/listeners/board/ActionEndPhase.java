package eg.edu.guc.yugioh.listeners.board;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import eg.edu.guc.yugioh.gui.Main;

public class ActionEndPhase implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		Main.getGameBoard().getPhaseIndicator().changePhase();
		// TODO Auto-generated method stub
	}

}
